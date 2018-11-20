package com.jdamcd.instabugrxissue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private var disposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        disposable = CompositeDisposable().also { it.add(doSomethingWithRxJava()) }
    }

    private fun doSomethingWithRxJava(): Disposable {
        return Single.just("result")
                .subscribe({
                    toast("onSuccess: $it")
                    doSomethingWrong()
                    toast("This code never executes, but the app didn't halt, so we're left in a non-deterministic state")
                }, { e -> toast("onError: ${e.message}") })
    }

    private fun doSomethingWrong() {
        throw RuntimeException("This should crash the main thread but is silently swallowed")
    }

    override fun onPause() {
        disposable?.dispose()
        super.onPause()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
