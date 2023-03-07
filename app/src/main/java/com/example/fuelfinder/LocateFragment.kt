package com.example.fuelfinder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LocateFragment : Fragment() {

    companion object {
        fun newInstance() = LocateFragment()
    }

    private lateinit var viewModel: LocateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocateViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreate() {
        super.onCreate()

        configureDependencyInjection()
    }

    private fun configureDependencyInjection() {
        startKoin {
            androidContext(this@LocateFragment)
            modules(searchModule)
        }
    }
}
