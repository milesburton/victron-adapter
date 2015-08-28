package com.mb.victron

class VEDirectPacketAdapter {

  val BATTERY_VOLTAGE = "V"
  val BATTERY_CHARGE_CURRENT_MILLI_AMPS = "I"
  val PANEL_VOLTAGE_MILLI_VOLTS = "VPV"
  val PANEL_WATTAGE = "PPV"


  val mappableDataPacketKeys = List(BATTERY_VOLTAGE, BATTERY_CHARGE_CURRENT_MILLI_AMPS, PANEL_VOLTAGE_MILLI_VOLTS, PANEL_WATTAGE)

  def convert(datapacket: String): SolarStatus = {

    val kvLines = datapacket.split('\n')
    val mappableKvLines = kvLines.map(_.replaceAll("\\s+", " ").split(' ')).filter(_.length == 2).filter(kv => mappableDataPacketKeys.contains(kv(0)))

    mappableKvLines.foldLeft(new SolarStatus) { (acc, kv) =>


      val key = kv(0)
      val value = kv(1)

      key match {
        case BATTERY_VOLTAGE => acc.copy(batteryVoltage = Some(value.toLong))
        case BATTERY_CHARGE_CURRENT_MILLI_AMPS => acc.copy(batteryChargeCurrent = Some(value.toLong))
        case PANEL_VOLTAGE_MILLI_VOLTS => acc.copy(pvVoltage = Some(value.toLong))
        case PANEL_WATTAGE => acc.copy(pvWattage = Some(value.toLong))
        case _ => throw new RuntimeException("Unknown mappable. Check mapping")
      }

    }


  }


}