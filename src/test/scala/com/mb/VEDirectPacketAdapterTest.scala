package com.mb

import com.mb.victron.{VEDirectPacketAdapter, SolarStatus}
import org.scalatest.{FlatSpec, Matchers}


class VEDirectPacketAdapterTest extends FlatSpec with Matchers {

  val vEDirectPacketAdapter: VEDirectPacketAdapter = new VEDirectPacketAdapter

  "A Normal VE Direct Packet" should "Translate to a full Solar Status" in {

    val expectedObject = new SolarStatus(
      batteryVoltage = Some(13360),
      batteryChargeCurrent = Some(2300),
      pvVoltage = Some(29180),
      pvWattage = Some(32))

    val obj = vEDirectPacketAdapter.convert(vEDirectPacket)
    obj should be (expectedObject)

  }

  val vEDirectPacket = "PID     0xA044\nFW      114\nSER#    HQ15139Q9FX\nV       13360\nI       2300\nVPV     29180\nPPV     32\nCS      3\nERR     0\nH19     1985\nH20     43\nH21     216\nH22     49\nH23     118\nChecksum        ?"

}
