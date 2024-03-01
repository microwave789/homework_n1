package com.example.homework_n1.ui

import android.app.Application
import com.example.homework_n1.ioc.ApplicationComponent

// Application instance
class App : Application() {
    val applicationComponent by lazy { ApplicationComponent() }
}
