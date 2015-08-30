package com.mb.actors

import akka.actor.{Actor, Stash}
import com.mb.thinkspeak.ThinkSpeakPublisher
import com.mb.victron.SolarStatus
import org.joda.time.{Seconds, DateTime}


class ThinkSpeakPushActor(val key: String, val sampleIntervalInSeconds: Int = 60) extends Actor with Stash {

  var lastSampleTime:Option[DateTime] = None

  override def receive: Receive = {
    case status@SolarStatus(Some(_),Some(_),Some(_),Some(_),Some(_),Some(_))
      if lastSampleTime.isEmpty || Seconds.secondsBetween(lastSampleTime.get, DateTime.now).getSeconds >= sampleIntervalInSeconds =>

      lastSampleTime = Some(DateTime.now())
      new ThinkSpeakPublisher().publish(status, key)

  }

}
