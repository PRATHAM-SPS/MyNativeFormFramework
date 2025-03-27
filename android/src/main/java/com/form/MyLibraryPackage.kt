package com.form

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class MyLibraryPackage : ReactPackage {
    private var rnEventEmitter: RNEventEmitter? = null  // ✅ Store instance

    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        rnEventEmitter = RNEventEmitter(reactContext)  // ✅ Initialize and store
        return listOf(rnEventEmitter!!)
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> =
        emptyList()
}
