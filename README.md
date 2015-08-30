# VE.Direct to ThingSpeak adapter

By taking advantage of the Victron Bluesolar serial output we can translate the VE.Direct messages and push normalised wattage, amperage & voltages into ThingSpeak's IoT graph service.

## Supporting libraries
* Scala 2.11
* Akka
* RXTX
* Spray

## Getting started
sbt assembly
java -jar target/scala-2.11/solar-assembly-0.0.1.jar /dev/ttyUSB0 <ThinkSpeak-Api-Key>
