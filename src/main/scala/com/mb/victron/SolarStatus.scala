package com.mb.victron


case class SolarStatus(
                        batteryChargeWatts: Option[String] = None,
                        batteryVolts: Option[String] = None,
                        batteryChargeAmps: Option[String] = None,
                        pvWatts: Option[String] = None,
                        pvVolts: Option[String] = None,
                        pvAmps: Option[String] = None
                        )
