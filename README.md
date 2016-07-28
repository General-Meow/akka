Scala activator:
The activator is a bundle that contains scala, akka, simple built tool (SBT) and other tools
Unzip the downloaded file and set the path in the zshrc/bashrc or whatever

export PATH=$PATH:/Users/paul/Dev/Software/activator-dist-1.3.10/bin

- ./activator 				//To install
- ./activator ui 			//To start ui in web browser
- ./activator new			//Create a new templated project
- ./activator test			//run the test in the current projects directory
- ./activator compile		//build

##SBT
As scala has alot of breaking changes between versions, libraries build against many different versions of scala.
To make SBT attempt to choose the correct version of a dependency for the set version of scala, put double percent
signs '%%' after the group id. i.e

"com.typesafe.akka" %% "akka-actor" % "2.3.11",


- Extend the AbstractActor to create an actor
- UntypedActor is an alternative to AbstractActor but its old and deprecated. It uses if statements to see if it can handle the message sent to it rather than using the ReceiveBuilder
- Have the contructor call the receive() method and provide it with a RecieveBuilder
- Or override the receive method
- The ReceieveBuilder will need to be configured with matchers to configure it to match against particular conditions. it will then need to return the result of build()
- There are a number of match methods on the ReceiveBuilder to create the matchers
- The matcher has a condition and also a function (lamda) that will run once a message has come that matches the condition. Here you can respond to the sender of the message using the sender().tell() method
- sender() method returns an ActorRef to the actor that sent the message and using tell() on it will allow you to send a message back in a fire and forget way
- When you use tell, you provide it with the message and the actor that the receipent will see. You can use ActorRef.noSender() to ensure one way communication to that actor
- Exceptions thrown within an actor will trigger the supervisor, so if you want to reply with an error, you can wrap the exception in a Status.Failure and send that via a tell()
- You never interact with an actor directly, you can't call methods on it and you cant gain access to its state. The only way in which you can gain access is via messages
- To create an actor, you first get an instance of the Actor System. Then with that you call actorOf() method passing a call to Props.create() with the actor class type and params
i.e. ActorRef myActor = actorSystem.actorOf(Props.create(MyActorType.class))
- Props is a factory class used to pass on properties to actors so that we dont use the actor constructors
- i.e. Props.create(MyActorClass.class, arg1, arg2, arg3, argn)
- Each actor has a path to which you can refer to it with. You get the path by doing: actorRef.path. This could return something like:
- akka://default/usr/paul
- It can even be a uri to another actor on the network: akka.tcp://my-system:3432/usr/paul
- You can use the uri to get a reference of the actor using actorSelection method from the system class. ActorSelection actor = system.actorSelection("akka://...")

##Futures

- To be as perfomant as possible, we should use as much asynchronous code as possible. This eventually means we need to use Futures
- The ask method is used to place a message on an Actor's message queue to receive a response asynchronously. It returns a Scala Future
- Akka returns Scala futures and they are not directly compatable with Java.
- To use Scala futures in Java, you need to convert the Future into a CompletionStage<x> type with the toJava(scala.Future) then cast that result into a CompletableFuture<x> 
- If you need to make an async call with the result of another async call, you should compose the CompletionStage<> Futures with thenApply (in java) or map (in Scala) i.e. asyncCall(message).thenApply(result -> anotherAsyncCall(result))
- By composing, you do not need to get the result from the future as it unwraps it for you
- Failures in the java version of akka isn't as nice as the Scala version. In java, you need to use the handle() method which returns a two parameters, first with the result the second with the throwable. In Scala, the onFailure method can be used.
i.e. ask(xxx).handle( (x, t) -> { if( t != null) do error thing } );

- The method exceptionally() also allows you to deal with errors but this method is just for handling errors. 
- merging multiple futures can be done using the thenCombine() method, this will allow you to use the result of two futures within the lambda.

In summary for Futures
When you want to transform the value, use thenApply()
When you want to async transform the value, use thenCompose()
When you want to return a val if error, use exceptionally()
When you want to return async value from error, use handle().thenCompose()


##Remoting

- To enable remote akka actors, you will need an additional dependency
- place into the sbt build file "com.typesafe.assk" %% "akka-remote" % "2.3.6"
- You then need to add configuration to allow remote access to the actors in this application. To do this you need the configuration file to be written in HOCON in the src/main/resources dir called application.conf
- 

###application.conf
akka {
	actor {
		provider = "akka.remote.RemoteActoreRefProvider"
	}
	remote {
		enabled-transports = { "akka.remote.netty.tcp" }
		netty.tcp {
			hostname = "127.0.0.1"
			port = 2552
		} 
	}
}
















