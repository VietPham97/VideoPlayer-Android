package com.espresslabs.videoplayer.helpers

class MathHelper {

    companion object {

        fun displayTime(totalSeconds: Int) : String {
            val timeFormat = "%02d"
            val minutes = totalSeconds / 60
            val minutesString = String.format(timeFormat, minutes)
            val seconds = totalSeconds % 60
            val secondsString = String.format(timeFormat, seconds)
            return "$minutesString:$secondsString"
        }

    }

}