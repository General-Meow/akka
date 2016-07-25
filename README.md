Scala activator:
The activator is a bundle that contains scala, akka, simple built tool (SBT) and other tools
Unzip the downloaded file and set the path in the zshrc/bashrc or whatever

export PATH=$PATH:/Users/paul/Dev/Software/activator-dist-1.3.10/bin

- ./activator 				//To install
- ./activator ui 			//To start ui in web browser
- ./activator new			//Create a new templated project
- ./activator test			//run the test in the current projects directory

SBT
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
- sender() method returns an ActorRef to the actor that sent the message and using tell() on it will allow you to send a message back
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