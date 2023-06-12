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
            var extension = ".txt"
            if(BikeApp.COLLECT_ALL_DATA)
            {
                extension = ".csv"
            }
            fileOutputStream = BikeApp.applicationContext.openFileOutput(SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH).format(Calendar.getInstance().time) + extension, MODE_PRIVATE)
            outputStreamWriter = OutputStreamWriter(fileOutputStream)
            if(BikeApp.COLLECT_ALL_DATA)
            {
                outputStreamWriter.append(SaveData.getCSVHeader() + "\n")
            }
        }
        fun appendDataFull(data: ClusterReceivedData) {
            outputStreamWriter.append(data.toString() + "\n")
            //Log.i("data written", data.toString())
        }
        fun appendData(data: SaveData) {
            outputStreamWriter.append(data.toString() + "\n")
        }
        fun closeFile()
        {
            outputStreamWriter.close()
            Log.i("outStream", "close")
        }
    }
}