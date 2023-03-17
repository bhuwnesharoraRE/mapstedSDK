package com.example.mapsted

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.mapsted.map.MapApi
import com.mapsted.map.models.layers.BaseMapStyle
import com.mapsted.map.views.MapPanType
import com.mapsted.map.views.MapstedMapRange
import com.mapsted.positioning.CoreApi
import com.mapsted.positioning.MapstedInitCallback
import com.mapsted.positioning.MessageType
import com.mapsted.positioning.SdkError
import com.mapsted.ui.CustomParams
import com.mapsted.ui.MapUiApi
import com.mapsted.ui.MapstedSdkController


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupScreen()
    }

    private fun setupScreen() {
        val mapContainerView = findViewById<FrameLayout>(R.id.mapContainerView)

        // Some sample customization parameters
        // Some sample customization parameters
        CustomParams.newBuilder(this)
            .setBaseMapStyle(BaseMapStyle.LIGHT)
            .setMapPanType(MapPanType.RESTRICT_TO_SELECTED_PROPERTY)
            .setMapZoomRange(MapstedMapRange(6.0f, 25.0f))
            .build()

        // Make sure you only initialize one version of this object in your app
// These can be stored in your activity and/or application
        // Make sure you only initialize one version of this object in your app
// These can be stored in your activity and/or application
        val mapUiApi: MapUiApi = MapstedSdkController.newInstance(this)
        val mapApi: MapApi = mapUiApi.mapApi
        val coreApi: CoreApi = mapApi.coreApi

        // activity is your activity
// mapViewContainer is your view container for the map (typically a frame layout)
        // activity is your activity
// mapViewContainer is your view container for the map (typically a frame layout)
        mapUiApi.initializeMapstedSDK(this, mapContainerView, object : MapstedInitCallback {
            override fun onCoreInitialized() {
                // CoreSDK initialized (This will occur first)
            }

            override fun onMapInitialized() {
                // MapSDK initialized (This will occur second)
            }

            override fun onSuccess() {
                // Initialize succeeded
                // optionally you can initialize the LocationMarketing sdk after this.
            }

            override fun onMessage(messageType: MessageType?, message: String?) {
                //you may receive onMessage before onSucess or onFailure.
                //They can be ignored or used for debugging purpose.
            }

            override fun onFailure(sdkError: SdkError) {
                // Initialize failed
            }
        })

    }
}