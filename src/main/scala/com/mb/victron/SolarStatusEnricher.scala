package com.mb.victron


class SolarStatusEnricher {

  def enrich(s: SolarStatus): SolarStatus = {
    calculatePvCurrent(calculateBatteryWattage(s))
  }

  private def calculateBatteryWattage(solarStatus: SolarStatus): SolarStatus = {

    solarStatus match {
      case SolarStatus(_, Some(_), Some(_), _, _, _) =>

        val watts = OhmsLawUtil.toWatts(solarStatus.batteryVolts.get.toDouble,solarStatus.batteryChargeAmps.get.toDouble)
        solarStatus.copy(batteryChargeWatts = Some(watts.toString))

      case _ => solarStatus
    }

  }

  private def calculatePvCurrent(solarStatus: SolarStatus): SolarStatus = {

    solarStatus match {
      case SolarStatus(_, _, _, Some(_), Some(_), _) =>

        var current = OhmsLawUtil.toAmps(solarStatus.pvWatts.get.toDouble, solarStatus.pvVolts.get.toDouble)
        solarStatus.copy(pvAmps = Some(current.toString))

      case _ => solarStatus
    }

  }
}
