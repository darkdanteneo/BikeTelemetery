package com.shubu.biketelemetery.bluetooth

import android.util.Log
import java.math.BigInteger
import java.text.DecimalFormat
import java.util.Date
import java.util.Locale
import kotlin.math.pow

data class ClusterReceivedData(
    // 24
    var connected: Boolean = false,
    var userId: Int = -1,
    var frameNo: Int = -1,
    var vehicleId: Int = -1,
    var vehicleSeries: String = "",
    var dataType: Int = -1,
    var manifoldAirPressure: Int = -1,
    var barometricPressure: Int = -1,
    var intakeAirTemperature: Int = -1,
    var engineTemperatureFrame: Int = -1,
    var fuelInjectionTime: Float = (-1.0).toFloat(),
    var batteryVoltageFrame: Double = -1.0,
    var runTimeSinceEngineStart: Float = (-1.0).toFloat(),
    var distanceTravelledSinceMILOn: Double = -1.0,
    var fuelInjectionVolume: Double = -1.0,

    // 16
    var locationTagActive: Boolean = false,
    var switchStatus: Int = -1,
    var voiceAssistInvoke: Boolean = false,
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
    var tripFMeter: String? = "",
    var averageMileageDirect: Int = -1,
    var engineRPM: Double = -1.0,
    var checksum: Int = -1,
    var callAcceptRejectStatus: Int = -1,
    var alertPositionBtnClick: Int = -1,
    var currentZeroTo60Time: Double = -1.0,
    var currentZeroTo100Time: Double = -1.0,
    var currentRideBestZeroTo60Time: String? = "",
    var currentRideBestZeroTo100Time: String? = "",
    var currentRideBestAcceleration: String? = "",
    var currentRideBestDeceleration: String? = "",
    var currentRideAverageSpeed: Int = -1,
    var currentRideAvgInstantMileage: Int = -1,

    // 17
    var vehicleState1: Int = -1,
    var clutchSwitchStatus: Int = -1,
    var breakSwitchStatus: Int = -1,
    var electricStartSwitchStatus: Int = -1,
    var sideStandStatus: Int = -1,
    var engineSpeedSensorStatus: Int = -1,
    var gearPositionSensorStatus: Int = -1,
    var sideStandSensorStatus: Int = -1,
    var canCommunicationErrorStatus: Int = -1,
    var lowBatteryStatus: Int = -1,
    var killSwitchStatus: Int = -1,
    var esSwitchStatus: Int = -1,
    var sideStandTellTaleStatus: Int = -1,
    var generalWarningTellTaleStatus: Int = -1,
    var engineStartedStatus: Int = -1,
    var gearPosition: Int = -1,
    var gearShiftIndication: Int = -1,
    var serviceReminder: Int = -1,
    var vehicleState2: Int = -1,
    var speedometerSwVersion: Int = -1,
    var vehicleDiagnostics: Int = -1,
    var tpsErrorStatus: Int = -1,
    var engineTempSensorStatus: Int = -1,
    var vehicleSpeedRearSensorErrorStatus: Int = -1,
    var vehicleSpeedFrontSensorErrorStatus: Int = -1,
    var intakeAirTempSensorStatus: Int = -1,
    var milStatus: Int = -1,
    var isgNormal: Int = -1,
    var absNormal: Int = -1,
    var turnSignalLampStatus: Int = -1,
    var engineTemperature: Int = -1,
    var intakeAirTemperature2: Int = -1,
    var accumulatedFuelInjectionTime: Float = (-1.0).toFloat(),
    var backlightIllumination: Int = -1,
    var rideMode: Int = -1,
    var checksum2: Int = -1,
    var fuelSensorFailure: Int = -1,
    var milBlinkCode: Int = -1,
    var vehicleModel: Int = -1,
    var vehicleModelName: String? = "",
    var iSGMilBlinkCode: Int = -1,
    var tellTaleStatus: Int = -1,
    var neutralTaleStatus: Int = -1,
    var tellLeftTaleStatus: Int = -1,
    var tellRightTaleStatus: Int = -1,
    var highBeamTaleStatus: Int = -1,
    var lfiStatus: Int = -1,
    var emsMilStatus: Int = -1,
    var absMilStatus: Int = -1,
    var screenMatrix: Int = -1,
    var vehicleState3: Int = -1,
    var absMilBlinkCode: Int = -1,

    // 18
    var leanAngleDegree: Int = -1,
    var cruisingRange: Double = -1.0,
    var wheelieAngleOffset: Int = -1,
    var acceleration2: Double = -1.0,
    var torque: Int = -1,
    var tripDistance: Double = -1.0,
    var tripTime: String = "",
    var tripMileage: Int = -1,
    var tripFuel: Double = -1.0,
    var checksum3: Int = -1,

    // 25
    var getTripADistance: Double = -1.0,
    var tripAMileage: Int = -1,
    var tripBDistance: Double = -1.0,
    var tripBMileage: Int = -1,
    var rangeDTE: Int = -1,
    var tripAAverageSpeed: Int = -1,
    var tripBAverageSpeed: Int = -1,
    var distanceCovered: Double = -1.0
)
{
    override fun toString(): String {
        return connected.toString() + "," + userId.toString() + "," + frameNo.toString() + "," + vehicleId.toString() + "," + vehicleSeries.toString() + "," +
                dataType.toString() + "," + manifoldAirPressure.toString() + "," + barometricPressure.toString() + "," + intakeAirTemperature.toString() + "," +
                engineTemperatureFrame.toString() + "," + fuelInjectionTime.toString() + "," + batteryVoltageFrame.toString() + "," + runTimeSinceEngineStart.toString() + "," +
                distanceTravelledSinceMILOn.toString() + "," + fuelInjectionVolume.toString() + "," + locationTagActive.toString() + "," + switchStatus.toString() + "," +
                voiceAssistInvoke.toString() + "," + speed.toString() + "," + acceleration.toString() + "," + odometer.toString() + "," + fuelLevelPercentage.toString() + "," +
                averageSpeed.toString() + "," + mileage.toString() + "," + topSpeed.toString() + "," + currentRideBestTopSpeed.toString() + "," + throttlePercentage.toString() + "," +
                zeroTo60Time.toString() + "," + tripFMeter.toString() + "," + averageMileageDirect.toString() + "," + engineRPM.toString() + "," + checksum.toString() + "," +
                callAcceptRejectStatus.toString() + "," + alertPositionBtnClick.toString() + "," + currentZeroTo60Time.toString() + "," + currentZeroTo100Time.toString() + "," +
                currentRideBestZeroTo60Time.toString() + "," + currentRideBestZeroTo100Time.toString() + "," + currentRideBestAcceleration.toString() + "," +
                currentRideBestDeceleration.toString() + "," + currentRideAverageSpeed.toString() + "," + currentRideAvgInstantMileage.toString() + "," +
                vehicleState1.toString() + "," + clutchSwitchStatus.toString() + "," + breakSwitchStatus.toString() + "," + electricStartSwitchStatus.toString() + "," +
                sideStandStatus.toString() + "," + engineSpeedSensorStatus.toString() + "," + gearPositionSensorStatus.toString() + "," + sideStandSensorStatus.toString() + "," +
                canCommunicationErrorStatus.toString() + "," + lowBatteryStatus.toString() + "," + killSwitchStatus.toString() + "," + esSwitchStatus.toString() + "," +
                sideStandTellTaleStatus.toString() + "," + generalWarningTellTaleStatus.toString() + "," + engineStartedStatus.toString() + "," + gearPosition.toString() + "," +
                gearShiftIndication.toString() + "," + serviceReminder.toString() + "," + vehicleState2.toString() + "," + speedometerSwVersion.toString() + "," +
                vehicleDiagnostics.toString() + "," + tpsErrorStatus.toString() + "," + engineTempSensorStatus.toString() + "," + vehicleSpeedRearSensorErrorStatus.toString() + "," +
                vehicleSpeedFrontSensorErrorStatus.toString() + "," + intakeAirTempSensorStatus.toString() + "," + milStatus.toString() + "," + isgNormal.toString() + "," +
                absNormal.toString() + "," + turnSignalLampStatus.toString() + "," + engineTemperature.toString() + "," + intakeAirTemperature2.toString() + "," +
                accumulatedFuelInjectionTime.toString() + "," + backlightIllumination.toString() + "," + rideMode.toString() + "," + checksum2.toString() + "," +
                fuelSensorFailure.toString() + "," + milBlinkCode.toString() + "," + vehicleModel.toString() + "," + vehicleModelName.toString() + "," + iSGMilBlinkCode.toString() + "," +
                tellTaleStatus.toString() + "," + neutralTaleStatus.toString() + "," + tellLeftTaleStatus.toString() + "," + tellRightTaleStatus.toString() + "," +
                highBeamTaleStatus.toString() + "," + lfiStatus.toString() + "," + emsMilStatus.toString() + "," + absMilStatus.toString() + "," + screenMatrix.toString() + "," +
                vehicleState3.toString() + "," + absMilBlinkCode.toString() + "," + leanAngleDegree.toString() + "," + cruisingRange.toString() + "," + wheelieAngleOffset.toString() + "," +
                acceleration2.toString() + "," + torque.toString() + "," + tripDistance.toString() + "," + tripTime.toString() + "," + tripMileage.toString() + "," +
                tripFuel.toString() + "," + checksum3.toString() + "," + getTripADistance.toString() + "," + tripAMileage.toString() + "," + tripBDistance.toString() + "," +
                tripBMileage.toString() + "," + rangeDTE.toString() + "," + tripAAverageSpeed.toString() + "," + tripBAverageSpeed.toString() + "," + distanceCovered.toString()
    }

    companion object {
        private var averageSpeedCount = 1
        private var currRideAvgSpeed = 0
        private var localReceivedData: ByteArray? = null
        private var rideOdometer = 0.0
        private var odometer = 0.0
        private var rawAcceleration = 0.0
        private var local_time: Long = 0
        private var local_speed = 0
        private var currentRideBestAcceleration = 0.0
        private var currentRidBestDeceleration = 0.0
        private var currentRideBestZeroTo100Time = 0.0
        private var currentRideBestZeroTo60Time = 0.0
        private var start_time: Long = 0
        private var currentRideTopSPeed = 0
        private var key_ride_mileage_sum = 0
        private var key_ride_mileage_count = 0
        private var key_current_ride_avg_instant_mileage = 0
        private var zeroTo60Achieved = 0.0
        private var zeroTo100Achieved = 0.0
        private var isRideOnGoing = false
        private var isRideOnGoing2 = false
        private var currentZeroTo100Time = -1.0
        private var currentZeroTo60Time = -1.0
        private var mileage = 0
        private var rideMode = 0
        private var distanceCovered = 0.0
        private var distanceCoveredLocal = 0.0
        private var distanceCoveredStarted = false
        private val df2: DecimalFormat = DecimalFormat("###.####")
        private val decimalFormat3 = DecimalFormat("###.###")

        private fun reverseString(str: String): String {
            var str2 = ""
            for (length in str.length - 1 downTo 0) {
                str2 += str[length]
            }
            return str2
        }

        fun getThrottlePercentage(): Int {
            return (localReceivedData!![10].toInt() and 255) / 2
        }

        private fun getEngineTempRaw(str: String): Int {
            val reverseString: String =
                reverseString(str.uppercase(Locale.getDefault()).trim { it <= ' ' })
            var i = 0
            for (i2 in reverseString.indices) {
                i = (i + 16.0.pow(i2.toDouble()) * "0123456789ABCDEF".indexOf(reverseString[i2])).toInt()
            }
            return i
        }

        fun getTopSpeed(): Int {
            return localReceivedData!![9].toInt() and 255
        }

        private fun getAccelerationRaw(): Double {
            val speed = getSpeed()
            if(local_time == 0L)
            {
                local_time = System.currentTimeMillis()
                local_speed = 0
            }
            if (speed == local_speed) {
                rawAcceleration = 0.0
                return rawAcceleration
            }
            else {
                val time = System.currentTimeMillis()
                computeRawAcceleration(
                    decimalFormat3.format(speed).toDouble(),
                    decimalFormat3.format(local_speed).toDouble(),
                    time,
                    local_time
                )
                local_speed = speed
                local_time = time
            }
            return rawAcceleration
        }

        private fun computeRawAcceleration(speed: Double, localSpeed: Double, time: Long, localTime: Long) {
            val deltaTime = ((time - localTime) / 10000).toDouble()
            val deltaAcceleration = (speed - localSpeed) / (deltaTime * 3.6)
            rawAcceleration = deltaAcceleration
        }

        private fun getTorque(): Int {
            return localReceivedData!![7].toInt() and 255
        }

        private fun getDistanceTravelledSinceMILOn(): Double {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![14], bArr[15])).toDouble()
        }

        private fun getTpsErrorStatus(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 1)
        }

        private fun getElectricStartSwitchStatus(): Int {
            return getStatus(localReceivedData!![3].toInt() and 255, 5)
        }

        fun getTripAAverageSpeed(): Int {
            return localReceivedData!![13].toInt() and 255
        }

        fun getEmsMilStatus(): Int {
            return getStatus(localReceivedData!![13].toInt() and 255, 5)
        }

        fun getTripADistance(): Double {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![5], bArr[6])).toDouble() / 10.0
        }

        fun getEngineRPM(): Double {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![16], bArr[17])).toDouble()
        }

        fun getTripAMileage(): Int {
            return localReceivedData!![7].toInt() and 255
        }

        private fun getEngineSpeedSensorStatus(): Int {
            return getStatus(localReceivedData!![2].toInt() and 255, 3)
        }

        fun getTripBAverageSpeed(): Int {
            return localReceivedData!![14].toInt() and 255
        }

        fun getEngineStartedStatus(): Int {
            return getStatus(localReceivedData!![15].toInt() and 255, 7)
        }

        fun getTripBDistance(): Double {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![8], bArr[9])).toDouble()
        }

        private fun getEngineTempSensorStatus(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 3)
        }

        fun getTripBMileage(): Int {
            return localReceivedData!![10].toInt() and 255
        }

        fun setEngineTemperatureFrame5(): Int {
            return (localReceivedData!![8].toInt() and 255) - 40
        }

        private fun getTripDistanceHM(): Double {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![8], bArr[9])).toDouble()
        }

        fun getEngineTemperature(): Int {
            return getEngineTempRaw(
                String.format(
                    "%02x",
                    java.lang.Byte.valueOf(localReceivedData!![13])
                )
            ) - 25
        }

        fun getDistanceCovered(): Double {
            if (getTripADistance() >= 0.0 && getTripADistance() < distanceCovered || !distanceCoveredStarted) {
                distanceCovered = getTripADistance()
            }
            if (!distanceCoveredStarted) {
                distanceCoveredStarted = true
            }
            distanceCoveredLocal += getTripADistance() - distanceCovered
            distanceCovered = getTripADistance()
            return distanceCoveredLocal
        }

        private fun getEsSwitchStatus(): Int {
            return getStatus(localReceivedData!![6].toInt() and 255, 6)
        }

        private fun getTripFMeter(): String {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![13], bArr[14], bArr[15])).toString()
        }

        private fun getTripFuel(): Double {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![13], bArr[14])).toDouble()
        }

        fun getFuelInjectionTime(): Float {
            val bArr = localReceivedData
            return ((BigInteger(byteArrayOf(bArr!![9], bArr[10])).divide(BigInteger.valueOf(100L)))
                .toFloat() * 0.001).toFloat()
        }

        private fun getTripMileage(): Int {
            return localReceivedData!![12].toInt() and 255
        }

        fun getFuelInjectionVolume(): Double {
            val bArr = localReceivedData
            return (bArr!![17].toInt() and 255 or (bArr[16].toInt() and 255 shl 8)).toDouble()
        }

        private fun getTripTime(): String {
            val bArr = localReceivedData
            return (bArr!![10].toInt() and 255).toString() + ":" + (bArr[11].toInt() and 255)
        }

        fun getFuelLevelPercentage(): Int {
            return localReceivedData!![6].toInt() and 255
        }

        fun getTurnSignalLampStatus(): Int {
            val i: Int = localReceivedData!![13].toInt() and 255
            val leftTurnStatus = getStatus(i, 2)
            val rightTurnStatus = getStatus(i, 3)
            val i2 = if (rightTurnStatus != 1) if (leftTurnStatus == 1) 1 else 0 else 2
            return if (leftTurnStatus == 1 && rightTurnStatus == 1) {
                3
            } else i2
        }

        private fun getFuelSensorFailure(): Int {
            return localReceivedData!![2].toInt() and 255
        }

        fun getVehicleDiagnostics(): Int {
            return localReceivedData!![10].toInt() and 255
        }

        fun getGearPosition(): Int {
            return try {
                String.format("%02X", Integer.valueOf(localReceivedData!![5].toInt() and 255))[1].toString().toInt()
            } catch (unused: Exception) {
                -1
            }
        }

        private fun getVehicleSpeedFrontSensorErrorStatus(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 4)
        }

        private fun getGearPositionSensorStatus(): Int {
            return getStatus(localReceivedData!![2].toInt() and 255, 4)
        }

        private fun getVehicleSpeedRearSensorErrorStatus(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 2)
        }

        private fun getGeneralWarningTellTaleStatus(): Int {
            return getStatus(localReceivedData!![15].toInt() and 255, 6)
        }

        private fun getVehicleState1(): Int {
            return localReceivedData!![3].toInt() and 255
        }

        private fun getVehicleState2(): Int {
            return localReceivedData!![6].toInt() and 255
        }

        fun getVehicleState3(): Int {
            return localReceivedData!![15].toInt() and 255
        }

        fun getHighBeamTaleStatus(): Int {
            return getStatus(localReceivedData!![13].toInt() and 255, 4)
        }

        private fun getIntakeAirTempSensorStatus(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 5)
        }

        private fun getIntakeAirTemperature(): Int {
            return localReceivedData!![14].toInt() and 255
        }

        private fun getVoiceAssistInvoke(): Boolean {
            return localReceivedData!![11].toInt() and 4 == 4
        }

        fun getIntakeAirTemperatureFrame5(): Int {
            return (localReceivedData!![7].toInt() and 255) - 40
        }

        private fun getWheelieAngleOffset(): Int {
            return localReceivedData!![5].toInt() and 255
        }

        private fun getIsgNormal(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 7)
        }

        fun getZeroTo60Time(): Double {
            return df2.format((localReceivedData!![12].toInt() and 255) / 10.0).toDouble()
        }

        private fun getStatus(i: Int, i2: Int): Int {
            String.format("%8s", Integer.toBinaryString(i and 255)).replace(' ', '0')
            return i shr i2 - 1 and 1
        }

        fun getKillSwitchStatus(): Int {
            return getStatus(localReceivedData!![6].toInt() and 255, 1)
        }

        private fun getAlertPositiveBtnClick(): Int {
            return localReceivedData!![11].toInt() and 255
        }

        private fun getLocationTagActive(): Boolean {
            return localReceivedData!![11].toInt() and 128 == 128
        }

        private fun getCallAcceptRejectStatus(): Int {
            val b = localReceivedData!![11]
            /* TODO*/
            return b.toInt()
        }

        private fun getAbsMilBlinkCode(): Int {
            return localReceivedData!![16].toInt() and 255
        }

        fun getCurrentRideBestTopSpeed(): Int {
            if (getSpeed() > currentRideTopSPeed) {
                currentRideTopSPeed = getSpeed()
            }
            return currentRideTopSPeed
        }

        fun getAbsMilStatus(): Int {
            return getStatus(localReceivedData!![13].toInt() and 255, 6)
        }

        private fun getLeanAngleDegree(): Int {
            return localReceivedData!![2].toInt() and 255
        }

        fun getAbsNormal(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 8)
        }

        fun getLfiStatus(): Int {
            return getStatus(localReceivedData!![13].toInt() and 255, 8)
        }

        fun getAcceleration(): Double {
            return decimalFormat3.format(getAccelerationRaw()).toDouble()
        }

        private fun getLowBatteryStatus(): Int {
            return getStatus(localReceivedData!![2].toInt() and 255, 7)
        }


        private fun getIllumination(b: Byte): Int {
            return b.toInt() and 240 shr 4
        }

        private fun getRideMode(b: Byte): Int {
            return b.toInt() and 15
        }

        fun getAcceleration2(): Double {
            return localReceivedData!![6].toDouble()
        }

        private fun getManifoldAirPressure(): Int {
            return localReceivedData!![5].toInt() and 255
        }

        fun getCurrentRideBestAcceleration(d: Double): String {
            if (d >= 0.0 && d > currentRideBestAcceleration) {
                currentRideBestAcceleration = d
            }
            return "$currentRideBestAcceleration g"
        }

        fun getAccumulatedFuelInjectionTime(): Float {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![15], bArr[16])).divide(BigInteger.valueOf(100L))
                .toFloat()
        }

        private fun getMilStatus(): Int {
            return getStatus(localReceivedData!![10].toInt() and 255, 6)
        }

        fun getCurrentRideBestDeceleration(d: Double): String {
            if (d <= 0.0 && d < currentRidBestDeceleration) {
                currentRidBestDeceleration = d
            }
            return "$currentRidBestDeceleration g"
        }

        fun getAverageSpeed(): Int {
            return localReceivedData!![7].toInt() and 255
        }

        fun getMileage(): Int {
            if (getSpeed() > 0) {
                mileage = localReceivedData!![8].toInt() and 255
            }
            return mileage
        }

        fun getAvgMileageDirect(): Int {
            return localReceivedData!![13].toInt() and 255
        }

        fun getNeutralTaleStatus(): Int {
            return getStatus(localReceivedData!![13].toInt() and 255, 1)
        }

        fun getBacklightIllumination(): Int {
            return getIllumination(localReceivedData!![17])
        }

        fun getOdometer(): Double {
            val bArr = localReceivedData
            val odoValue = (BigInteger(byteArrayOf(bArr!![3], bArr[4], bArr[5])).toDouble() / 10.0)
            odometer = odoValue
            if (!isRideOnGoing) {
                isRideOnGoing = true
                rideOdometer = odoValue
            }
            return odoValue
        }

        fun getBarometricPressure(): Int {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![9], bArr[10])).toInt()
        }

        fun getRangeDTE(): Int {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![11], bArr[12])).toInt()
        }

        fun getBatteryVoltageFrame5HV(): Double {
            return (localReceivedData!![11].toInt() and 255).toDouble()
        }

        fun getRideMode(): Int {
            var mode = getRideMode(localReceivedData!![17])
            if (mode > 3) {
                mode = rideMode
            }
            if (mode <= 3) {
                rideMode = mode
            }
            return mode
        }

        private fun getRunTimeSinceEngineStart(): Float {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![12], bArr[13])).divide(BigInteger.valueOf(100L))
                .toFloat()
        }


        private fun getScreenMatrix(): Int {
            return localReceivedData!![14].toInt() and 255
        }

        private fun getBreakSwitchStatus(): Int {
            return getStatus(localReceivedData!![3].toInt() and 255, 2)
        }

        private fun getServiceReminder(): Int {
            return localReceivedData!![4].toInt() and 255
        }

        private fun getCanCommunicationErrorStatus(): Int {
            return getStatus(localReceivedData!![2].toInt() and 255, 6)
        }

        private fun getSideStandSensorStatus(): Int {
            return getStatus(localReceivedData!![2].toInt() and 255, 5)
        }

        fun getChecksum(): Int {
            return localReceivedData!![18].toInt() and 255
        }

        fun getSideStandStatus(): Int {
            return getStatus(localReceivedData!![3].toInt() and 255, 7)
        }

        fun getClutchSwitchStatus(): Int {
            return getStatus(localReceivedData!![3].toInt() and 255, 1)
        }

        fun getSideStandTellTaleStatus(): Int {
            return getStatus(localReceivedData!![15].toInt() and 255, 5)
        }

        fun getCruisingRange(): Double {
            val bArr = localReceivedData
            return BigInteger(byteArrayOf(bArr!![3], bArr[4])).toDouble()
        }

        fun getSpeed(): Int {
            val i: Int = localReceivedData!![2].toInt() and 255
            if (i > 0 && !isRideOnGoing2) {
                setRideStartTime(Date().time)
                isRideOnGoing2 = true
            }
            return i
        }

        private fun getCurrentRideAvgInstantMileage(): Int {
            val i: Int = key_ride_mileage_count
            if (getSpeed() > 0 && i > 0) {
                val i2 = key_ride_mileage_sum
                val i3 = mileage
                key_current_ride_avg_instant_mileage = (i2 + i3) / i
                key_ride_mileage_sum = i3 + i2
                key_ride_mileage_count = i + 1
                /* TODO
                PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                    KEYS.U399_PREF_KEYS.key_ride_mileage_count,
                    f51959w
                )
                PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                    KEYS.U399_PREF_KEYS.key_ride_mileage_sum,
                    f51958v
                )
                PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                    KEYS.U399_PREF_KEYS.key_current_ride_avg_instant_mileage,
                    f51960x
                )*/
            }
            return key_current_ride_avg_instant_mileage
        }

        private fun getSpeedometerSwVersion(): Int {
            return localReceivedData!![7].toInt() and 255
        }

        fun getCurrentRideAverageSpeed(): Int {
            if (getSpeed() > 0) {
                currRideAvgSpeed = (((currRideAvgSpeed * averageSpeedCount) + getSpeed())
                        / (averageSpeedCount+1))
                averageSpeedCount++
            }
            return currRideAvgSpeed
        }

        fun getSwitchStatus(): Int {
            return localReceivedData!![11].toInt() and 255
        }

        fun getCurrentRideBestZeroTo100Time(): String {
            val d = currentZeroTo100Time
            if (d != -1.0 && d < this.currentRideBestZeroTo100Time) {
                this.currentRideBestZeroTo100Time = d
            }
            val d2 = this.currentRideBestZeroTo100Time
            if (d2 != 999.0 && d2 != 0.0) {
                return "" + this.currentRideBestZeroTo100Time
            }
            return "-"
        }

        fun getTellLeftTaleStatus(): Int {
            return getStatus(localReceivedData!![13].toInt() and 255, 2)
        }

        fun getCurrentRideBestZeroTo60Time(): String {
            val d = currentZeroTo60Time
            if (d != -1.0 && d < currentRideBestZeroTo60Time) {
                currentRideBestZeroTo60Time = d
            }
            val d2 = currentRideBestZeroTo60Time
            if (d2 != 999.0 && d2 != 0.0) {
                return "" + currentRideBestZeroTo60Time
            }
            return "-"
        }

        fun getTellRightTaleStatus(): Int {
            return getStatus(localReceivedData!![13].toInt() and 255, 3)
        }

        fun getCurrentZeroTo60Time(): Double {
            if (getSpeed() <= 0) {
                zeroTo60Achieved = -1.0
                start_time = Date().time
                Log.d("U399Best60Logs", "0-60 init called")
            }
            if (zeroTo60Achieved == -1.0 && getSpeed() >= 60) {
                val time: Long = Date().time - start_time
                currentZeroTo60Time = time.toDouble()
                currentZeroTo60Time = df2.format(time / 1000.0).toDouble()
                zeroTo60Achieved = 0.0
            }
            return currentZeroTo60Time
        }

        fun getCurrentZeroTo100Time(): Double {
            if (getSpeed() <= 0) {
                zeroTo100Achieved = -1.0
                start_time = Date().time
                Log.d("U399Best60Logs", "0-100 init called")
            }
            if (zeroTo100Achieved == -1.0 && getSpeed() >= 100) {
                val time: Long = Date().time - start_time
                currentZeroTo100Time = time.toDouble()
                currentZeroTo100Time = df2.format(time / 1000.0).toDouble()
                zeroTo100Achieved = 0.0
            }
            return currentZeroTo100Time
        }

        fun getTellTaleStatus(): Int {
            return localReceivedData!![13].toInt() and 255
        }

        fun getGearShift(): Int {
            return String.format("%02X", Integer.valueOf(localReceivedData!![5].toInt() and 15))[0].toString()
                .toInt()
        }

        fun getGearShiftIndication(): Int {
            return localReceivedData!![5].toInt() and 255 and 240 shr 4
        }

        private fun getISGMilBlinkCode(): Int {
            return localReceivedData!![11].toInt() and 255
        }

        private fun getMilBlinkCode(): Int {
            return localReceivedData!![8].toInt() and 255
        }

        fun getParsedData(receivedData: ByteArray?): ClusterReceivedData {
            setReceivedData(receivedData)
            val receivedClusterData = ClusterReceivedData(
                connected = false,
                userId = -1,
                frameNo = -1,
                vehicleId = -1,
                vehicleSeries = "",
                dataType = -1,
                manifoldAirPressure = -1,
                barometricPressure = -1,
                intakeAirTemperature = -1,
                engineTemperatureFrame = -1,
                fuelInjectionTime = (-1.0).toFloat(),
                batteryVoltageFrame = -1.0,
                runTimeSinceEngineStart = (-1.0).toFloat(),
                distanceTravelledSinceMILOn = -1.0,
                fuelInjectionVolume = -1.0,

                // 16
                locationTagActive = false,
                switchStatus = -1,
                voiceAssistInvoke = false,
                speed = -1,
                acceleration = -1.0,
                odometer = -1.0,
                fuelLevelPercentage = -1,
                averageSpeed = -1,
                mileage = -1,
                topSpeed = -1,
                currentRideBestTopSpeed = -1,
                throttlePercentage = -1,
                zeroTo60Time = -1.0,
                tripFMeter = "",
                averageMileageDirect = -1,
                engineRPM = -1.0,
                checksum = -1,
                callAcceptRejectStatus = -1,
                alertPositionBtnClick = -1,
                currentZeroTo60Time = -1.0,
                currentZeroTo100Time = -1.0,
                currentRideBestZeroTo60Time = "",
                currentRideBestZeroTo100Time = "",
                currentRideBestAcceleration = "",
                currentRideBestDeceleration = "",
                currentRideAverageSpeed = -1,
                currentRideAvgInstantMileage = -1,

                // 17
                vehicleState1 = -1,
                clutchSwitchStatus = -1,
                breakSwitchStatus = -1,
                electricStartSwitchStatus = -1,
                sideStandStatus = -1,
                engineSpeedSensorStatus = -1,
                gearPositionSensorStatus = -1,
                sideStandSensorStatus = -1,
                canCommunicationErrorStatus = -1,
                lowBatteryStatus = -1,
                killSwitchStatus = -1,
                esSwitchStatus = -1,
                sideStandTellTaleStatus = -1,
                generalWarningTellTaleStatus = -1,
                engineStartedStatus = -1,
                gearPosition = -1,
                gearShiftIndication = -1,
                serviceReminder = -1,
                vehicleState2 = -1,
                speedometerSwVersion = -1,
                vehicleDiagnostics = -1,
                tpsErrorStatus = -1,
                engineTempSensorStatus = -1,
                vehicleSpeedRearSensorErrorStatus = -1,
                vehicleSpeedFrontSensorErrorStatus = -1,
                intakeAirTempSensorStatus = -1,
                milStatus = -1,
                isgNormal = -1,
                absNormal = -1,
                turnSignalLampStatus = -1,
                engineTemperature = -1,
                intakeAirTemperature2 = -1,
                accumulatedFuelInjectionTime = (-1.0).toFloat(),
                backlightIllumination = -1,
                rideMode = -1,
                checksum2 = -1,
                fuelSensorFailure = -1,
                milBlinkCode = -1,
                vehicleModel = -1,
                vehicleModelName = "",
                iSGMilBlinkCode = -1,
                tellTaleStatus = -1,
                neutralTaleStatus = -1,
                tellLeftTaleStatus = -1,
                tellRightTaleStatus = -1,
                highBeamTaleStatus = -1,
                lfiStatus = -1,
                emsMilStatus = -1,
                absMilStatus = -1,
                screenMatrix = -1,
                vehicleState3 = -1,
                absMilBlinkCode = -1,

                // 18
                leanAngleDegree = -1,
                cruisingRange = -1.0,
                wheelieAngleOffset = -1,
                acceleration2 = -1.0,
                torque = -1,
                tripDistance = -1.0,
                tripTime = "",
                tripMileage = -1,
                tripFuel = -1.0,
                checksum3 = -1,

                // 25
                getTripADistance = -1.0,
                tripAMileage = -1,
                tripBDistance = -1.0,
                tripBMileage = -1,
                rangeDTE = -1,
                tripAAverageSpeed = -1,
                tripBAverageSpeed = -1,
                distanceCovered = -1.0,

                )
            if (receivedData!![0].toInt() == 90) {
                receivedClusterData.connected = true
                receivedClusterData.userId = 123
                receivedClusterData.frameNo = 123
                receivedClusterData.vehicleId = 123
                receivedClusterData.vehicleSeries = "APACHE"

                val b = receivedData[1]
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
                    receivedClusterData.manifoldAirPressure = getManifoldAirPressure()
                    receivedClusterData.barometricPressure = getBarometricPressure()
                    receivedClusterData.intakeAirTemperature = getIntakeAirTemperatureFrame5()
                    receivedClusterData.engineTemperatureFrame = setEngineTemperatureFrame5()
                    receivedClusterData.fuelInjectionTime = getFuelInjectionTime()
                    receivedClusterData.batteryVoltageFrame = getBatteryVoltageFrame5HV() / 10.0
                    receivedClusterData.runTimeSinceEngineStart = getRunTimeSinceEngineStart()
                    receivedClusterData.distanceTravelledSinceMILOn = getDistanceTravelledSinceMILOn()
                    receivedClusterData.fuelInjectionVolume = getFuelInjectionVolume()
                } else if (b.toInt() != 25) {
                    when (b.toInt()) {
                        16 -> {
                            receivedClusterData.locationTagActive = getLocationTagActive()
                            receivedClusterData.switchStatus = getSwitchStatus()
                            receivedClusterData.voiceAssistInvoke = getVoiceAssistInvoke()
                            receivedClusterData.speed = getSpeed()
                            receivedClusterData.acceleration = getAcceleration()
                            receivedClusterData.odometer = getOdometer()
                            receivedClusterData.fuelLevelPercentage = getFuelLevelPercentage()
                            receivedClusterData.averageSpeed = getAverageSpeed()
                            receivedClusterData.mileage = getMileage()
                            receivedClusterData.topSpeed = getTopSpeed()
                            receivedClusterData.currentRideBestTopSpeed = getCurrentRideBestTopSpeed()
                            receivedClusterData.throttlePercentage = getThrottlePercentage()
                            receivedClusterData.zeroTo60Time = getZeroTo60Time()
                            receivedClusterData.tripFMeter = getTripFMeter()
                            receivedClusterData.averageMileageDirect = getAvgMileageDirect()
                            receivedClusterData.engineRPM = getEngineRPM()
                            receivedClusterData.checksum = getChecksum()
                            receivedClusterData.callAcceptRejectStatus = getCallAcceptRejectStatus()
                            receivedClusterData.alertPositionBtnClick = getAlertPositiveBtnClick()
                            receivedClusterData.currentZeroTo60Time = getCurrentZeroTo60Time()
                            receivedClusterData.currentZeroTo100Time = getCurrentZeroTo100Time()
                            receivedClusterData.currentRideBestZeroTo60Time = getCurrentRideBestZeroTo60Time()
                            receivedClusterData.currentRideBestZeroTo100Time = getCurrentRideBestZeroTo100Time()
                            receivedClusterData.currentRideBestAcceleration = getCurrentRideBestAcceleration(getAcceleration())
                            receivedClusterData.currentRideBestDeceleration = getCurrentRideBestDeceleration(getAcceleration())
                            receivedClusterData.currentRideAverageSpeed = getCurrentRideAverageSpeed()
                            receivedClusterData.currentRideAvgInstantMileage = getCurrentRideAvgInstantMileage()

                        }

                        17 -> {
                            receivedClusterData.vehicleState1 = getVehicleState1()
                            receivedClusterData.clutchSwitchStatus = getClutchSwitchStatus()
                            receivedClusterData.breakSwitchStatus = getBreakSwitchStatus()
                            receivedClusterData.electricStartSwitchStatus = getElectricStartSwitchStatus()
                            receivedClusterData.sideStandStatus = getSideStandStatus()
                            receivedClusterData.engineSpeedSensorStatus = getEngineSpeedSensorStatus()
                            receivedClusterData.gearPositionSensorStatus = getGearPositionSensorStatus()
                            receivedClusterData.sideStandSensorStatus = getSideStandSensorStatus()
                            receivedClusterData.canCommunicationErrorStatus = getCanCommunicationErrorStatus()
                            receivedClusterData.lowBatteryStatus = getLowBatteryStatus()
                            receivedClusterData.killSwitchStatus = getKillSwitchStatus()
                            receivedClusterData.esSwitchStatus = getEsSwitchStatus()
                            receivedClusterData.sideStandTellTaleStatus = getSideStandTellTaleStatus()
                            receivedClusterData.generalWarningTellTaleStatus = getGeneralWarningTellTaleStatus()
                            receivedClusterData.engineStartedStatus = getEngineStartedStatus()
                            receivedClusterData.gearPosition = getGearPosition()
                            receivedClusterData.gearShiftIndication = getGearShiftIndication()
                            receivedClusterData.serviceReminder = getServiceReminder()
                            receivedClusterData.vehicleState2 = getVehicleState2()
                            receivedClusterData.speedometerSwVersion = getSpeedometerSwVersion()
                            receivedClusterData.vehicleDiagnostics = getVehicleDiagnostics()
                            receivedClusterData.tpsErrorStatus = getTpsErrorStatus()
                            receivedClusterData.engineTempSensorStatus = getEngineTempSensorStatus()
                            receivedClusterData.vehicleSpeedRearSensorErrorStatus = getVehicleSpeedRearSensorErrorStatus()
                            receivedClusterData.vehicleSpeedFrontSensorErrorStatus = getVehicleSpeedFrontSensorErrorStatus()
                            receivedClusterData.intakeAirTempSensorStatus = getIntakeAirTempSensorStatus()
                            receivedClusterData.milStatus = getMilStatus()
                            receivedClusterData.isgNormal = getIsgNormal()
                            receivedClusterData.absNormal = getAbsNormal()
                            receivedClusterData.turnSignalLampStatus = getTurnSignalLampStatus()
                            receivedClusterData.engineTemperature = getEngineTemperature()
                            receivedClusterData.intakeAirTemperature2 = getIntakeAirTemperature()
                            receivedClusterData.accumulatedFuelInjectionTime = getAccumulatedFuelInjectionTime()
                            receivedClusterData.backlightIllumination = getBacklightIllumination()
                            receivedClusterData.rideMode = getRideMode()
                            receivedClusterData.checksum2 = getChecksum()
                            receivedClusterData.fuelSensorFailure = getFuelSensorFailure()
                            receivedClusterData.milBlinkCode = getMilBlinkCode()
                            receivedClusterData.vehicleModel = getVehicleModel()
                            receivedClusterData.vehicleModelName = getVehicleModelName()
                            receivedClusterData.iSGMilBlinkCode = getISGMilBlinkCode()
                            receivedClusterData.tellTaleStatus = getTellTaleStatus()
                            receivedClusterData.neutralTaleStatus = getNeutralTaleStatus()
                            receivedClusterData.tellLeftTaleStatus = getTellLeftTaleStatus()
                            receivedClusterData.tellRightTaleStatus = getTellRightTaleStatus()
                            receivedClusterData.highBeamTaleStatus = getHighBeamTaleStatus()
                            receivedClusterData.lfiStatus = getLfiStatus()
                            receivedClusterData.emsMilStatus = getEmsMilStatus()
                            receivedClusterData.absMilStatus = getAbsMilStatus()
                            receivedClusterData.screenMatrix = getScreenMatrix()
                            receivedClusterData.vehicleState3 = getVehicleState3()
                            receivedClusterData.absMilBlinkCode = getAbsMilBlinkCode()
                        }

                        18 -> {
                            receivedClusterData.leanAngleDegree = getLeanAngleDegree()
                            receivedClusterData.cruisingRange = getCruisingRange()
                            receivedClusterData.wheelieAngleOffset = getWheelieAngleOffset()
                            receivedClusterData.acceleration2 = getAcceleration2()
                            receivedClusterData.torque = getTorque()
                            receivedClusterData.tripDistance = getTripDistanceHM() / 10.0
                            receivedClusterData.tripTime = getTripTime()
                            receivedClusterData.tripMileage = getTripMileage()
                            receivedClusterData.tripFuel = getTripFuel()
                            receivedClusterData.checksum3 = getChecksum()
                        }
                    }
                } else {
                    receivedClusterData.getTripADistance = getTripADistance()
                    receivedClusterData.tripAMileage = getTripAMileage()
                    receivedClusterData.tripBDistance = getTripBDistance() / 10.0
                    receivedClusterData.tripBMileage = getTripBMileage()
                    receivedClusterData.rangeDTE = getRangeDTE()
                    receivedClusterData.tripAAverageSpeed = getTripAAverageSpeed()
                    receivedClusterData.tripBAverageSpeed = getTripBAverageSpeed()
                    receivedClusterData.distanceCovered = getDistanceCovered()
                }
            }
            return receivedClusterData
        }

        fun getVehicleModel(): Int {
            return localReceivedData!![9].toInt() and 255
        }

        private fun getVehicleModelName(): String {
            val i: Int = localReceivedData!![9].toInt() and 255
            return if (i != 10) if (i != 179) if (i != 196) if (i != 161) if (i != 162) "" else "EV" else "NTORQ" else "Apache" else "HEV" else "R&D vehicle"
        }

        fun setReceivedData(bArr: ByteArray?) {
            localReceivedData = bArr
        }

        private fun setRideStartTime(j: Long) {
            start_time = j
        }
    }
}


