package com.example.patikaweek4.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patikaweek4.data.model.Daily
import com.example.patikaweek4.databinding.ItemWeatherCardBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate

class WeatherAdapter : ListAdapter<Daily, WeatherAdapter.WeatherViewHolder>(WeatherDiffUtil()) {

    class WeatherViewHolder(private val itemWeatherCardBinding: ItemWeatherCardBinding) :
        RecyclerView.ViewHolder(itemWeatherCardBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(daily: Daily) {
            val icon = daily.weather?.get(0)?.icon
            val maxTemp = daily.temp?.max
            val minTemp = daily.temp?.min
            val tomorrow = LocalDate.now().plusDays(1)
            itemWeatherCardBinding.apply {
                tvName.text = tomorrow.toString()
                    tvName.text = tomorrow.plusDays(1).toString()

                if (maxTemp != null) {
                    tvTempMax.text = (Math.round(maxTemp * 1.0) / 1.0).toInt().toString()
                }
                if (minTemp != null) {
                    tvTempMin.text = (Math.round(minTemp * 1.0) / 1.0).toInt().toString()
                }

                Picasso.get().load("https://openweathermap.org/img/wn/${icon}@2x.png").into(ivWeather);
            }




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemWeatherCardBinding = ItemWeatherCardBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(itemWeatherCardBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class WeatherDiffUtil : DiffUtil.ItemCallback<Daily>() {
        override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem == newItem
        }

    }
}