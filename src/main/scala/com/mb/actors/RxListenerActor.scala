package com.mb.actors

import akka.actor.{Actor, ActorRef, Stash}
import akka.io.IO
import com.mb.victron.VEDirectPacketAdapter
import rxtxio.Serial
import rxtxio.Serial._

class RxListenerActor(port: String, solarStatusListener: ActorRef) extends Actor with Stash {

  import context.system

  val veAdapter = new VEDirectPacketAdapter

  override def preStart = {
    IO(Serial) ! Open(port, 19200)
  }

  override def postStop = {
    println("Stopped")
    system.shutdown
  }

  override def receive = {
    case Opened(operator, _) =>
      println("Connected to port")
      context become open(operator)
      unstashAll()
    case CommandFailed(_, error) =>
      println(s"Could not connect to port: $error")
      context stop self
    case other => stash()
  }

  def open(operator: ActorRef): Receive = {

    case Received(data) =>

      solarStatusListener ! veAdapter.convert(data.decodeString("UTF-8"))

    case Closed =>
      println("Serial port closed")
      context stop self
  }
}