object EncryptDecryptDataBTData {
    private fun xorneg21(i: Int): ByteArray {
        val bArr = ByteArray(i)
        for (i2 in 0 until i) {
            bArr[i2] = -21
        }
        return bArr
    }

    fun decryptData(bArr: ByteArray): ByteArray {
        val bArr2 = byteArrayOf(bArr[0])
        val bArr3 = byteArrayOf(bArr[1])
        val bArr4 = byteArrayOf(-1)
        val copyOfRange = bArr.copyOfRange(2, bArr.size - 1)
        val bArr5 = ByteArray(copyOfRange.size)
        for (i in copyOfRange.indices) {
            bArr5[i] = (copyOfRange[i].toInt() xor xorneg21(copyOfRange.size)[i].toInt()).toByte()
        }
        return (bArr2 + bArr3 + bArr5 + bArr4)
    }

    fun encryptData(bArr: ByteArray): ByteArray {
        val bArr2 = byteArrayOf(bArr[0])
        val bArr3 = byteArrayOf(bArr[1])
        val bArr4 = byteArrayOf(-1)
        val copyOfRange = bArr.copyOfRange(2, bArr.size - 1)
        val bArr5 = ByteArray(copyOfRange.size)
        for (i in copyOfRange.indices) {
            bArr5[i] = (copyOfRange[i].toInt() xor xorneg21(copyOfRange.size)[i].toInt()).toByte()
        }
        return (bArr2 + bArr3 + bArr5 + bArr4)
    }
}
