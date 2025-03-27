package com.form

import android.content.Context
import android.view.View
import com.facebook.react.ReactApplication
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.ReactContext

object RNViewManager {
  private var reactInstanceManager: ReactInstanceManager? = null

  fun getReactNativeView(context: Context): View {
    val applicationContext = context.applicationContext
    val reactNativeHost = (applicationContext as? ReactApplication)?.reactNativeHost
      ?: throw IllegalStateException("React Native Host not found!")

    if (reactInstanceManager == null) {
      reactInstanceManager = reactNativeHost.reactInstanceManager
    }

    // ✅ Ensure React Native bridge is properly initialized
    if (!reactInstanceManager!!.hasStartedCreatingInitialContext()) {
      reactInstanceManager!!.createReactContextInBackground()
    }

    // ✅ Create ReactRootView and load the app
    val reactRootView = ReactRootView(context).apply {
      layoutParams = android.view.ViewGroup.LayoutParams(
        android.view.ViewGroup.LayoutParams.MATCH_PARENT,
        android.view.ViewGroup.LayoutParams.WRAP_CONTENT
      )
      startReactApplication(reactInstanceManager, "MyFormNative", null)
    }

    return reactRootView
  }
}
