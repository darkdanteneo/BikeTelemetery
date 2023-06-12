package com.shubu.biketelemetery.localdata

import com.shubu.biketelemetery.bluetooth.ClusterReceivedData

data class SaveData (
    //16
    var switchStatus: Int = -1,
    var speed: Int = -1,
    var acceleration: Double = -1.0,
    var odometer: Double = -1.0,
    var fuelLevelPercentage: Int = -1,
    var averageSpeed: Int = -1,
    var mileage: Int = -1,
    var topSpeed: Int = -1,
    var currentRideBestTopSpeed: Int = -1,
    var throttlePercentage: Int = -1,
    var zeroTo60Time: Double = -1.0,
    var averageMileageDirect: Int = -1,
    var engineRPM: Double = -1.0,
    var checksum: Int = -1,
    var currentZeroTo60Time: Double = -1.0,
    var currentZeroTo100Time: Double = -1.0,
    var currentRideBestZeroTo60Time: String? = "",
    var currentRideBestZeroTo100Time: String? = "",
    var currentRideBestAcceleration: String? = "",
    var currentRideBestDeceleration: String? = "",
    var currentRideAverageSpeed: Int = -1,

    //17
    var clutchSwitchStatus: Int = -1,
    var sideStandStatus: Int = -1,
    var killSwitchStatus: Int = -1,
    var sideStandTellTaleStatus: Int = -1,
    var engineStartedStatus: Int = -1,
    var gearPosition: Int = -1,
    var gearShiftIndication: Int = -1,
    var vehicleDiagnostics: Int = -1,
    var absNormal: Int = -1,
    var turnSignalLampStatus: Int = -1,
    var engineTemperature: Int = -1,
    var accumulatedFuelInjectionTime: Float = (-1.0).toFloat(),
    var backlightIllumination: Int = -1,
    var rideMode: Int = -1,
    var checksum2: Int = -1,
    var vehicleModel: Int = -1,
    var tellTaleStatus: Int = -1,
    var neutralTaleStatus: Int = -1,
    var tellLeftTaleStatus: Int = -1,
    var tellRightTaleStatus: Int = -1,
    var highBeamTaleStatus: Int = -1,
    var lfiStatus: Int = -1,
    var emsMilStatus: Int = -1,
    var absMilStatus: Int = -1,
    var vehicleState3: Int = -1,

    //18
    var cruisingRange: Double = -1.0,
    var acceleraation2: Double = -1.0,
    var checksum3: Int = -1,

    // 25
    var getTripADistance: Double = -1.0,
    var tripAMileage: Int = -1,
    var tripBDistance: Double = -1.0,
    var tripBMileage: Int = -1,
    var rangeDTE: Int = -1,
    var tripAAverageSpeed: Int = -1,
    var tripBAverageSpeed: Int = -1,
    var distanceCovered: Double = -1.0,

    ///24
    var connected: Boolean = false,
    var dataType: Int = -1,
    var barometricPressure: Int = -1,
    var intakeAirTemperature: Int = -1,
    var engineTemperatureFrame: Int = -1,
    var fuelInjectionTime: Float = (-1.0).toFloat(),
    var batteryVoltageFrame: Double = -1.0,
    var fuelInjectionVolume: Double = -1.0,

)
{
    override fun toString(): String {
        return switchStatus.toString() +  ","  + speed.toString() +  ","  + acceleration.toString() +  ","  + odometer.toString() +
                fuelLevelPercentage.toString() +  ","  + averageSpeed.toString() +  ","  + mileage.toString() +  ","  + topSpeed.toString() +
                currentRideBestTopSpeed.toString() +  ","  + throttlePercentage.toString() +  ","  + zeroTo60Time.toString() +
                averageMileageDirect.toString() +  ","  + engineRPM.toString() +  ","  + checksum.toString() +  ","  + currentZeroTo60Time.toString() +
                currentZeroTo100Time.toString() +  ","  + currentRideBestZeroTo60Time +  ","  + currentRideBestZeroTo100Time +
                currentRideBestAcceleration +  ","  + currentRideBestDeceleration +  ","  + currentRideAverageSpeed.toString() +
                clutchSwitchStatus.toString() +  ","  + sideStandStatus.toString() +  ","  + killSwitchStatus.toString() +  ","  + sideStandTellTaleStatus.toString() +
                engineStartedStatus.toString() +  ","  + gearPosition.toString() +  ","  + gearShiftIndication.toString() +  ","  + vehicleDiagnostics.toString() +
                absNormal.toString() +  ","  + turnSignalLampStatus.toString() +  ","  + engineTemperature.toString() +  ","  + accumulatedFuelInjectionTime.toString() +
                backlightIllumination.toString() +  ","  + rideMode.toString() +  ","  + checksum2.toString() +  ","  + vehicleModel.toString() +
                tellTaleStatus.toString() +  ","  + neutralTaleStatus.toString() +  ","  + tellLeftTaleStatus.toString() +  ","  + tellRightTaleStatus.toString() +
                highBeamTaleStatus.toString() +  ","  + lfiStatus.toString() +  ","  + emsMilStatus.toString() +  ","  + absMilStatus.toString() +
                vehicleState3.toString() +  ","  + cruisingRange.toString() +  ","  + acceleraation2.toString() +  ","  + checksum3.toString() +
                getTripADistance.toString() +  ","  + tripAMileage.toString() +  ","  + tripBDistance.toString() +  ","  + tripBMileage.toString() +
                rangeDTE.toString() +  ","  + tripAAverageSpeed.toString() +  ","  + tripBAverageSpeed.toString() +  ","  + distanceCovered.toString() +
                connected.toString() +  ","  + dataType.toString() +  ","  + barometricPressure.toString() +  ","  + intakeAirTemperature.toString() +
                engineTemperatureFrame.toString() +  ","  + fuelInjectionTime.toString() +  ","  + batteryVoltageFrame.toString() +  ","  + fuelInjectionVolume.toString()
    }

    companion object {
        fun getParsedData(recievedData: ByteArray?): SaveData {
            ClusterReceivedData.setReceivedData(recievedData)
            val receivedClusterData = SaveData()
            if (recievedData!![0].toInt() == 90) {
                receivedClusterData.connected = true
                val b = recievedData[1]
                receivedClusterData.dataType = b.toInt()
                if (b.toInt() == 22) {
                    /* TODO
                    setLapTrigger(getLapTrigger())
                    setLapNumber(lapNumber)
                    setBestLapTime(getBestLapTime())
                    setBestLapNumber(getBestLapNumber())
                    setLapNumberDirect(getLapNumberDirect())
                    setLapTimeDirect(getLapTimeDirect())
                    setCurrentRideTotalLaps(CurrentRideTotalLaps)
                    setCurrentRideTotalLapTime(
                        PreferenceUtils.getInstance(Utils.getAppContext())
                            .getLongValue(KEYS.U399_PREF_KEYS.key_total_lap_time, 0L)
                    )
                    setCurrentRideFastestLapTime(
                        PreferenceUtils.getInstance(Utils.getAppContext())
                            .getLongValue(KEYS.U399_PREF_KEYS.key_fastest_lap, 0L)
                    )
                    setCurrentRideBestLapNumber(
                        PreferenceUtils.getInstance(Utils.getAppContext())
                            .getIntValue(KEYS.U399_PREF_KEYS.key_fastest_lap_number, 0)
                    )
                    setCurrentRideLastLapTime(CurrentRideLastLapTime)*/
                } else if (b.toInt() == 24) {
                    receivedClusterData.barometricPressure =
                        ClusterReceivedData.getBarometric_pressure()
                    receivedClusterData.intakeAirTemperature =
                        ClusterReceivedData.getIntakeAirTemperatureFrame5()
                    receivedClusterData.engineTemperatureFrame =
                        ClusterReceivedData.setEngineTemperatureFrame5()
                    receivedClusterData.fuelInjectionTime =
                        ClusterReceivedData.getFuelInjectionTime()
                    receivedClusterData.batteryVoltageFrame =
                        ClusterReceivedData.getBatteryVoltageFrame5HV() / 10.0
                    receivedClusterData.fuelInjectionVolume =
                        ClusterReceivedData.getFuelInjectionVolume()
                } else if (b.toInt() != 25) {
                    when (b.toInt()) {
                        16 -> {
                            receivedClusterData.switchStatus =
                                ClusterReceivedData.getSwitchStatus()
                            receivedClusterData.speed =
                                ClusterReceivedData.getSpeed()
                            receivedClusterData.acceleration =
                                ClusterReceivedData.getAcceleration()
                            receivedClusterData.odometer =
                                ClusterReceivedData.getOdometer()
                            receivedClusterData.fuelLevelPercentage =
                                ClusterReceivedData.getFuelLevelPercentage()
                            receivedClusterData.averageSpeed =
                                ClusterReceivedData.getAverageSpeed()
                            receivedClusterData.mileage =
                                ClusterReceivedData.getMileage()
                            receivedClusterData.topSpeed =
                                ClusterReceivedData.getTopSpeed()
                            receivedClusterData.currentRideBestTopSpeed =
                                ClusterReceivedData.getCurrentRideBestTopSpeed()
                            receivedClusterData.throttlePercentage =
                                ClusterReceivedData.getThrottlePercentage()
                            receivedClusterData.zeroTo60Time =
                                ClusterReceivedData.getZeroTo60Time()
                            receivedClusterData.averageMileageDirect =
                                ClusterReceivedData.getAvgMilageDirect()
                            receivedClusterData.engineRPM =
                                ClusterReceivedData.getEngineRPM()
                            receivedClusterData.checksum =
                                ClusterReceivedData.getChecksum()
                            receivedClusterData.currentZeroTo60Time =
                                ClusterReceivedData.getCurrentZeroTo60Time()
                            receivedClusterData.currentZeroTo100Time =
                                ClusterReceivedData.getCurrentZeroTo100Time()
                            receivedClusterData.currentRideBestZeroTo60Time =
                                ClusterReceivedData.getCurrentRideBestZeroTo60Time()
                            receivedClusterData.currentRideBestZeroTo100Time =
                                ClusterReceivedData.getCurrentRideBestZeroTo100Time()
                            receivedClusterData.currentRideBestAcceleration =
                                ClusterReceivedData.getCurrentRideBestAcceleration(
                                    ClusterReceivedData.getAcceleration()
                                )
                            receivedClusterData.currentRideBestDeceleration =
                                ClusterReceivedData.getCurrentRideBestDeceleration(
                                    ClusterReceivedData.getAcceleration()
                                )
                            receivedClusterData.currentRideAverageSpeed =
                                ClusterReceivedData.getCurrentRideAverageSpeed()
                        }

                        17 -> {
                            receivedClusterData.clutchSwitchStatus =
                                ClusterReceivedData.getClutchSwitchStatus()
                            receivedClusterData.sideStandStatus =
                                ClusterReceivedData.getSideStandStatus()
                            receivedClusterData.killSwitchStatus =
                                ClusterReceivedData.getKillSwitchStatus()
                            receivedClusterData.sideStandTellTaleStatus =
                                ClusterReceivedData.getSideStandTellTaleStatus()
                            receivedClusterData.engineStartedStatus =
                                ClusterReceivedData.getEngineStartedStatus()
                            receivedClusterData.gearPosition =
                                ClusterReceivedData.getGearPosition()
                            receivedClusterData.gearShiftIndication =
                                ClusterReceivedData.getGearShiftIndication()
                            receivedClusterData.vehicleDiagnostics =
                                ClusterReceivedData.getVehicleDiagnostics()
                            receivedClusterData.absNormal =
                                ClusterReceivedData.getAbsNormal()
                            receivedClusterData.turnSignalLampStatus =
                                ClusterReceivedData.getTurnSignalLampStatus()
                            receivedClusterData.engineTemperature =
                                ClusterReceivedData.getEngineTemperature()
                            receivedClusterData.accumulatedFuelInjectionTime =
                                ClusterReceivedData.getAccumulatedFuelInjectionTime()
                            receivedClusterData.backlightIllumination =
                                ClusterReceivedData.getBacklightIllumination()
                            receivedClusterData.rideMode =
                                ClusterReceivedData.getRideMode()
                            receivedClusterData.checksum2 =
                                ClusterReceivedData.getChecksum()
                            receivedClusterData.vehicleModel =
                                ClusterReceivedData.getVehicleModel()
                            receivedClusterData.tellTaleStatus =
                                ClusterReceivedData.getTellTaleStatus()
                            receivedClusterData.neutralTaleStatus =
                                ClusterReceivedData.getNeutralTaleStatus()
                            receivedClusterData.tellLeftTaleStatus =
                                ClusterReceivedData.getTellLeftTaleStatus()
                            receivedClusterData.tellRightTaleStatus =
                                ClusterReceivedData.getTellRightTaleStatus()
                            receivedClusterData.highBeamTaleStatus =
                                ClusterReceivedData.getHighBeamTaleStatus()
                            receivedClusterData.lfiStatus =
                                ClusterReceivedData.getLfiStatus()
                            receivedClusterData.emsMilStatus =
                                ClusterReceivedData.getEmsMilStatus()
                            receivedClusterData.absMilStatus =
                                ClusterReceivedData.getAbsMilStatus()
                            receivedClusterData.vehicleState3 =
                                ClusterReceivedData.getVehicleState3()
                        }

                        18 -> {
                            receivedClusterData.cruisingRange =
                                ClusterReceivedData.getCruisingRange()
                            receivedClusterData.acceleraation2 =
                                ClusterReceivedData.getAcceleraation2()
                            receivedClusterData.checksum3 =
                                ClusterReceivedData.getChecksum()
                        }
                    }
                } else {
                    receivedClusterData.getTripADistance =
                        ClusterReceivedData.getTripADistance()
                    receivedClusterData.tripAMileage =
                        ClusterReceivedData.getTripAMileage()
                    receivedClusterData.tripBDistance =
                        ClusterReceivedData.getTripBDistance() / 10.0
                    receivedClusterData.tripBMileage =
                        ClusterReceivedData.getTripBMileage()
                    receivedClusterData.rangeDTE =
                        ClusterReceivedData.getRangeDTE()
                    receivedClusterData.tripAAverageSpeed =
                        ClusterReceivedData.getTripAAverageSpeed()
                    receivedClusterData.tripBAverageSpeed =
                        ClusterReceivedData.getTripBAverageSpeed()
                    receivedClusterData.distanceCovered =
                        ClusterReceivedData.getDistanceCovered()
                }
            }
            return receivedClusterData
        }
        fun getCSVHeader(): String {
            return "switchStatus" + "," + "speed" + "," + "acceleration" + "," + "odometer" + "," +
                    "fuelLevelPercentage" + "," + "averageSpeed" + "," + "mileage" + "," + "topSpeed" + "," +
                    "currentRideBestTopSpeed" + "," + "throttlePercentage" + "," + "zeroTo60Time" + "," +
                    "averageMileageDirect" + "," + "engineRPM" + "," + "checksum" + "," + "currentZeroTo60Time" + "," +
                    "currentZeroTo100Time" + "," + "currentRideBestZeroTo60Time" +  ","  + "currentRideBestZeroTo100Time" +
                    "currentRideBestAcceleration" +  ","  + "currentRideBestDeceleration" +  ","  + "currentRideAverageSpeed" + "," +
                    "clutchSwitchStatus" + "," + "sideStandStatus" + "," + "killSwitchStatus" + "," + "sideStandTellTaleStatus" + "," +
                    "engineStartedStatus" + "," + "gearPosition" + "," + "gearShiftIndication" + "," + "vehicleDiagnostics" + "," +
                    "absNormal" + "," + "turnSignalLampStatus" + "," + "engineTemperature" + "," + "accumulatedFuelInjectionTime" + "," +
                    "backlightIllumination" + "," + "rideMode" + "," + "checksum2" + "," + "vehicleModel" + "," +
                    "tellTaleStatus" + "," + "neutralTaleStatus" + "," + "tellLeftTaleStatus" + "," + "tellRightTaleStatus" + "," +
                    "highBeamTaleStatus" + "," + "lfiStatus" + "," + "emsMilStatus" + "," + "absMilStatus" + "," +
                    "vehicleState3" + "," + "cruisingRange" + "," + "acceleraation2" + "," + "checksum3" + "," +
                    "getTripADistance" + "," + "tripAMileage" + "," + "tripBDistance" + "," + "tripBMileage" + "," +
                    "rangeDTE" + "," + "tripAAverageSpeed" + "," + "tripBAverageSpeed" + "," + "distanceCovered" + "," +
                    "connected" + "," + "dataType" + "," + "barometricPressure" + "," + "intakeAirTemperature" + "," +
                    "engineTemperatureFrame" + "," + "fuelInjectionTime" + "," + "batteryVoltageFrame" + "," + "fuelInjectionVolume"
        }
    }
}