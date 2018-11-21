package com.jdamcd.instabugrxissue

import android.app.Application
import android.util.Log
import com.instabug.library.invocation.InstabugInvocationEvent
import com.instabug.library.Instabug
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        /*
         * Initialising Instabug registers an error handler via RxJavaPlugins.setErrorHandler()
         * This changes behaviour for the host app as well as for RxJava usages within the SDK
         */
        Instabug.Builder(this, "48ad905e141bc665d064945f423aa414")
                .setInvocationEvents(InstabugInvocationEvent.SHAKE)
                .build()

        Log.e("XXX", RxJavaPlugins.getErrorHandler()?.javaClass?.simpleName ?: "No RxJavaPlugins error handler")

        /*
         * With the 8.0.11.4-SNAPSHOT version, RxJava behaviour is not altered. No RxJavaPlugins error handler is set.
         * However, the Instabug SDK now crashes internally from system onLowMemory() callbacks. Reproduction steps:
         * 1. Open the app
         * 2. Exit to the home screen
         * 3. `adb shell am send-trim-memory com.jdamcd.instabugrxissue HIDDEN`
         */
    }
}
