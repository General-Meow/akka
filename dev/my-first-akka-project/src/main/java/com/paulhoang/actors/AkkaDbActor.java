package com.paulhoang.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.paulhoang.messages.SetRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by paul on 18/07/2016.
 */
public class AkkaDbActor extends AbstractActor {

    private LoggingAdapter LOG = Logging.getLogger(context().system(), this);
    private Map<String, Object> map = new HashMap<>();

    public AkkaDbActor(){
        receive(ReceiveBuilder.match(SetRequest.class, message -> {
            LOG.info("Received message of type SetRequest key: {} value: {}", message.getKey(), message.getValue());
            map.put(message.getKey(), message.getValue());
        }).matchAny(message -> {
            LOG.info("Got message of unknown type {}", message);
        }).build());
    }

    public Map<String, Object> getMap(){
        return this.map;
    }

}
