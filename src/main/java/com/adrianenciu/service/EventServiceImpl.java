package com.adrianenciu.service;

import com.adrianenciu.model.Event;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void send(Event event) {
        jmsTemplate.convertAndSend("test-queue", event);
    }

    @JmsListener(destination = "test-queue")
    public void receive(Event event) {
        System.out.println(event);
    }

    @JmsListener(destination = "test-queue.history")
    public void receiveHistoryMessage(Event event) {
        System.out.println(event);
    }

    public static void main(String[] args) throws NamingException, JMSException {
        long t = System.currentTimeMillis();
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        Context context = new InitialContext(props);
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        long delta = System.currentTimeMillis() - t;
        System.out.println("Got the connectionFactory in " + delta + " millis\n");

        sendMessage(connectionFactory);
        readMessage(connectionFactory);

        sendMessage(connectionFactory);
        readMessage(connectionFactory);
    }

    public static void sendMessage(ConnectionFactory connectionFactory) throws JMSException {
        long t = System.currentTimeMillis();
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        long delta = System.currentTimeMillis() - t;
        System.out.println("Producer created in " + delta + " millis\n");
        TextMessage message = new ActiveMQTextMessage();
        message.setText("test-message");
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
    }

    public static void readMessage(ConnectionFactory connectionFactory) throws JMSException {
        long t = System.currentTimeMillis();
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        long delta = System.currentTimeMillis() - t;
        System.out.println("Consumer created in " + delta + " millis\n");
        Message receivedMessage = consumer.receive(5000);
        System.out.println(receivedMessage);
        consumer.close();
        session.close();
        connection.close();
    }


}
