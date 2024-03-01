package com.example.homework_n1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


// Main Activity, has the Fragment Container
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Getting rid of the top bar
        supportActionBar?.hide()

        // navController to navigate through fragments
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as NavHostFragment
        navController = navHostFragment.navController
    }
}