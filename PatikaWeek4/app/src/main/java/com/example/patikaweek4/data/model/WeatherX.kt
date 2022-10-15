package com.example.patikaweek4.data.model
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue


data class  WeatherX(
  @SerializedName("current")
    val current:  @RawValue Current?,
  @SerializedName("daily")
    val daily:  @RawValue List<Daily>?,
  @SerializedName("hourly")
    val hourly:  @RawValue List<Hourly>?,
  @SerializedName("lat")
    val lat: Double?,
  @SerializedName("lon")
    val lon: Double?,
  @SerializedName("timezone")
    val timezone: String?,
  @SerializedName("timezone_offset")
    val timezoneOffset: Int?
)