package com.jdamcd.instabugrxissue

import android.app.Application
import com.instabug.library.invocation.InstabugInvocationEvent
import com.instabug.library.Instabug

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
    }
}
