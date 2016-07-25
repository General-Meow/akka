package com.paulhoang.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.paulhoang.messages.SetRequest;
import junit.framework.Assert;
import org.junit.Test;


/**
 * Created by paul on 18/07/2016.
 */
public class AkkaDbActorTest {

    ActorSystem system = ActorSystem.create();

    @Test
    public void shouldLogAndStoreMessage(){
        TestActorRef<AkkaDbActor> akkaActorRef = TestActorRef.create(system, Props.create(AkkaDbActor.class));
        SetRequest testMessage = new SetRequest("MyKey", "MyValue");
        akkaActorRef.tell(testMessage, ActorRef.noSender());

        AkkaDbActor akkaDbActor = akkaActorRef.underlyingActor();
        Assert.assertEquals("MyValue", akkaDbActor.getMap().get("MyKey"));
    }
}