package com.shubu.biketelemetery.localdata

import android.content.Context.MODE_PRIVATE
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.shubu.biketelemetery.BikeApp
import com.shubu.biketelemetery.bluetooth.ClusterReceivedData
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.util.Calendar
import java.util.Locale


class SaveCurrentSession {
    companion object {
        private lateinit var fileOutputStream: FileOutputStream
        private lateinit var outputStreamWriter:OutputStreamWriter
        fun initSessionOutFile() {
            if(this::outputStreamWriter.isInitialized)
            {
                outputStreamWriter.close()
            }
            var extension = ".csv"
            if(BikeApp.COLLECT_ALL_DATA)
            {
                extension = ".txt"
            }
            fileOutputStream = BikeApp.applicationContext.openFileOutput(
                SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH)
                    .format(Calendar.getInstance().time)
                        + extension,
                MODE_PRIVATE
            )
            outputStreamWriter = OutputStreamWriter(fileOutputStream)
            if(!BikeApp.COLLECT_ALL_DATA)
            {
                outputStreamWriter.append(SaveData.getCSVHeader() + "," + "fuelInjectionVolumeDelta" + "\n")
            }
        }
        fun appendDataFull(data: ClusterReceivedData) {
            outputStreamWriter.append(data.toString() + "\n")
            //Log.i("data written", data.toString())
        }
        fun appendDataSmall(data: SaveData) {
            outputStreamWriter.append(data.toString() + "\n")
        }
        fun appendData(data: SaveData) {
            val s = BikeApp.switchStatus.toString() + "," + BikeApp.speed.toString() + "," + BikeApp.acceleration.toString() + "," + BikeApp.odometer.toString() + "," +
                    BikeApp.fuelLevelPercentage.toString() + "," + BikeApp.averageSpeed.toString() + "," + BikeApp.mileage.toString() + "," + BikeApp.topSpeed.toString() + "," +
                    BikeApp.currentRideBestTopSpeed.toString() + "," + BikeApp.throttlePercentage.toString() + "," + BikeApp.zeroTo60Time.toString() + "," +
                    BikeApp.averageMileageDirect.toString() + "," + BikeApp.engineRPM.toString() + "," + BikeApp.checksum.toString() + "," + BikeApp.currentZeroTo60Time.toString() + "," +
                    BikeApp.currentZeroTo100Time.toString() + "," + BikeApp.currentRideBestZeroTo60Time.toString() +  ","  + BikeApp.currentRideBestZeroTo100Time.toString() + "," +
                    BikeApp.currentRideBestAcceleration.toString() +  ","  + BikeApp.currentRideBestDeceleration.toString() +  ","  + BikeApp.currentRideAverageSpeed.toString() + "," +
                    BikeApp.clutchSwitchStatus.toString() + "," + BikeApp.sideStandStatus.toString() + "," + BikeApp.killSwitchStatus.toString() + "," + BikeApp.sideStandTellTaleStatus.toString() + "," +
                    BikeApp.engineStartedStatus.toString() + "," + BikeApp.gearPosition.toString() + "," + BikeApp.gearShiftIndication.toString() + "," + BikeApp.vehicleDiagnostics.toString() + "," +
                    BikeApp.absNormal.toString() + "," + BikeApp.turnSignalLampStatus.toString() + "," + BikeApp.engineTemperature.toString() + "," + BikeApp.accumulatedFuelInjectionTime.toString() + "," +
                    BikeApp.backlightIllumination.toString() + "," + BikeApp.rideMode.toString() + "," + BikeApp.checksum2.toString() + "," + BikeApp.vehicleModel.toString() + "," +
                    BikeApp.tellTaleStatus.toString() + "," + BikeApp.neutralTaleStatus.toString() + "," + BikeApp.tellLeftTaleStatus.toString() + "," + BikeApp.tellRightTaleStatus.toString() + "," +
                    BikeApp.highBeamTaleStatus.toString() + "," + BikeApp.lfiStatus.toString() + "," + BikeApp.emsMilStatus.toString() + "," + BikeApp.absMilStatus.toString() + "," +
                    BikeApp.vehicleState3.toString() + "," + BikeApp.cruisingRange.toString() + "," + BikeApp.acceleration2.toString() + "," + BikeApp.checksum3.toString() + "," +
                    BikeApp.getTripADistance.toString() + "," + BikeApp.tripAMileage.toString() + "," + BikeApp.tripBDistance.toString() + "," + BikeApp.tripBMileage.toString() + "," +
                    BikeApp.rangeDTE.toString() + "," + BikeApp.tripAAverageSpeed.toString() + "," + BikeApp.tripBAverageSpeed.toString() + "," + BikeApp.distanceCovered.toString() + "," +
                    BikeApp.isConnected.toString() + "," + BikeApp.dataType.toString() + "," + BikeApp.barometricPressure.toString() + "," + BikeApp.intakeAirTemperature.toString() + "," +
                    BikeApp.engineTemperatureFrame.toString() + "," + BikeApp.fuelInjectionTime.toString() + "," + BikeApp.batteryVoltageFrame.toString() + "," +
                    BikeApp.fuelInjectionVolume.toString() + "," + BikeApp.fuelInjectionVolumeDelta.toString()
            outputStreamWriter.append(s + "\n")
        }
        fun closeFile()
        {
            outputStreamWriter.close()
            Log.i("outStream", "close")
        }
    }
}