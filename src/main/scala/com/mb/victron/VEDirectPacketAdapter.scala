package com.mb.victron

class VEDirectPacketAdapter {

  val BATTERY_VOLTAGE_MILLI_VOLTS = "V"
  val BATTERY_CHARGE_CURRENT_MILLI_AMPS = "I"
  val PANEL_VOLTAGE_MILLI_VOLTS = "VPV"
  val PANEL_WATTAGE = "PPV"


  val mappableDataPacketKeys = List(BATTERY_VOLTAGE_MILLI_VOLTS, BATTERY_CHARGE_CURRENT_MILLI_AMPS, PANEL_VOLTAGE_MILLI_VOLTS, PANEL_WATTAGE)


  def convert(datapacket: String): SolarStatus = {

    val mappableKvLines = convertDataPacketToKeyValue(datapacket)

    convertToSolarStatus(mappableKvLines)

  }


  private def convertToSolarStatus(mappableKvLines: Array[Array[String]]): SolarStatus = {

    mappableKvLines.foldLeft(new SolarStatus) { (acc, kv) =>

      val key = kv(0)
      val value = kv(1)

      key match {
        case BATTERY_VOLTAGE_MILLI_VOLTS =>
          acc.copy(batteryVolts = Some(OhmsLawUtil.milliVoltsToVolts(value.toDouble).toString))

        case BATTERY_CHARGE_CURRENT_MILLI_AMPS =>
          acc.copy(batteryChargeAmps =Some(OhmsLawUtil.milliVoltsToVolts(value.toDouble).toString))

        case PANEL_VOLTAGE_MILLI_VOLTS =>
          acc.copy(pvVolts = Some(OhmsLawUtil.milliVoltsToVolts(value.toDouble).toString))

        case PANEL_WATTAGE => 
          acc.copy(pvWatts = Some(value))

        case _ => throw new scala.RuntimeException("Unknown mappable. Check mapping")
      }
    }

  }



  private def convertDataPacketToKeyValue(datapacket: String): Array[Array[String]] = {

    val kvLines = datapacket.split('\n')
    kvLines
      .map(_.replaceAll("\\s+", " ")
      .split(' '))
      .filter(_.length == 2)
      .filter(kv => mappableDataPacketKeys.contains(kv(0)))

  }
}