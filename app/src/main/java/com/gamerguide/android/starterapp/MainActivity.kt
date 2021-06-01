package com.gamerguide.android.starterapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.gamerguide.android.starterapp.helpers.BlurHelper
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
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

        binding.title.text = "Application Title"

        checkAvailableFeatures();
    }

    private fun checkAvailableFeatures() {

        val allFeatures = """
            COMPASS ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) AVAILABLE else NOT_AVAILABLE }
            BLUETOOTH ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) AVAILABLE else NOT_AVAILABLE }
            ANY CAMERA ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) AVAILABLE else NOT_AVAILABLE }
            CAMERA WITH FLASH ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) AVAILABLE else NOT_AVAILABLE }
            FRONT SELFIE CAMERA ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) AVAILABLE else NOT_AVAILABLE }
            NETWORK CARD ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_ETHERNET)) AVAILABLE else NOT_AVAILABLE }
            FACE BIOMETRIC HARDWARE ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_FACE)) AVAILABLE else NOT_AVAILABLE }
            ADVANCED SENSORS ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_HIFI_SENSORS)) AVAILABLE else NOT_AVAILABLE }
            HOMESCREEN THEMING SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_HOME_SCREEN)) AVAILABLE else NOT_AVAILABLE }
            EYE BIOMETRIC HARDWARE ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_IRIS)) AVAILABLE else NOT_AVAILABLE }
            LIVE WALLPAPER SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_LIVE_WALLPAPER)) AVAILABLE else NOT_AVAILABLE }
            GPS SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) AVAILABLE else NOT_AVAILABLE }
            MICROPHONE ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) AVAILABLE else NOT_AVAILABLE }
            NFC SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)) AVAILABLE else NOT_AVAILABLE }
            PICTURE IN PICTURE MODE SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)) AVAILABLE else NOT_AVAILABLE }
            LANDSCAPE ORIENTATION SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SCREEN_LANDSCAPE)) AVAILABLE else NOT_AVAILABLE }
            PORTRAIT ORIENTATION SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SCREEN_PORTRAIT)) AVAILABLE else NOT_AVAILABLE }
            ACCELEROMETER ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)) AVAILABLE else NOT_AVAILABLE }
            TEMPERATURE SENSOR ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE)) AVAILABLE else NOT_AVAILABLE }
            BAROMETER SENSOR ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER)) AVAILABLE else NOT_AVAILABLE }
            COMPASS ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) AVAILABLE else NOT_AVAILABLE }
            GYROSCOPE ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)) AVAILABLE else NOT_AVAILABLE }
            HEART RATE SENSOR ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE)) AVAILABLE else NOT_AVAILABLE }
            LIGHT SENSOR ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT)) AVAILABLE else NOT_AVAILABLE }
            PROXIMITY SENSOR ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_PROXIMITY)) AVAILABLE else NOT_AVAILABLE }
            HUMIDITY SENSOR ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY)) AVAILABLE else NOT_AVAILABLE }
            TELEPHONE SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) AVAILABLE else NOT_AVAILABLE }
            CDMA TELEPHONE SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA)) AVAILABLE else NOT_AVAILABLE }
            GSM TELEPHONE SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM)) AVAILABLE else NOT_AVAILABLE }
            TOUCHSCREEN ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)) AVAILABLE else NOT_AVAILABLE }
            TOUCHSCREEN MULTITOUCH SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)) AVAILABLE else NOT_AVAILABLE }
            USB SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY)) AVAILABLE else NOT_AVAILABLE }
            WEBVIEW SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_WEBVIEW)) AVAILABLE else NOT_AVAILABLE }
            WIFI SUPPORT ${ if (packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI)) AVAILABLE else NOT_AVAILABLE }
            
            
            
        """.trimIndent()

        binding.data.text = allFeatures;


    }

}