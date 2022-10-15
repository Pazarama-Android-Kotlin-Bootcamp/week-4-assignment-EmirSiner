package com.example.patikaweek4.ui.weather.fragment

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.patikaweek4.data.api.ApiClient
import com.example.patikaweek4.data.model.WeatherX
import com.example.patikaweek4.databinding.FragmentWeatherBinding
import com.example.patikaweek4.ui.adapter.WeatherAdapter
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<WeatherFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDailyWeather()
    }


    private fun getDailyWeather() {
        ApiClient.getApiService().getDailyWeather(args.lat.toString(),args.lng.toString(),"metric").enqueue(object : Callback<WeatherX> {
            override fun onResponse(call: Call<WeatherX>, response: Response<WeatherX>) {
                Log.d("WeatherFragment", response.body().toString())

                if (response.isSuccessful) {
                    val weather = response.body()
                    weather?.let {
                        binding.rvWeather.adapter = WeatherAdapter().apply {
                            submitList(it.daily)
                        }
                        Log.d("WeatherFragment", it.daily?.get(0)?.weather?.get(0)?.description.toString())
                    }
                }
            }

            override fun onFailure(call: Call<WeatherX>, t: Throwable) {
                Log.d("WeatherFragment", t.toString())
            }
        })
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    private fun setImageViewHeight() {
        val paramsll = _binding?.ivWeather?.layoutParams
        paramsll?.height = getScreenHeight() / 4
        binding.ivWeather.layoutParams = paramsll

        val paramsvr = _binding?.rvWeather?.layoutParams
        paramsvr?.height = 2 * (getScreenHeight() / 4)
        binding.rvWeather.layoutParams = paramsvr
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}