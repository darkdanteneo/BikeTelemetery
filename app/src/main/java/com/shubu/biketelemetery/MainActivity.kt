package com.shubu.biketelemetery

import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
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
    Column()
    {
        AllDataList(modifier = Modifier)
        FuelConsumptionBar()
        Spacer(modifier = Modifier.size(15.dp))
        EngineTemperatureBar()
    }

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
        SingleData(title = "Fuel Consumption delta", data = BikeApp.fuelInjectionVolumeDelta.toString(), modifier = modifier)
    }
}


@Composable
fun FuelConsumptionBar() {
    var value = (BikeApp.fuelInjectionVolumeDelta.toInt() / 30).coerceAtLeast(0).coerceAtMost(100);
    var colorEnd = Color(0xffFD7D20);
    var colorStart = Color(0xffFBE41A);
    if(value == 0)
    {
        colorStart = Color(0xff3f911c)
        colorEnd = Color(0xffa2cf27)
        value = 100
    }
    CustomProgressBar(
        Modifier
            .clip(shape = RoundedCornerShape(10))
            .height(14.dp),
        400.dp,
        Color.Gray,
        Brush.horizontalGradient(listOf(colorStart, colorEnd)),
        value,
        true
    )
}

@Preview(showBackground = true)
@Composable
fun EngineTemperatureBar()
{
    val value = (BikeApp.engineTemperatureFrame).coerceAtLeast(0).coerceAtMost(150);
    val colorEnd = Color(0xffcc0e0e);
    val colorStart = Color(0xff4acc0e);
    Box()
    {
        CustomProgressBar(
            Modifier
                .clip(shape = RoundedCornerShape(10))
                .height(14.dp),
            400.dp,
            Color.Gray,
            Brush.horizontalGradient(listOf(colorStart, colorEnd)),
            value * 2 / 3,
            false
        )
        Text(text = value.toString(), modifier = Modifier.height(14.dp), color = Color(0xff500ecc))
    }
}

@Composable
fun CustomProgressBar(
    modifier: Modifier,
    width: Dp,
    backgroundColor: Color,
    foregroundColor: Brush,
    percent: Int,
    isShownText: Boolean
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100)
        )
    }
}