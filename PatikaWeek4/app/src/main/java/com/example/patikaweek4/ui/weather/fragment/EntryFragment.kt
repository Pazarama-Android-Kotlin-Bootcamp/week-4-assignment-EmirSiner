package com.example.patikaweek4.ui.weather.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.patikaweek4.R
import com.example.patikaweek4.data.model.WeatherX
import com.example.patikaweek4.databinding.FragmentEntryBinding
import com.example.patikaweek4.ui.adapter.WeatherAdapter

class EntryFragment : Fragment() {
    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentEntryBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.entryBtn.setOnClickListener {
            onClicked()
        }



    }
   private fun onClicked() {
        val lat =binding.entryTwLat.text.toString().toFloat()
        val lng =binding.entryTwLon.text.toString().toFloat()
        findNavController().navigate(EntryFragmentDirections.actionEntryFragmentToWeatherFragment(lat, lng))


    }


}