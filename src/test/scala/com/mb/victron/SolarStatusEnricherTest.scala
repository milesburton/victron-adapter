package com.mb.victron

import org.scalatest.{FlatSpec, Matchers}

class SolarStatusEnricherTest extends FlatSpec with Matchers {

  val solarStatusEnricher: SolarStatusEnricher = new SolarStatusEnricher

  "solarStatusEnricher" should "add battery wattage to solar status" in {

    val ss = new SolarStatus(
      batteryVolts = Some("13.6"),
      batteryChargeAmps = Some("2.3"))

    val expectedStatus = new SolarStatus(
      batteryVolts = Some("13.6"),
      batteryChargeAmps = Some("2.3"),
      batteryChargeWatts = Some("31.28")
    )

    val actualStatus = solarStatusEnricher.enrich(ss)
    actualStatus should be(expectedStatus)
  }

  "solarStatusEnricher" should "not add battery watter if battery voltage is missing" in {

    val ss = new SolarStatus(
      batteryChargeAmps = Some("2.3"))

    val actualStatus = solarStatusEnricher.enrich(ss)
    actualStatus should be(ss)
  }

  "solarStatusEnricher" should "add solar current to solar status" in {

    val ss = new SolarStatus(
      pvWatts = Some("31.28"),
      pvVolts = Some("13.6"))

    val expectedStatus = new SolarStatus(
      pvWatts = Some("31.28"),
      pvVolts = Some("13.6"),
      pvAmps = Some("2.3")
    )

    val actualStatus = solarStatusEnricher.enrich(ss)
    actualStatus should be(expectedStatus)
  }

  "solarStatusEnricher" should "not add solar current if pv wattage is missing" in {

    val ss = new SolarStatus()

    val actualStatus = solarStatusEnricher.enrich(ss)
    actualStatus should be(ss)
  }

}

