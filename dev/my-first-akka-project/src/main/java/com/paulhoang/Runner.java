package com.paulhoang;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.paulhoang.actors.AkkaDbActor;
import com.paulhoang.messages.SetRequest;

/**
 * Created by paul on 17/07/2016.
 */
public class Runner {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("MyAkkaActorSystem");
        ActorRef myAkkaDbActor = actorSystem.actorOf(Props.create(AkkaDbActor.class), "MyAkkaDbActor");
        myAkkaDbActor.tell(new SetRequest("key","val"), ActorRef.noSender());
    }
}
