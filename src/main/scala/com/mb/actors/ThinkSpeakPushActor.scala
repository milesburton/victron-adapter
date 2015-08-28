package com.mb.actors

import akka.actor.{Actor, Stash}
import com.mb.thinkspeak.ThinkSpeakPublisher
import com.mb.victron.SolarStatus


class ThinkSpeakPushActor(key: String) extends Actor with Stash {

  override def receive: Receive = {
    case a: SolarStatus =>
      new ThinkSpeakPublisher().publish(a, key)

    case _ =>
      throw new RuntimeException("Unexpected message")
  }

}
