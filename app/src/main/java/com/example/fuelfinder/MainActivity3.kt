package com.example.fuelfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity3: AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        val locateFragment = LocateFragment()
        val walletFragment = WalletFragment()
        val notifyFragment = NotifyFragment()
        val settingsFragment = SettingsFragment()
        setCurrentFragment(locateFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.locate -> setCurrentFragment(locateFragment)
                R.id.wallet -> setCurrentFragment(walletFragment)
                R.id.notify -> setCurrentFragment(notifyFragment)
                R.id.settings -> setCurrentFragment(settingsFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}