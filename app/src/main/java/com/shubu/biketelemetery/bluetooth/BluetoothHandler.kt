package com.shubu.biketelemetery.bluetooth

import android.annotation.SuppressLint
import android.content.Context
import android.os.BatteryManager
import android.telephony.CellInfoCdma
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.TelephonyManager
import android.util.Log
import com.shubu.biketelemetery.BikeApp
import com.shubu.biketelemetery.MainActivity
import com.shubu.biketelemetery.localdata.SaveCurrentSession
import com.shubu.biketelemetery.localdata.SaveData
import com.welie.blessed.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import java.text.SimpleDateFormat
import java.util.*


internal class BluetoothHandler private constructor(context: Context) {

    private var currentTimeCounter = 0
    private val secretUUID1 = UUID.fromString("5456534D-5647-5341-5342-454E544F5251")
    private val secretUUID2 = UUID.fromString("00005352-0000-1000-8000-00805F9B34FB")
    val dataChannelFull: Channel<ClusterReceivedData> = Channel<ClusterReceivedData>(UNLIMITED)
    val dataChannel: Channel<SaveData> = Channel<SaveData>(UNLIMITED)
    private var globalPeripheral: BluetoothPeripheral? = null
    private var connectedWithBike = false

    private suspend fun sendData(arr: ByteArray) {
        globalPeripheral!!.writeCharacteristic(
            globalPeripheral!!.getCharacteristic(secretUUID1, secretUUID2)!!,
            EncryptDecryptDataBTData.encryptData(arr),
            WriteType.WITH_RESPONSE
        )
    }

    fun concat(vararg arrays: ByteArray): ByteArray {
        val len = arrays.map { it.size }.sum()

        val result = ByteArray(len)
        var lengthSoFar = 0
        for (array in arrays) {
            System.arraycopy(array, 0, result, lengthSoFar, array.size)
            lengthSoFar += array.size
        }
        return result
    }

