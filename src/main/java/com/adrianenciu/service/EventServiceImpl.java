package com.adrianenciu.service;

import com.adrianenciu.model.Event;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void send(final Event event) {
        jmsTemplate.send("test-queue", (session) -> session.createObjectMessage(event));
    }

//    @JmsListener(destination = "test-queue")
//    public void receive(Message message) {
//        System.out.println(message);
//    }
}
