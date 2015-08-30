package com.mb

import akka.actor.{ActorSystem, Props}
import com.mb.actors.{RxListenerActor, ThinkSpeakPushActor}

object Main extends App {

  val parser = new scopt.OptionParser[Config]("VE.Direct To ThinkSpeak") {
    opt[String]('s', "serialPort") required() action { (serialPort, c) =>
      c.copy(serialPort = Some(serialPort))
    } text "Please specify serialPort"

    opt[String]('k', "apiKey") required() action { (apiKey, c) =>
      c.copy(apiKey = Some(apiKey))
    } text "Please specify Think Speak API Key"

    opt[Int]('t', "sampleTimeInSeconds") required() action { (t, c) =>
      c.copy(sampleTimeInSeconds = Some(t))
    } text "Please specify sample time in seconds"

  }

  val optionalConfig = parser.parse(args, Config())

  if (optionalConfig.isEmpty) {
    System.exit(0)
  }

  val config = optionalConfig.get

  val system = ActorSystem("reactor")

  val thinkSpeakActor = system.actorOf(Props(new ThinkSpeakPushActor(config.apiKey.get, config.sampleTimeInSeconds.get)), "thinkspeakPublisher")
  val rxActor = system.actorOf(Props(new RxListenerActor(config.serialPort.get, thinkSpeakActor)), "rxtx")


  system.awaitTermination
}

case class Config(serialPort: Option[String] = None, apiKey: Option[String] = None, sampleTimeInSeconds: Option[Int] = None)