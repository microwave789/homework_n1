package com.example.homework_n1

import android.app.Application
import com.example.homework_n1.ApplicationComponent

// Application instance
class App : Application() {
    val applicationComponent by lazy { ApplicationComponent() }
}
