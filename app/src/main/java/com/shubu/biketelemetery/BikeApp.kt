package com.shubu.biketelemetery

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.shubu.biketelemetery.bluetooth.ClusterReceivedData
import com.shubu.biketelemetery.localdata.SaveData

class BikeApp : Application() {
    companion object {
        const val COLLECT_ALL_DATA = false

        private val clusterData: ClusterReceivedData = ClusterReceivedData()
        lateinit var applicationContext: Context
        var isConnected by mutableStateOf(false);
        var btMAC: String = ""
        var fuelInjectionVolumeDelta = 0.0;

        var dataType by mutableStateOf(-1)
        var userId by mutableStateOf(-1)

        var engineRPM by mutableStateOf(-1.0)
        var torque by mutableStateOf(-1)
        var speed by mutableStateOf(-1)
        var averageSpeed by mutableStateOf(-1)
        var topSpeed by mutableStateOf(-1)
        var acceleration by mutableStateOf(-1.0)
        var acceleration2 by mutableStateOf(-1.0)
        var gearPosition by mutableStateOf(-1)
        var mileage by mutableStateOf(-1)
        var fuelLevelPercentage by mutableStateOf(-1)
        var odometer by mutableStateOf(-1.0)
        var throttlePercentage by mutableStateOf(-1)
        var fuelInjectionVolume by mutableStateOf(-1.0)
        var leanAngleDegree by mutableStateOf(-1)

        var averageMileageDirect by mutableStateOf(-1)
        var cruisingRange by mutableStateOf(-1.0)
        var rangeDTE by mutableStateOf(-1)
        var batteryVoltageFrame by mutableStateOf(-1.0)
        var engineTemperatureFrame by mutableStateOf(-1)
        var engineTemperature by mutableStateOf(-1)
        var intakeAirTemperature2 by mutableStateOf(-1)
        var intakeAirTemperature by mutableStateOf(-1)
        var accumulatedFuelInjectionTime by mutableStateOf(-1.0.toFloat())
        var manifoldAirPressure by mutableStateOf(-1)
        var barometricPressure by mutableStateOf(-1)
        var fuelInjectionTime by mutableStateOf((-1.0).toFloat())

        var zeroTo60Time by mutableStateOf(-1.0)
        var currentZeroTo100Time by mutableStateOf(-1.0)
        var currentZeroTo60Time by mutableStateOf(-1.0)

        var currentRideBestTopSpeed by mutableStateOf(-1)
        var currentRideBestZeroTo60Time by mutableStateOf("")
        var currentRideBestZeroTo100Time by mutableStateOf("")
        var currentRideBestAcceleration by mutableStateOf("")
        var currentRideBestDeceleration by mutableStateOf("")
        var currentRideAverageSpeed by mutableStateOf(-1)
        var currentRideAvgInstaMileage by mutableStateOf(-1)

        var wheelieAngleOffset by mutableStateOf(-1)
        var backlightIllumination by mutableStateOf(-1)
        var switchStatus by mutableStateOf(-1)
        var clutchSwitchStatus by mutableStateOf(-1)
        var breakSwitchStatus by mutableStateOf(-1)
        var sideStandStatus by mutableStateOf(-1)
        var turnSignalLampStatus by mutableStateOf(-1)
        var engineStartedStatus by mutableStateOf(-1)
        var gearShiftIndication by mutableStateOf(-1)
        var lowBatteryStatus by mutableStateOf(-1)
        var killSwitchStatus by mutableStateOf(-1)
        var serviceReminder by mutableStateOf(-1)
        var vehicleState2 by mutableStateOf(-1)

        var milStatus by mutableStateOf(-1)
        var isgNormal by mutableStateOf(-1)
        var absNormal by mutableStateOf(-1)
        var speedoSwVersion by mutableStateOf(-1)
        var vehicleDiagnostics by mutableStateOf(-1)
        var tpsErrorStatus by mutableStateOf(-1)
        var engineTempSensorStatus by mutableStateOf(-1)
        var vehicleSpeedRearSensorErrorStatus by mutableStateOf(-1)
        var vehicleSpeedFrontSensorErrorStatus by mutableStateOf(-1)
        var intakeAirTempSensorStatus by mutableStateOf(-1)
        var tellTaleStatus by mutableStateOf(-1)
        var sideStandTellTaleStatus by mutableStateOf(-1)
        var neutralTaleStatus by mutableStateOf(-1)
        var tellLeftTaleStatus by mutableStateOf(-1)
        var tellRightTaleStatus by mutableStateOf(-1)
        var highBeamTaleStatus by mutableStateOf(-1)
        var lfiStatus by mutableStateOf(-1)
        var emsMilStatus by mutableStateOf(-1)
        var absMilStatus by mutableStateOf(-1)
        var callAcceptRejectStatus by mutableStateOf(-1)
        var electricStartSwitchStatus by mutableStateOf(-1)
        var engineSpeedSensorStatus by mutableStateOf(-1)
        var gearPositionSensorStatus by mutableStateOf(-1)
        var sideStandSensorStatus by mutableStateOf(-1)
        var canCommunicationErrorStatus by mutableStateOf(-1)
        var esSwitchStatus by mutableStateOf(-1)
        var generalWarningTellTaleStatus by mutableStateOf(-1)
        var fuelSensorFailure by mutableStateOf(-1)
        var rideMode by mutableStateOf(-1)
        var checksum2 by mutableStateOf(-1)
        var milBlinkCode by mutableStateOf(-1)
        var vehicleModel by mutableStateOf(-1)
        var vehicleModelName by mutableStateOf("")
        var iSGMilBlinkCode by mutableStateOf(-1)
        var frameNo by mutableStateOf(-1)
        var vehicleId by mutableStateOf(-1)
        var vehicleSeries by mutableStateOf("")
        var alertPositionBtnClick by mutableStateOf(-1)
        var checksum by mutableStateOf(-1)
        var vehicleState1 by mutableStateOf(-1)
        var voiceAssistInvoke by mutableStateOf(false)
        var checksum3 by mutableStateOf(-1)
        var screenMatrix by mutableStateOf(-1)
        var vehicleState3 by mutableStateOf(-1)
        var absMilBlinkCode by mutableStateOf(-1)

        var tripFMeter by mutableStateOf("")
        var tripDistance by mutableStateOf(-1.0)
        var tripTime by mutableStateOf("")
        var tripMileage by mutableStateOf(-1)
        var tripFuel by mutableStateOf(-1.0)
        var tripAAverageSpeed by mutableStateOf(-1)
        var tripBAverageSpeed by mutableStateOf(-1)
        var getTripADistance by mutableStateOf(-1.0)
        var tripAMileage by mutableStateOf(-1)
        var tripBDistance by mutableStateOf(-1.0)
        var tripBMileage by mutableStateOf(-1)
        var distanceTravelledSinceMILOn by mutableStateOf(-1.0)
        var distanceCovered by mutableStateOf(-1.0)
        var runTimeSinceEngineStart by mutableStateOf((-1.0).toFloat())
        var locationTagActive by mutableStateOf(false)


        fun getClusterData(): ClusterReceivedData {
            return clusterData
        }

        fun setClusterDataFull(receivedClusterData: ClusterReceivedData): Unit {
            if (!receivedClusterData.connected)
            {
                clusterData.connected = false;
                return;
            }
            val b = receivedClusterData.dataType
            if (b == 22) {
            } else if (b == 24) {
                clusterData.manifoldAirPressure = receivedClusterData.manifoldAirPressure
                clusterData.barometricPressure = receivedClusterData.barometricPressure
                clusterData.intakeAirTemperature = receivedClusterData.intakeAirTemperature
                clusterData.engineTemperatureFrame = receivedClusterData.engineTemperatureFrame
                clusterData.fuelInjectionTime = receivedClusterData.fuelInjectionTime
                clusterData.batteryVoltageFrame = receivedClusterData.batteryVoltageFrame
                clusterData.runTimeSinceEngineStart = receivedClusterData.runTimeSinceEngineStart
                clusterData.distanceTravelledSinceMILOn = receivedClusterData.distanceTravelledSinceMILOn
                clusterData.fuelInjectionVolume = receivedClusterData.fuelInjectionVolume
                fuelInjectionVolumeDelta = (clusterData.fuelInjectionVolume - fuelInjectionVolume).coerceAtLeast(0.0)
            } else if (b != 25) {
                when (b) {
                    16 -> {
                        clusterData.locationTagActive = receivedClusterData.locationTagActive
                        clusterData.switchStatus = receivedClusterData.switchStatus
                        clusterData.voiceAssistInvoke = receivedClusterData.voiceAssistInvoke
                        clusterData.speed = receivedClusterData.speed
                        clusterData.acceleration = receivedClusterData.acceleration
                        clusterData.odometer = receivedClusterData.odometer
                        clusterData.fuelLevelPercentage = receivedClusterData.fuelLevelPercentage
                        clusterData.averageSpeed = receivedClusterData.averageSpeed
                        clusterData.mileage = receivedClusterData.mileage
                        clusterData.topSpeed = receivedClusterData.topSpeed
                        clusterData.currentRideBestTopSpeed = receivedClusterData.currentRideBestTopSpeed
                        clusterData.throttlePercentage = receivedClusterData.throttlePercentage
                        clusterData.zeroTo60Time = receivedClusterData.zeroTo60Time
                        clusterData.tripFMeter = receivedClusterData.tripFMeter
                        clusterData.averageMileageDirect = receivedClusterData.averageMileageDirect
                        clusterData.engineRPM = receivedClusterData.engineRPM
                        clusterData.checksum = receivedClusterData.checksum
                        clusterData.callAcceptRejectStatus = receivedClusterData.callAcceptRejectStatus
                        clusterData.alertPositionBtnClick = receivedClusterData.alertPositionBtnClick
                        clusterData.currentZeroTo60Time = receivedClusterData.currentZeroTo60Time
                        clusterData.currentZeroTo100Time = receivedClusterData.currentZeroTo100Time
                        clusterData.currentRideBestZeroTo60Time = receivedClusterData.currentRideBestZeroTo60Time
                        clusterData.currentRideBestZeroTo100Time = receivedClusterData.currentRideBestZeroTo100Time
                        clusterData.currentRideBestAcceleration = receivedClusterData.currentRideBestAcceleration
                        clusterData.currentRideBestDeceleration = receivedClusterData.currentRideBestDeceleration
                        clusterData.currentRideAverageSpeed = receivedClusterData.currentRideAverageSpeed
                        clusterData.currentRideAvgInstaMileage = receivedClusterData.currentRideAvgInstaMileage
                    }

                    17 -> {
                        clusterData.vehicleState1 = receivedClusterData.vehicleState1
                        clusterData.clutchSwitchStatus = receivedClusterData.clutchSwitchStatus
                        clusterData.breakSwitchStatus = receivedClusterData.breakSwitchStatus
                        clusterData.electricStartSwitchStatus = receivedClusterData.electricStartSwitchStatus
                        clusterData.sideStandStatus = receivedClusterData.sideStandStatus
                        clusterData.engineSpeedSensorStatus = receivedClusterData.engineSpeedSensorStatus
                        clusterData.gearPositionSensorStatus = receivedClusterData.gearPositionSensorStatus
                        clusterData.sideStandSensorStatus = receivedClusterData.sideStandSensorStatus
                        clusterData.canCommunicationErrorStatus = receivedClusterData.canCommunicationErrorStatus
                        clusterData.lowBatteryStatus = receivedClusterData.lowBatteryStatus
                        clusterData.killSwitchStatus = receivedClusterData.killSwitchStatus
                        clusterData.esSwitchStatus = receivedClusterData.esSwitchStatus
                        clusterData.sideStandTellTaleStatus = receivedClusterData.sideStandTellTaleStatus
                        clusterData.generalWarningTellTaleStatus = receivedClusterData.generalWarningTellTaleStatus
                        clusterData.engineStartedStatus = receivedClusterData.engineStartedStatus
                        clusterData.gearPosition = receivedClusterData.gearPosition
                        clusterData.gearShiftIndication = receivedClusterData.gearShiftIndication
                        clusterData.serviceReminder = receivedClusterData.serviceReminder
                        clusterData.vehicleState2 = receivedClusterData.vehicleState2
                        clusterData.speedoSwVersion = receivedClusterData.speedoSwVersion
                        clusterData.vehicleDiagnostics = receivedClusterData.vehicleDiagnostics
                        clusterData.tpsErrorStatus = receivedClusterData.tpsErrorStatus
                        clusterData.engineTempSensorStatus = receivedClusterData.engineTempSensorStatus
                        clusterData.vehicleSpeedRearSensorErrorStatus = receivedClusterData.vehicleSpeedRearSensorErrorStatus
                        clusterData.vehicleSpeedFrontSensorErrorStatus = receivedClusterData.vehicleSpeedFrontSensorErrorStatus
                        clusterData.intakeAirTempSensorStatus = receivedClusterData.intakeAirTempSensorStatus
                        clusterData.milStatus = receivedClusterData.milStatus
                        clusterData.isgNormal = receivedClusterData.isgNormal
                        clusterData.absNormal = receivedClusterData.absNormal
                        clusterData.turnSignalLampStatus = receivedClusterData.turnSignalLampStatus
                        clusterData.engineTemperature = receivedClusterData.engineTemperature
                        clusterData.intakeAirTemperature2 = receivedClusterData.intakeAirTemperature2
                        clusterData.accumulatedFuelInjectionTime = receivedClusterData.accumulatedFuelInjectionTime
                        clusterData.backlightIllumination = receivedClusterData.backlightIllumination
                        clusterData.rideMode = receivedClusterData.rideMode
                        clusterData.checksum2 = receivedClusterData.checksum2
                        clusterData.fuelSensorFailure = receivedClusterData.fuelSensorFailure
                        clusterData.milBlinkCode = receivedClusterData.milBlinkCode
                        clusterData.vehicleModel = receivedClusterData.vehicleModel
                        clusterData.vehicleModelName = receivedClusterData.vehicleModelName
                        clusterData.iSGMilBlinkCode = receivedClusterData.iSGMilBlinkCode
                        clusterData.tellTaleStatus = receivedClusterData.tellTaleStatus
                        clusterData.neutralTaleStatus = receivedClusterData.neutralTaleStatus
                        clusterData.tellLeftTaleStatus = receivedClusterData.tellLeftTaleStatus
                        clusterData.tellRightTaleStatus = receivedClusterData.tellRightTaleStatus
                        clusterData.highBeamTaleStatus = receivedClusterData.highBeamTaleStatus
                        clusterData.lfiStatus = receivedClusterData.lfiStatus
                        clusterData.emsMilStatus = receivedClusterData.emsMilStatus
                        clusterData.absMilStatus = receivedClusterData.absMilStatus
                        clusterData.screenMatrix = receivedClusterData.screenMatrix
                        clusterData.vehicleState3 = receivedClusterData.vehicleState3
                        clusterData.absMilBlinkCode = receivedClusterData.absMilBlinkCode
                    }

                    18 -> {
                        clusterData.leanAngleDegree = receivedClusterData.leanAngleDegree
                        clusterData.cruisingRange = receivedClusterData.cruisingRange
                        clusterData.wheelieAngleOffset = receivedClusterData.wheelieAngleOffset
                        clusterData.acceleraation2 = receivedClusterData.acceleraation2
                        clusterData.torque = receivedClusterData.torque
                        clusterData.tripDistance = receivedClusterData.tripDistance
                        clusterData.tripTime = receivedClusterData.tripTime
                        clusterData.tripMileage = receivedClusterData.tripMileage
                        clusterData.tripFuel = receivedClusterData.tripFuel
                        clusterData.checksum3 = receivedClusterData.checksum3
                    }
                }
            } else {
                clusterData.getTripADistance = receivedClusterData.getTripADistance
                clusterData.tripAMileage = receivedClusterData.tripAMileage
                clusterData.tripBDistance = receivedClusterData.tripBDistance
                clusterData.tripBMileage = receivedClusterData.tripBMileage
                clusterData.rangeDTE = receivedClusterData.rangeDTE
                clusterData.tripAAverageSpeed = receivedClusterData.tripAAverageSpeed
                clusterData.tripBAverageSpeed = receivedClusterData.tripBAverageSpeed
                clusterData.distanceCovered = receivedClusterData.distanceCovered
            }
            updateLocalDataFull()
        }

        fun setClusterData(receivedClusterData: SaveData): Unit {
            if (!receivedClusterData.connected)
            {
                clusterData.connected = false;
                return;
            }
            val b = receivedClusterData.dataType
            if (b == 22) {
            } else if (b == 24) {
                clusterData.barometricPressure = receivedClusterData.barometricPressure
                clusterData.intakeAirTemperature = receivedClusterData.intakeAirTemperature
                clusterData.engineTemperatureFrame = receivedClusterData.engineTemperatureFrame
                clusterData.fuelInjectionTime = receivedClusterData.fuelInjectionTime
                clusterData.batteryVoltageFrame = receivedClusterData.batteryVoltageFrame
                clusterData.fuelInjectionVolume = receivedClusterData.fuelInjectionVolume
                fuelInjectionVolumeDelta = (clusterData.fuelInjectionVolume - fuelInjectionVolume).coerceAtLeast(0.0)
            } else if (b != 25) {
                when (b) {
                    16 -> {
                        clusterData.switchStatus = receivedClusterData.switchStatus
                        clusterData.speed = receivedClusterData.speed
                        clusterData.acceleration = receivedClusterData.acceleration
                        clusterData.odometer = receivedClusterData.odometer
                        clusterData.fuelLevelPercentage = receivedClusterData.fuelLevelPercentage
                        clusterData.averageSpeed = receivedClusterData.averageSpeed
                        clusterData.mileage = receivedClusterData.mileage
                        clusterData.topSpeed = receivedClusterData.topSpeed
                        clusterData.currentRideBestTopSpeed = receivedClusterData.currentRideBestTopSpeed
                        clusterData.throttlePercentage = receivedClusterData.throttlePercentage
                        clusterData.zeroTo60Time = receivedClusterData.zeroTo60Time
                        clusterData.averageMileageDirect = receivedClusterData.averageMileageDirect
                        clusterData.engineRPM = receivedClusterData.engineRPM
                        clusterData.checksum = receivedClusterData.checksum
                        clusterData.currentZeroTo60Time = receivedClusterData.currentZeroTo60Time
                        clusterData.currentZeroTo100Time = receivedClusterData.currentZeroTo100Time
                        clusterData.currentRideBestZeroTo60Time = receivedClusterData.currentRideBestZeroTo60Time
                        clusterData.currentRideBestZeroTo100Time = receivedClusterData.currentRideBestZeroTo100Time
                        clusterData.currentRideBestAcceleration = receivedClusterData.currentRideBestAcceleration
                        clusterData.currentRideBestDeceleration = receivedClusterData.currentRideBestDeceleration
                        clusterData.currentRideAverageSpeed = receivedClusterData.currentRideAverageSpeed
                    }

                    17 -> {
                        clusterData.clutchSwitchStatus = receivedClusterData.clutchSwitchStatus
                        clusterData.sideStandStatus = receivedClusterData.sideStandStatus
                        clusterData.killSwitchStatus = receivedClusterData.killSwitchStatus
                        clusterData.sideStandTellTaleStatus = receivedClusterData.sideStandTellTaleStatus
                        clusterData.engineStartedStatus = receivedClusterData.engineStartedStatus
                        clusterData.gearPosition = receivedClusterData.gearPosition
                        clusterData.gearShiftIndication = receivedClusterData.gearShiftIndication
                        clusterData.vehicleDiagnostics = receivedClusterData.vehicleDiagnostics
                        clusterData.absNormal = receivedClusterData.absNormal
                        clusterData.turnSignalLampStatus = receivedClusterData.turnSignalLampStatus
                        clusterData.engineTemperature = receivedClusterData.engineTemperature
                        clusterData.accumulatedFuelInjectionTime = receivedClusterData.accumulatedFuelInjectionTime
                        clusterData.backlightIllumination = receivedClusterData.backlightIllumination
                        clusterData.rideMode = receivedClusterData.rideMode
                        clusterData.checksum2 = receivedClusterData.checksum2
                        clusterData.vehicleModel = receivedClusterData.vehicleModel
                        clusterData.tellTaleStatus = receivedClusterData.tellTaleStatus
                        clusterData.neutralTaleStatus = receivedClusterData.neutralTaleStatus
                        clusterData.tellLeftTaleStatus = receivedClusterData.tellLeftTaleStatus
                        clusterData.tellRightTaleStatus = receivedClusterData.tellRightTaleStatus
                        clusterData.highBeamTaleStatus = receivedClusterData.highBeamTaleStatus
                        clusterData.lfiStatus = receivedClusterData.lfiStatus
                        clusterData.emsMilStatus = receivedClusterData.emsMilStatus
                        clusterData.absMilStatus = receivedClusterData.absMilStatus
                        clusterData.vehicleState3 = receivedClusterData.vehicleState3
                    }

                    18 -> {
                        clusterData.cruisingRange = receivedClusterData.cruisingRange
                        clusterData.acceleraation2 = receivedClusterData.acceleraation2
                        clusterData.checksum3 = receivedClusterData.checksum3
                    }
                }
            } else {
                clusterData.getTripADistance = receivedClusterData.getTripADistance
                clusterData.tripAMileage = receivedClusterData.tripAMileage
                clusterData.tripBDistance = receivedClusterData.tripBDistance
                clusterData.tripBMileage = receivedClusterData.tripBMileage
                clusterData.rangeDTE = receivedClusterData.rangeDTE
                clusterData.tripAAverageSpeed = receivedClusterData.tripAAverageSpeed
                clusterData.tripBAverageSpeed = receivedClusterData.tripBAverageSpeed
                clusterData.distanceCovered = receivedClusterData.distanceCovered
            }
            updateLocalData()
        }


        private fun updateLocalDataFull() {
//            isConnected = clusterData.connected
            userId = clusterData.userId
            frameNo = clusterData.frameNo
            vehicleId = clusterData.vehicleId
            vehicleSeries = clusterData.vehicleSeries
            dataType = clusterData.dataType
            manifoldAirPressure = clusterData.manifoldAirPressure
            barometricPressure = clusterData.barometricPressure
            intakeAirTemperature = clusterData.intakeAirTemperature
            engineTemperatureFrame = clusterData.engineTemperatureFrame
            fuelInjectionTime = clusterData.fuelInjectionTime
            batteryVoltageFrame = clusterData.batteryVoltageFrame
            runTimeSinceEngineStart = clusterData.runTimeSinceEngineStart
            distanceTravelledSinceMILOn = clusterData.distanceTravelledSinceMILOn
            fuelInjectionVolume = clusterData.fuelInjectionVolume
            locationTagActive = clusterData.locationTagActive
            switchStatus = clusterData.switchStatus
            voiceAssistInvoke = clusterData.voiceAssistInvoke
            speed = clusterData.speed
            acceleration = clusterData.acceleration
            odometer = clusterData.odometer
            fuelLevelPercentage = clusterData.fuelLevelPercentage
            averageSpeed = clusterData.averageSpeed
            mileage = clusterData.mileage
            topSpeed = clusterData.topSpeed
            currentRideBestTopSpeed = clusterData.currentRideBestTopSpeed
            throttlePercentage = clusterData.throttlePercentage
            zeroTo60Time = clusterData.zeroTo60Time
            tripFMeter = clusterData.tripFMeter.toString()
            averageMileageDirect = clusterData.averageMileageDirect
            engineRPM = clusterData.engineRPM
            checksum = clusterData.checksum
            callAcceptRejectStatus = clusterData.callAcceptRejectStatus
            alertPositionBtnClick = clusterData.alertPositionBtnClick
            currentZeroTo60Time = clusterData.currentZeroTo60Time
            currentZeroTo100Time = clusterData.currentZeroTo100Time
            currentRideBestZeroTo60Time = clusterData.currentRideBestZeroTo60Time.toString()
            currentRideBestZeroTo100Time = clusterData.currentRideBestZeroTo100Time.toString()
            currentRideBestAcceleration = clusterData.currentRideBestAcceleration.toString()
            currentRideBestDeceleration = clusterData.currentRideBestDeceleration.toString()
            currentRideAverageSpeed = clusterData.currentRideAverageSpeed
            currentRideAvgInstaMileage = clusterData.currentRideAvgInstaMileage
            vehicleState1 = clusterData.vehicleState1
            clutchSwitchStatus = clusterData.clutchSwitchStatus
            breakSwitchStatus = clusterData.breakSwitchStatus
            electricStartSwitchStatus = clusterData.electricStartSwitchStatus
            sideStandStatus = clusterData.sideStandStatus
            engineSpeedSensorStatus = clusterData.engineSpeedSensorStatus
            gearPositionSensorStatus = clusterData.gearPositionSensorStatus
            sideStandSensorStatus = clusterData.sideStandSensorStatus
            canCommunicationErrorStatus = clusterData.canCommunicationErrorStatus
            lowBatteryStatus = clusterData.lowBatteryStatus
            killSwitchStatus = clusterData.killSwitchStatus
            esSwitchStatus = clusterData.esSwitchStatus
            sideStandTellTaleStatus = clusterData.sideStandTellTaleStatus
            generalWarningTellTaleStatus = clusterData.generalWarningTellTaleStatus
            engineStartedStatus = clusterData.engineStartedStatus
            gearPosition = clusterData.gearPosition
            gearShiftIndication = clusterData.gearShiftIndication
            serviceReminder = clusterData.serviceReminder
            vehicleState2 = clusterData.vehicleState2
            speedoSwVersion = clusterData.speedoSwVersion
            vehicleDiagnostics = clusterData.vehicleDiagnostics
            tpsErrorStatus = clusterData.tpsErrorStatus
            engineTempSensorStatus = clusterData.engineTempSensorStatus
            vehicleSpeedRearSensorErrorStatus = clusterData.vehicleSpeedRearSensorErrorStatus
            vehicleSpeedFrontSensorErrorStatus = clusterData.vehicleSpeedFrontSensorErrorStatus
            intakeAirTempSensorStatus = clusterData.intakeAirTempSensorStatus
            milStatus = clusterData.milStatus
            isgNormal = clusterData.isgNormal
            absNormal = clusterData.absNormal
            turnSignalLampStatus = clusterData.turnSignalLampStatus
            engineTemperature = clusterData.engineTemperature
            intakeAirTemperature2 = clusterData.intakeAirTemperature2
            accumulatedFuelInjectionTime = clusterData.accumulatedFuelInjectionTime
            backlightIllumination = clusterData.backlightIllumination
            rideMode = clusterData.rideMode
            checksum2 = clusterData.checksum2
            fuelSensorFailure = clusterData.fuelSensorFailure
            milBlinkCode = clusterData.milBlinkCode
            vehicleModel = clusterData.vehicleModel
            vehicleModelName = clusterData.vehicleModelName.toString()
            iSGMilBlinkCode = clusterData.iSGMilBlinkCode
            tellTaleStatus = clusterData.tellTaleStatus
            neutralTaleStatus = clusterData.neutralTaleStatus
            tellLeftTaleStatus = clusterData.tellLeftTaleStatus
            tellRightTaleStatus = clusterData.tellRightTaleStatus
            highBeamTaleStatus = clusterData.highBeamTaleStatus
            lfiStatus = clusterData.lfiStatus
            emsMilStatus = clusterData.emsMilStatus
            absMilStatus = clusterData.absMilStatus
            screenMatrix = clusterData.screenMatrix
            vehicleState3 = clusterData.vehicleState3
            absMilBlinkCode = clusterData.absMilBlinkCode
            leanAngleDegree = clusterData.leanAngleDegree
            cruisingRange = clusterData.cruisingRange
            wheelieAngleOffset = clusterData.wheelieAngleOffset
            acceleration2 = clusterData.acceleraation2
            torque = clusterData.torque
            tripDistance = clusterData.tripDistance
            tripTime = clusterData.tripTime
            tripMileage = clusterData.tripMileage
            tripFuel = clusterData.tripFuel
            checksum3 = clusterData.checksum3
            getTripADistance = clusterData.getTripADistance
            tripAMileage = clusterData.tripAMileage
            tripBDistance = clusterData.tripBDistance
            tripBMileage = clusterData.tripBMileage
            rangeDTE = clusterData.rangeDTE
            tripAAverageSpeed = clusterData.tripAAverageSpeed
            tripBAverageSpeed = clusterData.tripBAverageSpeed
            distanceCovered = clusterData.distanceCovered
        }

        private fun updateLocalData() {
//            isConnected = clusterData.connected
            dataType = clusterData.dataType
            barometricPressure = clusterData.barometricPressure
            intakeAirTemperature = clusterData.intakeAirTemperature
            engineTemperatureFrame = clusterData.engineTemperatureFrame
            fuelInjectionTime = clusterData.fuelInjectionTime
            batteryVoltageFrame = clusterData.batteryVoltageFrame
            fuelInjectionVolume = clusterData.fuelInjectionVolume
            switchStatus = clusterData.switchStatus
            speed = clusterData.speed
            acceleration = clusterData.acceleration
            odometer = clusterData.odometer
            fuelLevelPercentage = clusterData.fuelLevelPercentage
            averageSpeed = clusterData.averageSpeed
            mileage = clusterData.mileage
            topSpeed = clusterData.topSpeed
            currentRideBestTopSpeed = clusterData.currentRideBestTopSpeed
            throttlePercentage = clusterData.throttlePercentage
            zeroTo60Time = clusterData.zeroTo60Time
            averageMileageDirect = clusterData.averageMileageDirect
            engineRPM = clusterData.engineRPM
            checksum = clusterData.checksum
            currentZeroTo60Time = clusterData.currentZeroTo60Time
            currentZeroTo100Time = clusterData.currentZeroTo100Time
            currentRideBestZeroTo60Time = clusterData.currentRideBestZeroTo60Time.toString()
            currentRideBestZeroTo100Time = clusterData.currentRideBestZeroTo100Time.toString()
            currentRideBestAcceleration = clusterData.currentRideBestAcceleration.toString()
            currentRideBestDeceleration = clusterData.currentRideBestDeceleration.toString()
            currentRideAverageSpeed = clusterData.currentRideAverageSpeed
            clutchSwitchStatus = clusterData.clutchSwitchStatus
            sideStandStatus = clusterData.sideStandStatus
            killSwitchStatus = clusterData.killSwitchStatus
            sideStandTellTaleStatus = clusterData.sideStandTellTaleStatus
            engineStartedStatus = clusterData.engineStartedStatus
            gearPosition = clusterData.gearPosition
            gearShiftIndication = clusterData.gearShiftIndication
            vehicleDiagnostics = clusterData.vehicleDiagnostics
            absNormal = clusterData.absNormal
            turnSignalLampStatus = clusterData.turnSignalLampStatus
            engineTemperature = clusterData.engineTemperature
            accumulatedFuelInjectionTime = clusterData.accumulatedFuelInjectionTime
            backlightIllumination = clusterData.backlightIllumination
            rideMode = clusterData.rideMode
            checksum2 = clusterData.checksum2
            vehicleModel = clusterData.vehicleModel
            tellTaleStatus = clusterData.tellTaleStatus
            neutralTaleStatus = clusterData.neutralTaleStatus
            tellLeftTaleStatus = clusterData.tellLeftTaleStatus
            tellRightTaleStatus = clusterData.tellRightTaleStatus
            highBeamTaleStatus = clusterData.highBeamTaleStatus
            lfiStatus = clusterData.lfiStatus
            emsMilStatus = clusterData.emsMilStatus
            absMilStatus = clusterData.absMilStatus
            vehicleState3 = clusterData.vehicleState3
            cruisingRange = clusterData.cruisingRange
            acceleration2 = clusterData.acceleraation2
            checksum3 = clusterData.checksum3
            getTripADistance = clusterData.getTripADistance
            tripAMileage = clusterData.tripAMileage
            tripBDistance = clusterData.tripBDistance
            tripBMileage = clusterData.tripBMileage
            rangeDTE = clusterData.rangeDTE
            tripAAverageSpeed = clusterData.tripAAverageSpeed
            tripBAverageSpeed = clusterData.tripBAverageSpeed
            distanceCovered = clusterData.distanceCovered
        }

    }
}