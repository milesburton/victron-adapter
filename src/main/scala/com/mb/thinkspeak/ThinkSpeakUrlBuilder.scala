package com.mb.thinkspeak

import spray.http.Uri

case class ThinkSpeakUrlBuilder(params: Map[String, String] = Map()) {

  def withKey(v: String) = addParam("key",v)

  def withBatteryVoltage(v: Option[String]) = addParam("field1", v)

  def withBatteryChargeCurrent(v: Option[String]) = addParam("field2", v)

  def withPvVoltage(v: Option[String]) = addParam("field3", v)

  def withPvWattage(v: Option[String]) = addParam("field4", v)

  def withPvCurrent(v: Option[String]) = addParam("field5", v)

  def withBatteryChargeWattage(v: Option[String]) = addParam("field6", v)

  private def addParam(k: String, v: Option[String]):ThinkSpeakUrlBuilder = if (v.nonEmpty) addParam(k, v.get) else this

  private def addParam(k: String, v: String):ThinkSpeakUrlBuilder = this.copy(params = params + (k -> v))

  def build() = Uri("http://api.thingspeak.com/update").withQuery(params)
}
