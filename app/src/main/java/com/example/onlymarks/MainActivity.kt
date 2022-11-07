package com.example.onlymarks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.onlymarks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("XXX", "Result OK")
            val data: Intent? = result.data
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // when clicking the start swiping button, go to the new swipes intent
        binding.contentMain.startSwipingButton.setOnClickListener {
            val swipeCarouselIntent = Intent(this, SwipeCarousel::class.java)
            val extras = Bundle()
            swipeCarouselIntent.putExtras(extras)
            resultLauncher.launch(swipeCarouselIntent)
        }
    }
}