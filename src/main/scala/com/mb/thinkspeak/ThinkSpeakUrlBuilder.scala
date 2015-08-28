package com.mb.thinkspeak

class ThinkSpeakUrlBuilder {

  var queryString = ""

  def withKey(v: String) = {
    queryString = queryString + "key=" + v + "&"
    this
  }

  def withBatteryVoltage(v: Option[Long]) = {
    v match {
      case Some(l) =>
        queryString = queryString + "field1=" + l + "&"
      case _ =>
    }
    this
  }

  def withBatteryChargeCurrent(v: Option[Long]) = {
    v match {
      case Some(l) =>
        queryString = queryString + "field2=" + l + "&"
      case _ =>
    }
    this
  }

  def WithPvVoltage(v: Option[Long]) = {
    v match {
      case Some(l) =>
        queryString = queryString + "field3=" + l + "&"
      case _ =>
    }
    this
  }

  def withPvWattage(v: Option[Long]) = {
    v match {
      case Some(l) =>
        queryString = queryString + "field4=" + l + "&"
      case _ =>
    }
    this
  }

  def build() = {
    "http://api.thingspeak.com/update?" + queryString
  }
}