    fun createRiderNameArray(str: String): ByteArray {
        var str = str
        return try {
            val bArr = byteArrayOf(91, 82)
            if (str.length > 17) {
                str = str.substring(0, 17)
            }
            val bytes = str.toByteArray(charset("UTF-8"))
            val bArr2 = byteArrayOf(-1)
            var bArr3 = ByteArray(20 - (bytes.size + 2 + 1))
            var i = 0
            while (i < bArr3.size) {
                val length = bArr3.size
                val bArr4 = ByteArray(length)
                for (i2 in 0 until length) {
                    bArr4[i2] = 0
                }
                i++
                bArr3 = bArr4
            }
            concat(bArr, bytes, bArr3, bArr2)
        } catch (e: Exception) {
            e.printStackTrace()
            byteArrayOf(91, 82)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCellSignal(): Int {
        return try {
            val cellInfo = (MainActivity.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).allCellInfo[0]
            if (cellInfo is CellInfoGsm) {
                return cellInfo.cellSignalStrength.level
            }
            if (cellInfo is CellInfoCdma) {
                return cellInfo.cellSignalStrength.level
            }
            if (cellInfo is CellInfoLte) {
                return cellInfo.cellSignalStrength.level
            }
            if (cellInfo is CellInfoWcdma) {
                return cellInfo.cellSignalStrength.level
            }
            throw java.lang.Exception("Unknown type of cell signal!")
        } catch (e: java.lang.Exception) {
            Log.e("Unable to obtain cell signal information", e.toString())
            0
        }
    }

    fun hex2decimal(str: String): Int {
        val upperCase = str.uppercase(Locale.getDefault())
        var i = 0
        for (i2 in 0 until upperCase.length) {
            i = i * 16 + "0123456789ABCDEF".indexOf(upperCase[i2])
        }
        return i
    }

    fun getMobileData(): ByteArray {
        val hour: ByteArray
        val bArr2: ByteArray = byteArrayOf(1)  //or 0
        try {
            val startingData = byteArrayOf(91, 74)
            val endingData = byteArrayOf(-1)
            val cellSignal = 0 //getCellSignal()
            val batPerc = (MainActivity.instance.getSystemService(Context.BATTERY_SERVICE) as BatteryManager)
                .getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            var batteryAndSignal = byteArrayOf(hex2decimal(cellSignal.toString() + batPerc.toString()).toByte())
            batteryAndSignal = byteArrayOf(batPerc.toByte(), 120)
            val size2_1_1 = byteArrayOf(1, 1)
            val zero = byteArrayOf(0)
            val isSmsSent = byteArrayOf(0)
            val isCrashAlert = byteArrayOf(0)
            val missedCalls = ByteArray(1)
            val missedSMS = ByteArray(1)
            val calendar = Calendar.getInstance()
            hour = byteArrayOf(SimpleDateFormat("hh").format(calendar.time).toByte())
            val minute = byteArrayOf(calendar.get(Calendar.MINUTE).toByte())
            val second = byteArrayOf(calendar.get(Calendar.MINUTE).toByte())
            val concatByteArrays: ByteArray = concat(
                startingData,
                batteryAndSignal,
                zero,
                isSmsSent,
                hour,
                minute,
                second,
                bArr2,
                missedCalls,
                zero,
                byteArrayOf(
                    calendar[5].toByte(), // date
                    (calendar[2] + 1).toByte(), // month
                    java.lang.String.valueOf(calendar[1]).substring(2).toInt().toByte() //year
                ),
                missedSMS,
                byteArrayOf(0),
                isCrashAlert,
                byteArrayOf(0),
                endingData
            )
            return concatByteArrays
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return ByteArray(0);
    }

    fun String.decodeHex(): ByteArray {
        check(length % 2 == 0) { "Must have an even length" }

        return chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }

    private fun sendInitialdata() {
        scope.launch {
            val name = "Shubu"
            sendData(createRiderNameArray(name))
            delay(400)
            sendData(createRiderNameArray(name))//"5b525368756268616d20536861726d61000000ff".decodeHex()
            delay(400)
            sendData(createRiderNameArray(name))
            delay(400)
            sendData(getMobileData())//sendData(EncryptDecryptDataBTData.decryptData("5b4aff93ebebeee6fdeaebebe3eefcebebebebff".decodeHex()))
            //sendData(EncryptDecryptDataBTData.decryptData("5b4aff93ebebeee6fdeaebebe3eefcebebebebff".decodeHex()))
            delay(1000)
            sendData(EncryptDecryptDataBTData.decryptData("5b5ca38a8682999b9e99ebebebebebebebebebff".decodeHex()))
            delay(500)
            try {
                while (connectedWithBike)
                {
                    sendData(getMobileData())
                    //5b4a0001010000022a320100000c051700000000ff
                    //sendData(EncryptDecryptDataBTData.decryptData("5b4aff93ebebeee6fdeaebebe3eefcebebebebff".decodeHex()))
                    Log.i("Sending mobile data", getMobileData().toHex())
                    delay(2000)
                }
            } catch (e: Exception)
            {
                Log.i("Send Data", e.toString())
            }

        }
    }

    private fun handlePeripheral(peripheral: BluetoothPeripheral) {
        scope.launch {
            try {
                val mtu = 23//peripheral.requestMtu(23)
                // Log.i("", "MTU is $mtu")
                peripheral.requestConnectionPriority(ConnectionPriority.HIGH)
                globalPeripheral = peripheral
                Log.i("Handle Peripheral", "Send initial data")
                sendInitialdata()
                Log.i("Handle Peripheral", "setup notification")
                setupDataUpdateNotification()
            } catch (e: IllegalArgumentException) {
                Log.e("", e.toString())
            } catch (b: GattException) {
                Log.e("", b.toString())
            }
        }
    }

    fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

    private suspend fun setupDataUpdateNotification() {
        Log.i("Update Notification", "Data Update listener created")
        globalPeripheral?.getCharacteristic(secretUUID1, UUID.fromString("00005354-0000-1000-8000-00805F9B34FB"))?.let {
            globalPeripheral?.observe(it) { value ->
                val decrypted = EncryptDecryptDataBTData.decryptData(value)
                // Log.i("Update Notification", "measurement received")
                // Log.i("Update Notification - pre cal measurement value", decrypted.toHex())
                try {
                    if (BikeApp.COLLECT_ALL_DATA)
                    {
                        val measurement = ClusterReceivedData.getParsedData(decrypted)
                        dataChannelFull.trySend(measurement)
                    }
                    else
                    {
                        val measurement = SaveData.getParsedData(decrypted)
                        dataChannel.trySend(measurement)
                    }

                } catch (e: java.lang.Exception) {
                    Log.i("error in decoding data", e.toString())
                    Log.i("Failed input", decrypted.toHex())
                    e.message?.let { it1 -> Log.e("Failed message", it1) }
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun stopDataUpdateNotification() {
        val c = globalPeripheral?.getCharacteristic(secretUUID1, UUID.fromString("00005354-0000-1000-8000-00805F9B34FB"));
        if (c != null) {
            Log.i("Update Notification", "Stopped")
            globalPeripheral?.stopObserving(c)
        };
    }

    companion object {

        private var instance: BluetoothHandler? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): BluetoothHandler {
            if (instance == null) {
                instance = BluetoothHandler(context.applicationContext)
            }
            return requireNotNull(instance)
        }
    }

    @JvmField
    var central: BluetoothCentralManager

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        central = BluetoothCentralManager(context)
        Log.i("", "stated scanning")
        // Log.i("mobile data log", getMobileData().toHex())

        central.observeConnectionState { peripheral, state ->
            val temp = peripheral.address
            Log.i("","Peripheral '${peripheral.name}' is $temp")
            when (state) {
                ConnectionState.CONNECTED -> {
                    if (peripheral.address == "AC:4D:16:1C:30:7B") {
                        handlePeripheral(peripheral)
                        BikeApp.isConnected = true
                        connectedWithBike = true
                        BikeApp.btMAC = peripheral.address
                        SaveCurrentSession.initSessionOutFile()
                    }
                }
                ConnectionState.DISCONNECTED -> scope.launch {
                    Log.i("Bike Connection", "Disconnected")
                    if (peripheral.address == "AC:4D:16:1C:30:7B") {
                        Log.i("Bike Connection", "Disconnected")
                        BikeApp.isConnected = false
                        connectedWithBike = false
                        BikeApp.btMAC = ""
                        SaveCurrentSession.closeFile()
                        stopDataUpdateNotification()
                        delay(15000)
                        central.autoConnectPeripheral(central.getPeripheral("AC:4D:16:1C:30:7B"))
                    }
                }
                else -> {
                }
            }
        }

        central.autoConnectPeripheral(central.getPeripheral("AC:4D:16:1C:30:7B"))
    }
}