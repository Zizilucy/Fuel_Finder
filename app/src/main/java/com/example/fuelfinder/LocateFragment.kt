package com.example.fuelfinder

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class LocateFragment : Fragment(){

    companion object {
        fun newInstance() = LocateFragment()
    }

    private lateinit var viewModel: LocateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocateViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.fragment_locate)
    val button: Button = findViewById(R.id.locate_fuel_stations)
    button.setOnClickListener {
        val intent = Intent(this, MapsActivity2::class.java)
                startActivity(intent)
    }
}



