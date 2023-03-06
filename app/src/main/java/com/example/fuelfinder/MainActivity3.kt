package com.example.fuelfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity3: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation)
        val locateFragment = LocateFragment ()
        val walletFragment = WalletFragment ()
        val notifyFragment = NotifyFragment ()
        val settingsFragment = SettingsFragment ()
        setCurrentFragment (locateFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.locate->setCurrentFragment(locateFragment)
                R.id.wallet->setCurrentFragment(walletFragment)
                R.id.notify->setCurrentFragment(notifyFragment)
                R.id.settings->setCurrentFragment(settingsFragment)

            }
            true
        }
    }
    private fun setCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment.fragment)
            commit()
        }
}