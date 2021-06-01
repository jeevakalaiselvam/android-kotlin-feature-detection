package com.gamerguide.android.starterapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.gamerguide.android.starterapp.helpers.BlurHelper
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamerguide.android.starterapp.adapters.ListAdapter
import com.gamerguide.android.starterapp.databinding.ActivityMainBinding
import io.github.inflationx.viewpump.ViewPump
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.calligraphy3.CalligraphyConfig

const val AVAILABLE = "AVAILABLE";
const val NOT_AVAILABLE = "NOT AVAILABE"

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    private var background: ImageView? = null
    private var blurHelper: BlurHelper? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListAdapter

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/sourcesanspro.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        blurHelper = BlurHelper()
        background = findViewById(R.id.background)
        blurHelper!!.setupImageBlurBackground(this, background!!)

        binding.title.text = "Available Features"

        val featureMaps: LinkedHashMap<String, Boolean> = checkAvailableFeatures();

        linearLayoutManager = LinearLayoutManager(this)
        adapter = ListAdapter(featureMaps)
        binding.list.layoutManager = linearLayoutManager
        binding.list.adapter = adapter

        binding.reload.setOnClickListener { blurHelper!!.setupImageBlurBackground(this, background!!) }

    }

    private fun checkAvailableFeatures(): LinkedHashMap<String, Boolean> {

        val allFeatures = linkedMapOf<String, Boolean>(

            "COMPASS" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS),
            "BLUETOOTH" to packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH),
            "ANY CAMERA" to packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY),
            "CAMERA WITH FLASH" to packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH),
            "FRONT SELFIE CAMERA" to packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT),
            "NETWORK CARD" to packageManager.hasSystemFeature(PackageManager.FEATURE_ETHERNET),
            "FACE BIOMETRIC HARDWARE" to packageManager.hasSystemFeature(PackageManager.FEATURE_FACE),
            "ADVANCED SENSORS" to packageManager.hasSystemFeature(PackageManager.FEATURE_HIFI_SENSORS),
            "HOMESCREEN THEMING SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_HOME_SCREEN),
            "EYE BIOMETRIC HARDWARE" to packageManager.hasSystemFeature(PackageManager.FEATURE_IRIS),
            "LIVE WALLPAPER SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_LIVE_WALLPAPER),
            "GPS SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS),
            "MICROPHONE" to packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE),
            "NFC SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_NFC),
            "PICTURE IN PICTURE MODE SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE),
            "LANDSCAPE ORIENTATION SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_SCREEN_LANDSCAPE),
            "PORTRAIT ORIENTATION SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_SCREEN_PORTRAIT),
            "ACCELEROMETER" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER),
            "TEMPERATURE SENSOR" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE),
            "BAROMETER SENSOR" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER),
            "COMPASS" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS),
            "GYROSCOPE" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE),
            "HEART RATE SENSOR" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE),
            "LIGHT SENSOR" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT),
            "PROXIMITY SENSOR" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_PROXIMITY),
            "HUMIDITY SENSOR" to packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY),
            "TELEPHONE SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY),
            "CDMA TELEPHONE SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA),
            "GSM TELEPHONE SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM),
            "TOUCHSCREEN" to packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN),
            "TOUCHSCREEN MULTITOUCH SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH),
            "USB SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY),
            "WEBVIEW SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_WEBVIEW),
            "WIFI SUPPORT" to packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI),
        )

        return allFeatures


    }

}