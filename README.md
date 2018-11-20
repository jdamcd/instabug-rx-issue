This project demonstrates an issue where initialising the Instabug SDK alters RxJava behaviour.

Without the Instabug setup in `App.kt`, the app will crash via the `doSomethingWrong()` call in `MainActivity`. With Instabug initialised, the exception will be swallowed silently. The app does not halt, but the remainder of the code in `onSuccess()` will not execute, leaving us in a non-deterministic state.
