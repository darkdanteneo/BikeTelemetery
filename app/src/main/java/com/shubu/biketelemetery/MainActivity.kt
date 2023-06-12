package com.shubu.biketelemetery

import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shubu.biketelemetery.bluetooth.BluetoothPermissions
import com.shubu.biketelemetery.ui.theme.BikeTelemeteryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        BikeApp.applicationContext = applicationContext
        registerReceiver(
            BluetoothPermissions.locationServiceStateReceiver,
            IntentFilter(LocationManager.MODE_CHANGED_ACTION)
        )
        setContent {
            BikeTelemeteryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        if (BluetoothPermissions.bluetoothManager.adapter != null) {
            if (!BluetoothPermissions.isBluetoothEnabled) {
                BluetoothPermissions.askToEnableBluetooth()
            } else {
                BluetoothPermissions.checkPermissions()
            }
        } else {
            Log.e("on resume", "err")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver((BluetoothPermissions.locationServiceStateReceiver))
    }

    companion object {
        const val ACCESS_LOCATION_REQUEST = 2
        lateinit var instance: MainActivity
    }

}


@Composable
fun SimpleText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AllDataList(modifier = Modifier.fillMaxSize())
}

@Composable
fun SingleData(title: String, data: String, modifier: Modifier) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, modifier = Modifier.weight(1f))
        Text(text = ":               $data", modifier = Modifier.weight(1f))
    }
}

@Composable
fun AllDataList(modifier: Modifier) {
    Column(modifier = modifier) {
        SimpleText( if(BikeApp.isConnected) "Connected" else "Not Connected" )
        SingleData(title = "Engine RPM", data = BikeApp.engineRPM.toString(), modifier = modifier)
        SingleData(title = "Speed", data = BikeApp.speed.toString(), modifier = modifier)
        SingleData(title = "Torque", data = BikeApp.torque.toString(), modifier = modifier)
        SingleData(title = "Top Speed", data = BikeApp.topSpeed.toString(), modifier = modifier)
        SingleData(title = "Acceleration", data = BikeApp.acceleration.toString(), modifier = modifier)
        SingleData(title = "Acceleration2", data = BikeApp.acceleration2.toString(), modifier = modifier)
        SingleData(title = "Fuel Injection Volume", data = BikeApp.fuelInjectionVolume.toString(), modifier = modifier)
        SingleData(title = "Fuel Injection Time", data = BikeApp.fuelInjectionTime.toString(), modifier = modifier)
        SingleData(title = "Mileage", data = BikeApp.mileage.toString(), modifier = modifier)
        SingleData(title = "Lean Angle Degrees", data = BikeApp.leanAngleDegree.toString(), modifier = modifier)
        SingleData(title = "Average Speed", data = BikeApp.averageSpeed.toString(), modifier = modifier)
        SingleData(title = "Gear Position", data = BikeApp.gearPosition.toString(), modifier = modifier)
        SingleData(title = "Fuel Level Percentage", data = BikeApp.fuelLevelPercentage.toString(), modifier = modifier)
        SingleData(title = "Odometer", data = BikeApp.odometer.toString(), modifier = modifier)
        SingleData(title = "Throttle Percentage", data = BikeApp.throttlePercentage.toString(), modifier = modifier)
        SingleData(title = "Average Mileage Direct", data = BikeApp.averageMileageDirect.toString(), modifier = modifier)
        SingleData(title = "Battery Voltage", data = BikeApp.batteryVoltageFrame.toString(), modifier = modifier)
        SingleData(title = "Engine Temperature", data = BikeApp.engineTemperature.toString(), modifier = modifier)
        SingleData(title = "Engine Temperature Frame", data = BikeApp.engineTemperatureFrame.toString(), modifier = modifier)
        SingleData(title = "Manifold Air Pressure", data = BikeApp.manifoldAirPressure.toString(), modifier = modifier)
        SingleData(title = "Intake Air Temperature", data = BikeApp.intakeAirTemperature.toString(), modifier = modifier)
        SingleData(title = "Intake Air Temperature 2", data = BikeApp.intakeAirTemperature2.toString(), modifier = modifier)
        SingleData(title = "Current Best top speed", data = BikeApp.currentRideBestTopSpeed.toString(), modifier = modifier)
        SingleData(title = "Current Best 0 to 60", data = BikeApp.currentRideBestZeroTo60Time, modifier = modifier)
        SingleData(title = "Wheelie Angle", data = BikeApp.wheelieAngleOffset.toString(), modifier = modifier)
    }
}