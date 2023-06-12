package com.shubu.biketelemetery.bluetooth

import android.util.Log
import java.math.BigInteger
import java.text.DecimalFormat
import java.util.Date
import java.util.Locale

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
    var currentRideAvgInstaMileage: Int = -1,

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
    var speedoSwVersion: Int = -1,
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
    var acceleraation2: Double = -1.0,
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
    companion object {
        val U368_BT_DATA_KEY = "u368BluetoothData"
        /*        private var lapNumber = i6
                private var CurrentRideLastLapTime: Long = j
                private var CurrentRideTotalLaps = i7
                private var f51924D: Long = j2
                private var f51925E: Long = j3
                private var f51926F = i8*/
        private var intValue2 = 0
        private var intValue3 = 1
        private var key_current_ride_avg_insta_speed = 0
        private var localRecievedData: ByteArray? = null
        //private val *: U368BluetoothDataModel?*/ = null
        private var f51941e = 0.0
        private var f51942f = 0.0
        private var f51943g = 0f
        private var f51946j = 0.0
        private var f51947k: Long = 0
        private var f51948l = 0
        private var parseDouble = 0.0
        private var parseDouble2 = 0.0
        private var f51952p: Long = 0
        private var d = 0.0
        private var parseDouble3 = 0.0
        private var f51956t: Long = 0
        private var intValue = 0
        private var key_ride_mileage_sum = 0
        private var key_ride_mileage_count = 0
        private var key_current_ride_avg_insta_mileage = 0
        private var f51961y = 0.0
        private var f51962z = 0.0
        private var f51939c = false
        private val f51940d = false
        private var f51944h = false
        private var f51945i: Long = 0
        private var f51951o = -1.0
        private var f51955s = -1.0
        private var f51930J = 0
        private var f51931K = 0
        private val f51932L = false
        private val f51933M = 0.0f
        private var f51934N = 0.0
        private var f51935O = 0.0
        private var f51936P = false
        val df2: DecimalFormat = DecimalFormat("#.##")
        val f62114df = DecimalFormat("#.#")
        fun reverseString(str: String): String {
            var str2 = ""
            for (length in str.length - 1 downTo 0) {
                str2 = str2 + str[length]
            }
            return str2
        }

        fun getCurrentZeroTo60Time(): Double {
            if (getSpeed() <= 0) {
                f51961y = -1.0
                f51956t = Date().time
                Log.d("U399Best60Logs", "0-60 init called")
            }
            if (f51961y == -1.0 && getSpeed() >= 60) {
                val time: Long = Date().getTime() - f51956t
                f51955s = time.toDouble()
                f51955s = java.lang.Double.valueOf(df2.format(time / 1000.0)).toDouble()
                Log.d("U399Best60Logs", "0-60 time is: " + f51955s)
                f51961y = 0.0
            }
            return f51955s
        }

        fun getThrottlePercentage(): Int {
            return (localRecievedData!![10].toInt() and 255) / 2
        }

        private fun getEngineTempRaw(str: String): Int {
            val reverseString: String =
                reverseString(str.uppercase(Locale.getDefault()).trim { it <= ' ' })
            var i = 0
            for (i2 in 0 until reverseString.length) {
                i = (i + Math.pow(
                    16.0,
                    i2.toDouble()
                ) * "0123456789ABCDEF".indexOf(reverseString[i2])).toInt()
            }
            return i
        }

        fun getTopSpeed(): Int {
            return localRecievedData!![9].toInt() and 255
        }

        private fun getAccelerationRaw(): Double {
            if (f51948l == 0) {
                f51948l = getSpeed()
            }
            if (f51947k == 0L) {
                f51947k = System.currentTimeMillis()
            } else {
                val decimalFormat: DecimalFormat = f62114df
                m11722b(
                    decimalFormat.format(getSpeed() / 3.6).toDouble(),
                    decimalFormat.format(f51948l).toDouble(),
                    System.currentTimeMillis(),
                    f51947k
                )
            }
            return f51946j
        }

        private fun getTorque(): Int {
            return localRecievedData!![7].toInt() and 255
        }

        private fun getDistanceTravelledSinceMILOn(): Double {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![14], bArr[15])).toDouble()
        }

        private fun getTpsErrorStatus(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 1)
        }

        private fun getElectricStartSwitchStatus(): Int {
            return m11725a(localRecievedData!![3].toInt() and 255, 5)
        }

        fun getTripAAverageSpeed(): Int {
            return localRecievedData!![13].toInt() and 255
        }

        fun getEmsMilStatus(): Int {
            return m11725a(localRecievedData!![13].toInt() and 255, 5)
        }

        fun getTripADistance(): Double {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![5], bArr[6])).toDouble() / 10.0
        }

        fun getEngineRPM(): Double {
            val bArr = localRecievedData
            return java.lang.Double.toString(BigInteger(byteArrayOf(bArr!![16], bArr[17])).toDouble())
                .toDouble()
        }

        fun getTripAMileage(): Int {
            return localRecievedData!![7].toInt() and 255
        }

        private fun getEngineSpeedSensorStatus(): Int {
            return m11725a(localRecievedData!![2].toInt() and 255, 3)
        }

        fun getTripBAverageSpeed(): Int {
            return localRecievedData!![14].toInt() and 255
        }

        fun getEngineStartedStatus(): Int {
            return m11725a(localRecievedData!![15].toInt() and 255, 7)
        }

        fun getTripBDistance(): Double {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![8], bArr[9])).toDouble()
        }

        private fun getEngineTempSensorStatus(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 3)
        }

        fun getTripBMileage(): Int {
            return localRecievedData!![10].toInt() and 255
        }

        fun setEngineTemperatureFrame5(): Int {
            return (localRecievedData!![8].toInt() and 255) - 40
        }

        private fun getTripDistanceHM(): Double {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![8], bArr[9])).toDouble()
        }

        fun getEngineTemperature(): Int {
            return getEngineTempRaw(
                String.format(
                    "%02x",
                    java.lang.Byte.valueOf(localRecievedData!![13])
                )
            ) - 25
        }

        fun getDistanceCovered(): Double {
            if (getTripADistance() >= 0.0 && getTripADistance() < f51934N || !f51936P) {
                f51934N = getTripADistance()
            }
            if (!f51936P) {
                f51936P = true
            }
            f51935O += getTripADistance() - f51934N
            f51934N = getTripADistance()
            return f51935O
        }

        private fun getEsSwitchStatus(): Int {
            return m11725a(localRecievedData!![6].toInt() and 255, 6)
        }

        private fun getTripFMeter(): String? {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![13], bArr[14], bArr[15])).toString()
        }

        private fun getLanguageSomething(i: Int): String {
            return String.format(Locale.getDefault(), "%02d", Integer.valueOf(i))
        }

        private fun getTripFuel(): Double {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![13], bArr[14])).toDouble()
        }

        fun getFuelInjectionTime(): Float {
            val bArr = localRecievedData
            return (BigInteger(byteArrayOf(bArr!![9], bArr[10])).divide(BigInteger.valueOf(100L))
                .toFloat() * 0.001).toFloat()
        }

        private fun getTripMileage(): Int {
            return localRecievedData!![12].toInt() and 255
        }

        fun getFuelInjectionVolume(): Double {
            val bArr = localRecievedData
            return (bArr!![17].toInt() and 255 or (bArr[16].toInt() and 255 shl 8)).toDouble()
        }

        private fun getTripTime(): String {
            val bArr = localRecievedData
            return (bArr!![10].toInt() and 255).toString() + ":" + (bArr[11].toInt() and 255)
        }

        fun getFuelLevelPercentage(): Int {
            return localRecievedData!![6].toInt() and 255
        }

        fun getTurnSignalLampStatus(): Int {
            val i: Int = localRecievedData!![13].toInt() and 255
            val m11725a = m11725a(i, 2)
            val m11725a2 = m11725a(i, 3)
            val i2 = if (m11725a2 != 1) if (m11725a == 1) 1 else 0 else 2
            return if (m11725a == 1 && m11725a2 == 1) {
                3
            } else i2
        }

        private fun getFuelSensorFailure(): Int {
            return localRecievedData!![2].toInt() and 255
        }

        fun getVehicleDiagnostics(): Int {
            return localRecievedData!![10].toInt() and 255
        }

        fun getGearPosition(): Int {
            return try {
                String.format("%02X", Integer.valueOf(localRecievedData!![5].toInt() and 255))[1].toString().toInt()
            } catch (unused: Exception) {
                0
            }
        }

        private fun getVehicleSpeedFrontSensorErrorStatus(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 4)
        }

        private fun getGearPositionSensorStatus(): Int {
            return m11725a(localRecievedData!![2].toInt() and 255, 4)
        }

        private fun getVehicleSpeedRearSensorErrorStatus(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 2)
        }

        private fun getGeneralWarningTellTaleStatus(): Int {
            return m11725a(localRecievedData!![15].toInt() and 255, 6)
        }

        private fun getVehicleState1(): Int {
            return localRecievedData!![3].toInt() and 255
        }

        fun getHighBeamTaleStatus(): Int {
            return m11725a(localRecievedData!![13].toInt() and 255, 4)
        }

        private fun getVehicleState2(): Int {
            return localRecievedData!![6].toInt() and 255
        }

        private fun getIntakeAirTempSensorStatus(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 5)
        }

        fun getVehicleState3(): Int {
            return localRecievedData!![15].toInt() and 255
        }

        private fun getIntakeAirTemperature(): Int {
            return localRecievedData!![14].toInt() and 255
        }

        private fun getVoiceAssistInvoke(): Boolean {
            return localRecievedData!![11].toInt() and 4 === 4
        }

        fun getIntakeAirTemperatureFrame5(): Int {
            return (localRecievedData!![7].toInt() and 255) - 40
        }

        private fun getWheelieAngleOffset(): Int {
            return localRecievedData!![5].toInt() and 255
        }

        private fun getIsgNormal(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 7)
        }

        fun getZeroTo60Time(): Double {
            return java.lang.Double.valueOf(df2.format((localRecievedData!![12].toInt() and 255) / 10.0))
                .toDouble()
        }

        private fun m11725a(i: Int, i2: Int): Int {
            String.format("%8s", Integer.toBinaryString(i and 255)).replace(' ', '0')
            return i shr i2 - 1 and 1
        }

        fun getKillSwitchStatus(): Int {
            return m11725a(localRecievedData!![6].toInt() and 255, 1)
        }

        private fun getAlertPositiveBtnClick(): Int {
            return localRecievedData!![11].toInt() and 255
        }

        private fun m11722b(d: Double, d2: Double, j: Long, j2: Long) {
            if (d2 != d) {
                f51947k = System.currentTimeMillis()
                val d3 = ((j - j2).toFloat() / 1000.0f).toDouble()
                val d4 = (d - d2) / d3
                val i = if (d4 > 0.0) 1 else if (d4 == 0.0) 0 else -1
                if (i == 0 || d3 <= 0.0) {
                    return
                }
                if (d4 < 0.0 && f51946j > d4 && d4 > -1.0) {
                    f51946j = d4
                } else if (i <= 0 || f51946j >= d4 || d4 >= 3.0) {
                } else {
                    f51946j = d4
                }
            }
        }

        private fun getLapNumberDirect(): Int {
            return localRecievedData!![5].toInt()
        }

        private fun getLocationTagActive(): Boolean {
            return localRecievedData!![11].toInt() and 128 === 128
        }

        private fun crashAlertSomething() {
            /* TODO
            if (recievedData!![11].toInt() and 255 === 5) {
                BroadcastUtils.getInstance().broadcastByAction(
                    TVSApplication.getInstance().getApplicationContext(),
                    BluetoothUtil.ACTION_CANCEL_CRASH_ALERT
                )
                TVSApplication.getInstance().setCancelCrashAlert(true)
            }*/
        }


        /*private fun getLapTimeDirect(): String? {
                val bArr = recievedData
                val b = bArr!![2]
                val b2 = bArr[3]
                val b3 = bArr[4]
                return getLanguageSomething(b.toInt()) + ":" + getLanguageSomething(b2.toInt()) + ":" + getLanguageSomething(
                    b3.toInt()
                )
            }*/

        private fun getCallAcceptRejectStatus(): Int {
            val b = localRecievedData!![11]
            /* TODO
            Utils.saveU399CallStatusLog("Call Status value: " + b.toInt().toString())
            Utils.controlU368PhoneCall(b)*/
            return b.toInt()
        }

        private fun getAbsMilBlinkCode(): Int {
            return localRecievedData!![16].toInt() and 255
        }

        /*private fun getLapTrigger(): Int {
            val b = recievedData!![10]
            m11708f1(b.toInt())
            return b.toInt()
        }*/

        fun getCurrentRideBestTopSpeed(): Int {
            if (getSpeed() > intValue) {
                intValue = getSpeed()
            }
            return intValue
        }

        fun getAbsMilStatus(): Int {
            return m11725a(localRecievedData!![13].toInt() and 255, 6)
        }

        private fun getLeanAngleDegree(): Int {
            return localRecievedData!![2].toInt() and 255
        }

        /*private fun m11711e1(j: Long, i: Int) {
            val j2 = f51925E
            if (j < j2) {
                if (i > 2) {
                    CurrentRideLastLapTime = j2 - j
                }
                f51925E = j
                f51926F = i - 1
                Utils.saveLocationTagAndLapLog("current ride fastest lap time " + f51925E)
                Utils.saveLocationTagAndLapLog("current ride fastest lap number " + f51926F)
                return
            }
            if (lapNumber > 2) {
                CurrentRideLastLapTime = j2 - j
            }
            Utils.saveLocationTagAndLapLog("current ride last lap time " + CurrentRideLastLapTime)
        }*/

        fun getAbsNormal(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 8)
        }

        fun getLfiStatus(): Int {
            return m11725a(localRecievedData!![13].toInt() and 255, 8)
        }

        /*private fun m11708f1(i: Int) {
            if (i != 1) {
                if (i == 2) {
                    KEYS.isLapResetTriggered = true
                    f51943g = 0.0f
                    Utils.clearLapDataHandlerInstance()
                    CurrentRideTotalLaps--
                    return
                }
                return
            }
            KEYS.isLapTriggered = true
            KEYS.isNewLapStarted = true
            f51943g = f51942f.toFloat()
            Log.e("LapData", "lap start odo reading " + f51943g)
            val i2 = lapNumber
            if (i2 == 0) {
                PreferenceUtils.getInstance(Utils.getAppContext())
                    .setValue(KEYS.U399_PREF_KEYS.key_prev_lap_time, 0L)
                KEYS.isLapResetTriggered = false
                lapNumber = 1
                CurrentRideTotalLaps = 1
                f51926F = 1
                m11705g1()
                return
            }
            if (!KEYS.isLapResetTriggered) {
                lapNumber = i2 + 1
            }
            val longValue: Long = PreferenceUtils.getInstance(Utils.getAppContext())
                .getLongValue(KEYS.U399_PREF_KEYS.key_current_ride_lap_time, 0L)
            PreferenceUtils.getInstance(Utils.getAppContext())
                .setValue(KEYS.U399_PREF_KEYS.key_current_ride_completed_lap_time, longValue)
            val i3 = lapNumber
            if (i3 == 2) {
                f51925E = longValue
            }
            if (!KEYS.isLapResetTriggered) {
                m11711e1(longValue, i3)
            }
            CurrentRideTotalLaps++
            m11696j1(longValue)
            m11705g1()
        }*/

        fun getAcceleration(): Double {
            return f62114df.format(getAccelerationRaw()).toDouble()
        }

        private fun getLowBatteryStatus(): Int {
            return m11725a(localRecievedData!![2].toInt() and 255, 7)
        }

        /*private fun m11705g1() {
            PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                KEYS.U399_PREF_KEYS.key_lap_number,
                lapNumber
            )
            PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                KEYS.U399_PREF_KEYS.key_last_lap,
                CurrentRideLastLapTime
            )
            PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                KEYS.U399_PREF_KEYS.key_fastest_lap,
                f51925E
            )
            PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                KEYS.U399_PREF_KEYS.key_fastest_lap_number,
                f51926F
            )
            PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                KEYS.U399_PREF_KEYS.key_total_laps,
                CurrentRideTotalLaps
            )
            PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                KEYS.U399_PREF_KEYS.key_total_lap_time,
                f51924D
            )
            Utils.saveLocationTagAndLapLog("current ride lap number " + lapNumber)
        }*/

        fun getIllumination(b: Byte): Int {
            return b.toInt() and 240 shr 4
        }

        fun getRideMode(b: Byte): Int {
            return b.toInt() and 15
        }

        fun getAcceleraation2(): Double {
            return localRecievedData!![6].toDouble()
        }

        private fun getManifold_air_pressure(): Int {
            return localRecievedData!![5].toInt() and 255
        }

        fun getCurrentRideBestAcceleration(d: Double): String? {
            if (d >= 0.0 && d > parseDouble) {
                parseDouble = d
            }
            return parseDouble.toString() + " g"
        }

        fun getAccumulatedFuelInjectionTime(): Float {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![15], bArr[16])).divide(BigInteger.valueOf(100L))
                .toFloat()
        }

        private fun getMilStatus(): Int {
            return m11725a(localRecievedData!![10].toInt() and 255, 6)
        }

        fun getCurrentRideBestDeceleration(d: Double): String? {
            if (d <= 0.0 && d < parseDouble2) {
                parseDouble2 = d
            }
            return parseDouble2.toString() + " g"
        }

        fun getAverageSpeed(): Int {
            return localRecievedData!![7].toInt() and 255
        }

        fun getMileage(): Int {
            if (getSpeed() > 0) {
                f51930J = localRecievedData!![8].toInt() and 255
            }
            return f51930J
        }

        /*private fun m11696j1(j: Long) {
            if (KEYS.isLapResetTriggered) {
                KEYS.isLapResetTriggered = false
            } else {
                f51924D = j + f51924D
            }
            Utils.saveLocationTagAndLapLog("current ride total lap time " + f51924D)
            PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                KEYS.U399_PREF_KEYS.key_total_lap_time,
                f51924D
            )
        }*/

        fun getAvgMilageDirect(): Int {
            return localRecievedData!![13].toInt() and 255
        }

        fun getNeutralTaleStatus(): Int {
            return m11725a(localRecievedData!![13].toInt() and 255, 1)
        }

        fun getBacklightIllumination(): Int {
            return getIllumination(localRecievedData!![17])
        }

        fun getOdometer(): Double {
            val bArr = localRecievedData
            val parseDouble = java.lang.Double.toString(
                BigInteger(
                    byteArrayOf(
                        bArr!![3], bArr[4], bArr[5]
                    )
                ).toDouble() / 10.0
            ).toDouble()
            f51942f = parseDouble
            if (!f51939c) {
                f51939c = true
                f51941e = parseDouble
                Log.d("U368 Data", "isRideStartOdometerRecorded: " + f51941e)
            }
            return parseDouble
        }

        fun getBarometric_pressure(): Int {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![9], bArr[10])).toInt()
        }

        fun getRangeDTE(): Int {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![11], bArr[12])).toInt()
        }

        fun getBatteryVoltageFrame5HV(): Double {
            return (localRecievedData!![11].toInt() and 255).toDouble()
        }

        fun getRideMode(): Int {
            var rideMode = getRideMode(localRecievedData!![17])
            if (rideMode > 3) {
                rideMode = f51931K
            }
            if (rideMode < 4) {
                f51931K = rideMode
            }
            return rideMode
        }

        private fun getBestLapNumber(): Int {
            return localRecievedData!![9].toInt()
        }

        private fun getRunTimeSinceEngineStart(): Float {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![12], bArr[13])).divide(BigInteger.valueOf(100L))
                .toFloat()
        }

        private fun getBestLapTime(): String? {
            val bArr = localRecievedData
            val b = bArr!![6]
            val b2 = bArr[7]
            val b3 = bArr[8]
            return getLanguageSomething(b.toInt()) + ":" + getLanguageSomething(b2.toInt()) + ":" + getLanguageSomething(
                b3.toInt()
            )
        }

        private fun getScreenMatrix(): Int {
            return localRecievedData!![14].toInt() and 255
        }

        private fun getBreakSwitchStatus(): Int {
            return m11725a(localRecievedData!![3].toInt() and 255, 2)
        }

        private fun getServiceReminder(): Int {
            return localRecievedData!![4].toInt() and 255
        }

        private fun getCanCommunicationErrorStatus(): Int {
            return m11725a(localRecievedData!![2].toInt() and 255, 6)
        }

        private fun getSideStandSensorStatus(): Int {
            return m11725a(localRecievedData!![2].toInt() and 255, 5)
        }

        fun getChecksum(): Int {
            return localRecievedData!![18].toInt() and 255
        }

        fun getSideStandStatus(): Int {
            return m11725a(localRecievedData!![3].toInt() and 255, 7)
        }

        fun getClutchSwitchStatus(): Int {
            return m11725a(localRecievedData!![3].toInt() and 255, 1)
        }

        fun getSideStandTellTaleStatus(): Int {
            return m11725a(localRecievedData!![15].toInt() and 255, 5)
        }

        fun getCruisingRange(): Double {
            val bArr = localRecievedData
            return BigInteger(byteArrayOf(bArr!![3], bArr[4])).toDouble()
        }

        fun getSpeed(): Int {
            val i: Int = localRecievedData!![2].toInt() and 255
            if (i > 0 && !f51944h) {
                setRideStartTime(Date().getTime())
                f51944h = true
            }
            return i
        }

        private fun getCurrentRideAvgInstaMileage(): Int {
            val i: Int = key_ride_mileage_count
            if (getSpeed() > 0 && i > 0) {
                val i2 = key_ride_mileage_sum
                val i3 = f51930J
                key_current_ride_avg_insta_mileage = (i2 + i3) / i
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
                    KEYS.U399_PREF_KEYS.key_current_ride_avg_insta_mileage,
                    f51960x
                )*/
            }
            return key_current_ride_avg_insta_mileage
        }

        private fun getSpeedoSwVersion(): Int {
            return localRecievedData!![7].toInt() and 255
        }

        fun getCurrentRideAverageSpeed(): Int {
            if (getSpeed() > 0) {

                key_current_ride_avg_insta_speed = (intValue2 + getSpeed()) / intValue3
                intValue2 += getSpeed()
                intValue3++
                /* TODO
                PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                    KEYS.U399_PREF_KEYS.key_ride_count,
                    f51928H
                )
                PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                    KEYS.U399_PREF_KEYS.key_ride_sum,
                    f51927G
                )
                PreferenceUtils.getInstance(Utils.getAppContext()).setValue(
                    KEYS.U399_PREF_KEYS.key_current_ride_avg_insta_speed,
                    f51929I
                )*/
            }
            return key_current_ride_avg_insta_speed
        }

        fun getSwitchStatus(): Int {
            return localRecievedData!![11].toInt() and 255
        }

        fun getCurrentRideBestZeroTo100Time(): String? {
            val d = f51951o
            if (d != -1.0 && d < this.d) {
                this.d = d
            }
            Log.d("U399Best60Logs", "Best 0-100 : " + this.d)
            val d2 = this.d
            if (d2 != 999.0 && d2 != 0.0) {
                /* TODO
                val preferenceUtils: PreferenceUtils =
                    PreferenceUtils.getInstance(Utils.getAppContext())
                preferenceUtils.setValue(KEYS.U399_PREF_KEYS.key_best_100_speed, "" + f51953q)*/
                return "" + this.d
            }
            /* TODO
            PreferenceUtils.getInstance(Utils.getAppContext())
                .setValue(KEYS.U399_PREF_KEYS.key_best_100_speed, "")*/
            return "-"
        }

        fun getTellLeftTaleStatus(): Int {
            return m11725a(localRecievedData!![13].toInt() and 255, 2)
        }

        fun getCurrentRideBestZeroTo60Time(): String? {
            val d = f51955s
            if (d != -1.0 && d < parseDouble3) {
                parseDouble3 = d
            }
            Log.d("U399Best60Logs", "Best 0-60 : " + parseDouble3)
            val d2 = parseDouble3
            if (d2 != 999.0 && d2 != 0.0) {
                /* TODO
                val preferenceUtils: PreferenceUtils =
                    PreferenceUtils.getInstance(Utils.getAppContext())
                preferenceUtils.setValue(KEYS.U399_PREF_KEYS.key_best_60_speed, "" + f51954r)*/
                return "" + parseDouble3
            }
            /* TODO
            PreferenceUtils.getInstance(Utils.getAppContext())
                .setValue(KEYS.U399_PREF_KEYS.key_best_60_speed, "")*/
            return "-"
        }

        fun getTellRightTaleStatus(): Int {
            return m11725a(localRecievedData!![13].toInt() and 255, 3)
        }

        fun getCurrentZeroTo100Time(): Double {
            if (getSpeed() <= 0) {
                f51962z = -1.0
                f51952p = Date().getTime()
                Log.d("U399Best60Logs", "0-100 init called")
            }
            if (f51962z == -1.0 && getSpeed() >= 100) {
                val time: Long = Date().getTime() - f51952p
                f51951o = time.toDouble()
                f51951o = java.lang.Double.valueOf(df2.format(time / 1000.0)).toDouble()
                Log.d("U399Best60Logs", "0-100 time is: " + f51951o)
                f51962z = 0.0
            }
            return f51951o
        }

        fun getTellTaleStatus(): Int {
            return localRecievedData!![13].toInt() and 255
        }

        fun getGearShift(): Int {
            return String.format("%02X", Integer.valueOf(localRecievedData!![5].toInt() and 15))[0].toString()
                .toInt()
        }

        fun getGearShiftIndication(): Int {
            return localRecievedData!![5].toInt() and 255 and 240 shr 4
        }

        fun getISGMilBlinkCode(): Int {
            return localRecievedData!![11].toInt() and 255
        }

        fun getMilBlinkCode(): Int {
            return localRecievedData!![8].toInt() and 255
        }

        fun getParsedData(recievedData: ByteArray?): ClusterReceivedData {
            setReceivedData(recievedData)
            var receivedClusterData = ClusterReceivedData(
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
                currentRideAvgInstaMileage = -1,

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
                speedoSwVersion = -1,
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
                acceleraation2 = -1.0,
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
            if (recievedData!![0].toInt() == 90) {
                receivedClusterData.connected = true
                receivedClusterData.userId = 123
                receivedClusterData.frameNo = 123
                receivedClusterData.vehicleId = 123
                receivedClusterData.vehicleSeries = "APACHE"

                val b = recievedData!![1]
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
                    receivedClusterData.manifoldAirPressure = getManifold_air_pressure()
                    receivedClusterData.barometricPressure = getBarometric_pressure()
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
                            receivedClusterData.averageMileageDirect = getAvgMilageDirect()
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
                            receivedClusterData.currentRideAvgInstaMileage = getCurrentRideAvgInstaMileage()

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
                            receivedClusterData.speedoSwVersion = getSpeedoSwVersion()
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
                            receivedClusterData.acceleraation2 = getAcceleraation2()
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
            return localRecievedData!![9].toInt() and 255
        }

        fun getVehicleModelName(): String? {
            val i: Int = localRecievedData!![9].toInt() and 255
            return if (i != 10) if (i != 179) if (i != 196) if (i != 161) if (i != 162) "" else "EV" else "NTORQ" else "Apache" else "HEV" else "R&D vehicle"
        }

        fun setReceivedData(bArr: ByteArray?) {
            localRecievedData = bArr
        }

        fun setRideStartTime(j: Long) {
            f51945i = j
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
