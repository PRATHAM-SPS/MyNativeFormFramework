package com.form

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.facebook.react.ReactApplication
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.google.gson.Gson

interface RNEventEmitterDelegate {
    fun onFormSubmit(data: WritableMap)

}

class RNEventEmitter(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  companion object {
    private var sharedInstance: RNEventEmitter? = null
    private var sharedDelegate: RNEventEmitterDelegate? = null
    private var storedApiData: WritableMap? = null // ✅ Stored API Data
    private const val PREFS_NAME = "RNEventEmitterPrefs"

    fun getInstance(): RNEventEmitter? {
      return sharedInstance
    }
    // SharedPreferences
    private fun getPreferences(context: ReactApplicationContext): SharedPreferences {
      return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Helper method to convert WritableMap to JSON String
    private fun writableMapToJson(writableMap: WritableMap): String {
      val json = Gson().toJson(writableMap.toHashMap()) // Convert to HashMap first, then to JSON
      return json
    }

    // Helper method to convert JSON String back to WritableMap
    private fun jsonToWritableMap(json: String): WritableMap {
      // Deserialize to a generic Map
      val hashMap: Map<*, *> = Gson().fromJson(json, Map::class.java)

      // Cast Map<*, *> to Map<String, Any> explicitly
      val typedMap = hashMap as? Map<String, Any> ?: throw IllegalArgumentException("Invalid map type")

      val writableMap = Arguments.createMap()
      for ((key, value) in typedMap) {
        writableMap.putString(key, value.toString()) // Store as string (adjust for other types as needed)
      }
      Log.d("RNEventEmitter", "✅ removeListeners called: $writableMap")
      return writableMap
    }


    fun setSharedDelegate(delegate: RNEventEmitterDelegate) {
      sharedDelegate = delegate
      Log.d("RNEventEmitter", "✅ Android: Shared delegate set!")
    }
  }

  init {
    sharedInstance = this
  }

  override fun getName(): String {
    return "RNEventEmitter"
  }

  @ReactMethod
  fun addListener(eventName: String) {
    Log.d("RNEventEmitter", "✅ addListener called for event: $eventName ")
  }

  @ReactMethod
  fun removeListeners(count: Int) {
    Log.d("RNEventEmitter", "✅ removeListeners called: $count")
  }

  fun storeApiDataa( apiData: WritableMap) {
    val reactContext = sharedInstance?.reactApplicationContext
    val json = writableMapToJson(apiData)
    val editor = getPreferences(reactContext!!).edit()
    editor.putString("storedApiData", json)  // Store the JSON string in SharedPreferences
    editor.apply()
    Log.d("RNEventEmitter", "📡 API response stored in SharedPreferences -> $json")
  }

  // Retrieve the API data from SharedPreferences
  @ReactMethod
  fun fetchApiData(promise: Promise) {
    val reactContext = sharedInstance?.reactApplicationContext
    val json = getPreferences(reactContext!!).getString("storedApiData", null)
    Log.d("RNEventEmitter", "📡 API response fetched from SharedPreferences -> $json")

    if (json != null) {
      // If data is found, resolve the promise with the data
      promise.resolve(json)
    } else {
      // If no data is available, reject the promise with an error
      promise.reject("E_NO_DATA", "No API data available")
    }
  }


  // ✅ Store API Data (Called from native Android)
  fun setApiResponse(apiData: WritableMap) {
    storedApiData = apiData
    Log.d("RNEventEmitter", "📡 Android: API response stored -> $storedApiData")
  }
  fun setApiResponseWheelchair(apiData: WritableMap) {
    val reactContext = sharedInstance?.reactApplicationContext
    val json = writableMapToJson(apiData)
    val editor = getPreferences(reactContext!!).edit()
    editor.putString("storedApiDataWheelchair", json)  // Store the JSON string in SharedPreferences
    editor.apply()
    Log.d("RNEventEmitter", "📡 API response stored in SharedPreferences -> $json")
  }
  fun setApiResponseInseat(apiData: WritableMap) {
    val reactContext = sharedInstance?.reactApplicationContext
    val json = writableMapToJson(apiData)
    val editor = getPreferences(reactContext!!).edit()
    editor.putString("storedApiDataInseat", json)  // Store the JSON string in SharedPreferences
    editor.apply()
    Log.d("RNEventEmitter", "📡 API response stored in SharedPreferences -> $json")
  }


  // ✅ Fetch API Data (Called from React Native)
  @ReactMethod
  fun fetchApiResponse(promise: Promise) {
    Log.d("RNEventEmitter", "🚀 Android: Sending stored API data to React Native -> $storedApiData")
    storedApiData?.let {
      promise.resolve(it) // ✅ Send API Data to React Native
    } ?: promise.reject("E_NO_DATA", "No API data available")
  }

  @ReactMethod
  fun fetchApiResponseWheelchair(promise: Promise) {
    val reactContext = sharedInstance?.reactApplicationContext
    val json = getPreferences(reactContext!!).getString("storedApiDataWheelchair", null)
    Log.d("RNEventEmitter", "📡 API response fetched from SharedPreferences -> $json")

    if (json != null) {
      // If data is found, resolve the promise with the data
      promise.resolve(json)
    } else {
      // If no data is available, reject the promise with an error
      promise.reject("E_NO_DATA", "No API data available")
    }
  }

  @ReactMethod
  fun fetchApiResponseInseat(promise: Promise) {
    val reactContext = sharedInstance?.reactApplicationContext
    val json = getPreferences(reactContext!!).getString("storedApiDataInseat", null)
    Log.d("RNEventEmitter", "📡 API response fetched from SharedPreferences -> $json")

    if (json != null) {
      // If data is found, resolve the promise with the data
      promise.resolve(json)
    } else {
      // If no data is available, reject the promise with an error
      promise.reject("E_NO_DATA", "No API data available")
    }
  }

  // ✅ Send Form Data to Android Delegate
  @ReactMethod
  fun sendFormData(formData: ReadableMap) {
    Log.d("RNEventEmitter", "📩 Received form data from React Native: $formData")

    if (sharedDelegate == null) {
      Log.e("RNEventEmitter", "❌ Error: No delegate set! Cannot forward event to native.")
      return
    }

    val writableMap = Arguments.createMap().apply {
      merge(formData)
    }

    // ✅ Ensure delegate is not null before calling the method
    sharedDelegate?.onFormSubmit(writableMap)
  }

  // ✅ Send event from Android to React Native
  fun sendEvent(eventName: String, params: WritableMap?) {

    val reactContext = reactApplicationContext

    Log.d("RNEventEmitter", "🛠 sendEvent called for event: $eventName with data: $params")
    if (!reactContext.hasActiveCatalystInstance()) {
      Log.w("RNEventEmitter", "⚠️ CatalystInstance is not active. Skipping event.")
      return  // 🔥 Prevents unnecessary restart loops
    }

    Log.d("RNEventEmitter", "📡 Sending event '$eventName' to React Native with data: $params")
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
      .emit(eventName, params)
  }
}
