package com.adrianenciu.controller;

import com.adrianenciu.model.Event;
import com.adrianenciu.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.POST)
    public String sendEvent(@RequestHeader("X-Client-Id") String clientId,
                            @RequestHeader("X-Destination-Id") String destinationId,
                            @RequestBody Event event) {
        LOGGER.info("clientId {}", clientId);
        LOGGER.info("destinationId {}", destinationId);
        eventService.send(event);
        return event.getType() + " successfully processed";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> readEvent(@RequestHeader("X-Client-Id") String clientId,
                                       @RequestHeader("X-Destination-Id") String destinationId) {
        return null;
    }

}
