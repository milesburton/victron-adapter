# VE.Direct to ThingSpeak adapter

By taking advantage of the Victron Bluesolar serial output we can translate the VE.Direct messages and push normalised wattage, amperage & voltages into ThingSpeak's IoT graph service.

## Supporting libraries
* Scala 2.11
* Akka
* RXTX
* Spray

## Getting started

Assuming you have installed Scala

sbt assembly

This will produce target/scala-2.11/solar-assembly-0.0.1.jar

As a fat JAR you can copy this to your target device or simply run it with java target/scala-2.11/solar-assembly-0.0.1.jar and you will be provided with the options.

### Raspberry Pi?
Install Java: http://www.rpiblog.com/2014/03/installing-oracle-jdk-8-on-raspberry-pi.html
sudo apt-get instal librxtx-java
java -Djava.library.path=/usr/lib/jni/ -jar solar-assembly-0.0.1.jar  --serialPort /dev/ttyUSB0  --apiKey $THINK_SPEAK_API --sampleTimeInSeconds 60


jar /dev/ttyUSB0 <ThinkSpeak-Api-Key>

