package com.mb


import akka.actor.{ActorSystem, Props}
import com.mb.actors.{RxListenerActor, ThinkSpeakPushActor}

object Main extends App {


  val port = if (args.length > 0) args(0) else "/dev/ttyUSB0"
  val apiKey = if (args.length > 1) args(1) else throw new RuntimeException("API key not provided alongside your device [device] [key]")
  val system = ActorSystem("reactor")

  val thinkSpeakActor = system.actorOf(Props(new ThinkSpeakPushActor(apiKey)),"thinkspeakPublisher")
  val rxActor = system.actorOf(Props(new RxListenerActor(port,thinkSpeakActor)), "rxtx")


  system.awaitTermination
}