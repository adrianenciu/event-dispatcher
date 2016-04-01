package com.adrianenciu.service;

import com.adrianenciu.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

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
}
