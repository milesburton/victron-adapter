package com.mb.thinkspeak

import com.mb.victron.SolarStatus

class ThinkSpeakPublisher(requestBuilder:(scala.Predef.String) => scala.io.Source = scala.io.Source.fromURL) {

  def publish(s: SolarStatus, key: String) = {

    val url = new ThinkSpeakUrlBuilder()
      .withKey(key)
      .withBatteryChargeCurrent(s.batteryChargeAmps)
      .withBatteryVoltage(s.batteryVolts)
      .withPvWattage(s.pvWatts)
      .withPvVoltage(s.pvVolts)
      .withPvCurrent(s.pvAmps)
      .withBatteryChargeWattage(s.batteryChargeWatts)
      .build()

    println(s)
    println(url.toString())
    requestBuilder(url.toString())
  }

}
