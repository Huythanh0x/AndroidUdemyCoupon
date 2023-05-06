package com.example.androidudemycoupon.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object TimeLeft {
    @RequiresApi(Build.VERSION_CODES.O)
    fun timeFromLastUpdate(fetchedTime: String): String {
        val differentInMinute = getTimeDifferentInMinute(fetchedTime)
        return when (differentInMinute) {
            in 0..1 -> "$differentInMinute minute ago"
            in 2..59 -> "  ${differentInMinute / 60} minutes ago"
            in 60..60 * 24 -> "${differentInMinute / 60 / 24} hours ago"
            else -> "Long time ago"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTimeDifferentInMinute(startTime: String): Long {
        if (startTime == "") return 0
        val startInstant = LocalDateTime.parse(startTime).toInstant(ZoneOffset.UTC)
        val endInstant = Instant.now()
        return (endInstant.toEpochMilli() - startInstant.toEpochMilli()) / (1000 * 60)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDurationFromNow(dateTimeString: String): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX")
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)
        val inputInstant = dateTime.atZone(ZoneId.systemDefault()).toInstant()
        val now = Instant.now()
        return Duration.between(now, inputInstant).toMinutes()
    }
}