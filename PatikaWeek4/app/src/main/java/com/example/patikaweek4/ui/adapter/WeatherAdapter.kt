package com.example.patikaweek4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patikaweek4.data.model.Daily
import com.example.patikaweek4.databinding.ItemWeatherCardBinding
import com.squareup.picasso.Picasso

class WeatherAdapter : ListAdapter<Daily, WeatherAdapter.WeatherViewHolder>(WeatherDiffUtil()) {

    class WeatherViewHolder(private val itemWeatherCardBinding: ItemWeatherCardBinding) :
        RecyclerView.ViewHolder(itemWeatherCardBinding.root) {
        fun bind(daily: Daily) {
            val icon = daily.weather?.get(0)?.icon
            val maxTemp = daily.temp?.max
            val minTemp = daily.temp?.min
            itemWeatherCardBinding.apply {
                tvName.text = daily.weather?.get(0)?.id.toString()
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