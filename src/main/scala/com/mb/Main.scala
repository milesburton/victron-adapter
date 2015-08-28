package com.mb


import akka.actor.{Actor, ActorRef, ActorSystem, Props, Stash}
import akka.io.IO
import rxtxio.Serial
import rxtxio.Serial._

class Main(port: String) extends Actor with Stash {
  import context.system

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
    case "close" =>
      println("Closing")
      operator ! Close

    case s: String => //external input
      //operator ! Write(ByteString(s))

    case Received(data) =>
      print("Received data from serial port: ")
      println(data.decodeString("UTF-8"))

    case Closed =>
      println("Serial port closed")
      context stop self
  }
}

object Main extends App {
  val port = "/dev/ttyUSB0"

  val system = ActorSystem("Example")
  val actor = system.actorOf(Props(new Main(port)), "e1")

  //The following lines are just for the sake of an example, never program in
  // akka like that.. normally your logic would be embedded in the example
  // actor
  actor ! "Hi from the computer"
  Thread.sleep(2000)
  actor ! "close"

  system.awaitTermination
}