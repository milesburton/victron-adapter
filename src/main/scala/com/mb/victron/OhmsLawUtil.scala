package com.mb.victron


object OhmsLawUtil {
  def milliVoltsToVolts(mV: Double): Double = round(mV / 1000)

  def toAmps(watts: Double, volts: Double) = round(watts / volts)

  def toWatts(volts: Double, amps: Double) = round(volts * amps)

  private def round(v: Double) = BigDecimal(v).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
}
