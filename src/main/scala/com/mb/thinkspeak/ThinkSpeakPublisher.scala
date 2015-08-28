package com.mb.thinkspeak

import com.mb.victron.SolarStatus

class ThinkSpeakPublisher {

  def publish(s: SolarStatus, key: String) = {

    val url = new ThinkSpeakUrlBuilder()
      .withKey(key)
      .withBatteryChargeCurrent(s.batteryChargeCurrent)
      .withBatteryVoltage(s.batteryVoltage)
      .withPvWattage(s.pvWattage)
      .WithPvVoltage(s.pvVoltage)
      .build()

    scala.io.Source.fromURL(url)
  }

}
