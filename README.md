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


