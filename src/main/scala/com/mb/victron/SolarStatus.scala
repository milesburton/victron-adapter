package com.mb.victron


case class SolarStatus(
                        batteryVoltage: Option[Long] = None,
                        batteryChargeCurrent: Option[Long] = None,
                        pvVoltage: Option[Long] = None,
                        pvWattage: Option[Long] = None
                        )
