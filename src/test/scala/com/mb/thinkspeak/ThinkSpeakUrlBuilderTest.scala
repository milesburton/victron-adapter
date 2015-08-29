package com.mb.thinkspeak

import org.scalatest.{FlatSpec, Matchers}


class ThinkSpeakUrlBuilderTest extends FlatSpec with Matchers {

  val thinkSpeakUrlBuilder: ThinkSpeakUrlBuilder = new ThinkSpeakUrlBuilder

  "builder" should "add key to url" in {

    val url = thinkSpeakUrlBuilder
      .withKey("TEST")
      .build().toString()

      url should be ("http://api.thingspeak.com/update?key=TEST")
  }

  "builder" should "add battery voltage to url" in {

    val url = thinkSpeakUrlBuilder
      .withBatteryVoltage(Some("1"))
      .build().toString()

    url should be ("http://api.thingspeak.com/update?field1=1")
  }

  "builder" should "does not add battery voltage to url if empty" in {

    val url = thinkSpeakUrlBuilder
      .withBatteryVoltage(None)
      .build().toString()

    url should be ("http://api.thingspeak.com/update")
  }

  "builder" should "add pv voltage to url" in {

    val url = thinkSpeakUrlBuilder
      .withPvVoltage(Some("3"))
      .build().toString()

    url should be ("http://api.thingspeak.com/update?field3=3")
  }

  "builder" should "does not add pv to url if empty" in {

    val url = thinkSpeakUrlBuilder
      .withPvVoltage(None)
      .build().toString()

    url should be ("http://api.thingspeak.com/update")
  }

  "builder" should "add pv wattage to url" in {

    val url = thinkSpeakUrlBuilder
      .withPvWattage(Some("4"))
      .build().toString()

    url should be ("http://api.thingspeak.com/update?field4=4")
  }

  "builder" should "does not add pv wattage to url if empty" in {

    val url = thinkSpeakUrlBuilder
      .withPvWattage(None)
      .build().toString()

    url should be ("http://api.thingspeak.com/update")
  }

  "builder" should "add battery charge current to url" in {

    val url = thinkSpeakUrlBuilder
      .withBatteryChargeCurrent(Some("2"))
      .build().toString()

    url should be ("http://api.thingspeak.com/update?field2=2")
  }

  "builder" should "does not add battery charge current to url if empty" in {

    val url = thinkSpeakUrlBuilder
      .withBatteryChargeCurrent(None)
      .build().toString()

    url should be ("http://api.thingspeak.com/update")
  }


  "builder" should "add battery charge wattage to url" in {

    val url = thinkSpeakUrlBuilder
      .withBatteryChargeWattage(Some("6"))
      .build().toString()

    url should be ("http://api.thingspeak.com/update?field6=6")
  }

  "builder" should "does not add battery charge wattage to url if empty" in {

    val url = thinkSpeakUrlBuilder
      .withBatteryChargeWattage(None)
      .build().toString()

    url should be ("http://api.thingspeak.com/update")
  }

  "builder" should "add pv current to url" in {

    val url = thinkSpeakUrlBuilder
      .withPvCurrent(Some("5"))
      .build().toString()

    url should be ("http://api.thingspeak.com/update?field5=5")
  }

  "builder" should "does not add pv current e to url if empty" in {

    val url = thinkSpeakUrlBuilder
      .withPvCurrent(None)
      .build().toString()

    url should be ("http://api.thingspeak.com/update")
  }

}
