package com.mb.thinkspeak

import com.mb.victron.SolarStatus
import org.scalamock.proxy.ProxyMockFactory
import org.scalatest.{FlatSpec, Matchers}


class ThinkSpeakPublisherTest extends FlatSpec with Matchers with ProxyMockFactory {


  "builder" should "add key to url" in {


    val publisher = new ThinkSpeakPublisher({
      url =>
        url should be("http://api.thingspeak.com/update?field5=5&field1=1&field4=4&key=TEST&field3=3&field2=2&field6=6")
        null
    })

    val solarStatus = new SolarStatus(
      batteryChargeAmps = Some("2"),
      batteryChargeWatts = Some("6"),
      batteryVolts = Some("1"),
      pvAmps = Some("5"),
      pvVolts = Some("3"),
      pvWatts = Some("4")
    )

    publisher.publish(solarStatus, "TEST")

  }

}
