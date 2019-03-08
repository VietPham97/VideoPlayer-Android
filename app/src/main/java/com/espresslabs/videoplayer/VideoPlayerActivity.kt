package com.espresslabs.videoplayer

import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import com.espresslabs.videoplayer.helpers.MathHelper
import kotlinx.android.synthetic.main.activity_video_player.*
import java.util.*

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var mainLayout: ConstraintLayout
    private lateinit var playbackControlLayout: ConstraintLayout
    private lateinit var playPauseButton: ImageButton
    private lateinit var videoSeekBar: SeekBar
    private lateinit var currentTimeTextView: TextView
    private lateinit var totalTimeTextView: TextView

    private var isPlaying = false
    private var durationInSeconds = 0

    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        setUpLayout()
        setUpPlaybackControl()
        setUpMediaPlayer()
    }

    private fun setUpLayout() {
        mainLayout = layout_main
        mainLayout.setBackgroundColor(Color.WHITE)
    }

    private fun setUpPlaybackControl() {
        playbackControlLayout = playback_control_layout

        currentTimeTextView = current_time_text
        totalTimeTextView = total_time_text

        videoSeekBar = video_seek_bar
        videoSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener)

        playPauseButton = play_pause_button
        playPauseButton.setOnClickListener(onPressPlayPause)
    }

    private fun setUpMediaPlayer() {
        videoView = video_view
        videoView.setVideoPath("android.resource://$packageName/${R.raw.httyd3}")
        videoView.setOnPreparedListener(onVideoPreparedListener)
        videoView.setOnCompletionListener(onVideoCompletionListener)
        playVideo()
    }

    private val timerTask = object : TimerTask() {

        override fun run() {
            if (isPlaying) {
                val currentTime = (videoView.currentPosition / 1000) % 60
                Log.i("Video", "Current Time: $currentTime")
                videoSeekBar.progress = currentTime
            }
            else {
                Log.i("Video", "Pause")
//                cancel()
            }
        }

    }

    private fun playVideo() {
        isPlaying = true
        videoView.start()
        playPauseButton.setBackgroundResource(R.drawable.ic_pause_white)
        timer.scheduleAtFixedRate(timerTask, 0, 1000)
    }

    private fun pauseVideo() {
        isPlaying = false
        videoView.pause()
        playPauseButton.setBackgroundResource(R.drawable.ic_play_white)
    }

    private val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//            Log.i("Video", "progress: $progress")
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }

    }

    private val onVideoPreparedListener = MediaPlayer.OnPreparedListener { mediaPlayer ->
        durationInSeconds = (mediaPlayer.duration / 1000) % 60
//        Log.i("Video", "duration: $durationInSeconds")
        videoSeekBar.max = durationInSeconds
        totalTimeTextView.text = MathHelper.displayTime(durationInSeconds)
    }

    private val onVideoCompletionListener = MediaPlayer.OnCompletionListener { mediaPlayer ->
        Log.i("Video", "Completed")
        timer.cancel()
    }

    private val onPressPlayPause = View.OnClickListener {
        if (isPlaying) {
            pauseVideo()
        } else {
            playVideo()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Video", "onDestroy")
        timer.cancel()
    }
}
