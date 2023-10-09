package com.shubu.biketelemetery.bluetooth

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.shubu.biketelemetery.BikeApp
import com.shubu.biketelemetery.MainActivity
import com.shubu.biketelemetery.localdata.SaveCurrentSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

class BluetoothPermissions {
    companion object
    {
        private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        private val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)

        val locationServiceStateReceiver: BroadcastReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action
                if (action != null && action == LocationManager.MODE_CHANGED_ACTION) {
                    // val isEnabled = areLocationServicesEnabled()
                    checkPermissions()
                }
            }
        }

        private val enableBluetoothRequest =
            MainActivity.instance.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == ComponentActivity.RESULT_OK) {
                    checkPermissions()
                } else {
                    askToEnableBluetooth()
                }
            }

        val isBluetoothEnabled: Boolean
            get() {
                val bluetoothAdapter = bluetoothManager.adapter ?: return false
                return bluetoothAdapter.isEnabled
            }

        private val requiredPermissions: Array<String>
            get() {
                val targetSdkVersion = BikeApp.applicationContext.applicationInfo.targetSdkVersion
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && targetSdkVersion >= Build.VERSION_CODES.S) {
                    arrayOf(android.Manifest.permission.BLUETOOTH_SCAN, android.Manifest.permission.BLUETOOTH_CONNECT)
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && targetSdkVersion >= Build.VERSION_CODES.Q) {
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                } else arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }

        val bluetoothManager by lazy {
            BikeApp.applicationContext
                .getSystemService(ComponentActivity.BLUETOOTH_SERVICE)
                    as BluetoothManager
        }

        fun checkPermissions() {
            val missingPermissions = getMissingPermissions(requiredPermissions)
            if (false and missingPermissions.isNotEmpty()) {
                MainActivity.instance.requestPermissions(missingPermissions, MainActivity.ACCESS_LOCATION_REQUEST)
//                checkIfLocationIsNeeded()
            } else {
                checkIfLocationIsNeeded()
            }
        }

        private fun getMissingPermissions(requiredPermissions: Array<String>) : Array<String> {
            val missingPermissions: MutableList<String> = ArrayList()
            for (requiredPermission in requiredPermissions) {
                if (BikeApp.applicationContext.checkSelfPermission(requiredPermission) != PackageManager.PERMISSION_GRANTED) {
                    missingPermissions.add(requiredPermission)
                }
            }
            return missingPermissions.toTypedArray()
        }


        private fun initBluetoothHandler() {
            val bluetoothHandler = BluetoothHandler.getInstance(BikeApp.applicationContext)

            collectData(bluetoothHandler)
        }

        private fun collectData(bluetoothHandler: BluetoothHandler) {
            if(BikeApp.COLLECT_ALL_DATA)
            {
                scope.launch {
                    Log.i("data consumption", "consumption started")
                    bluetoothHandler.dataChannelFull.consumeAsFlow().collect {
                        withContext(Dispatchers.Main) {
                            BikeApp.setClusterDataFull(it)
                            SaveCurrentSession.appendDataFull(it)
                            // measurementValue = it.toString().substring(20, it.toString().length - 2).replace("=", "    :    ").split(",").toString()
                            // measurementValue = it.dataType.toString()
                            // Log.i("data consumption - type", it.dataType.toString())
                        }
                    }
                }
            } else {
                scope.launch {
                    Log.i("data consumption", "consumption started")
                    bluetoothHandler.dataChannel.consumeAsFlow().collect {
                        withContext(Dispatchers.Main) {
                            BikeApp.setClusterData(it)
                            SaveCurrentSession.appendData(it)
                        }
                    }
                }
            }

        }


        private fun checkIfLocationIsNeeded() {
            val targetSdkVersion = BikeApp.applicationContext.applicationInfo.targetSdkVersion
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S && targetSdkVersion < Build.VERSION_CODES.S) {
                // Check if Location services are on because they are required to make scanning work for SDK < 31
                if (checkLocationServices()) {
                    initBluetoothHandler()
                }
            } else {
                initBluetoothHandler()
            }
        }

        private fun areLocationServicesEnabled(): Boolean {
            val locationManager =
                BikeApp.applicationContext.getSystemService(ComponentActivity.LOCATION_SERVICE) as LocationManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                locationManager.isLocationEnabled
            } else {
                val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetworkEnabled =
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                isGpsEnabled || isNetworkEnabled
            }
        }

        private fun checkLocationServices(): Boolean {
            return if (!areLocationServicesEnabled()) {
                AlertDialog.Builder(MainActivity.instance)
                    .setTitle("Location services are not enabled")
                    .setMessage("Scanning for Bluetooth peripherals requires locations services to be enabled.") // Want to enable?
                    .setPositiveButton("Enable") { dialogInterface, _ ->
                        dialogInterface.cancel()
                        MainActivity.instance.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel()
                    }
                    .create()
                    .show()
                false
            } else {
                true
            }
        }

        fun askToEnableBluetooth() {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            enableBluetoothRequest.launch(enableBtIntent)
        }
    }
}