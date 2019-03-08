package com.espresslabs.videoplayer

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_video_fullscreen.*

class VideoFullscreenActivity : AppCompatActivity() {

    private lateinit var mainLayout: ConstraintLayout
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_fullscreen)
        setUpLayout()
        setUpMediaPlayer()
    }

    private fun setUpMediaPlayer() {
        videoView = video_view
        videoView.setVideoPath("android.resource://$packageName/${R.raw.httyd3}")
//        videoView.setOnPreparedListener(onVideoPreparedListener)
//        videoView.setOnCompletionListener(onVideoCompletionListener)
        playVideo()
    }

    private fun playVideo() {
        videoView.start()
    }

    private fun pauseVideo() {

    }

    private fun setUpLayout() {
        mainLayout = layout_main
        mainLayout.setBackgroundColor(Color.GRAY)

        window.decorView.setOnSystemUiVisibilityChangeListener(onSystemUiVisibilityChangeListener)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private val onSystemUiVisibilityChangeListener = View.OnSystemUiVisibilityChangeListener { visibility ->
        // Note that system bars will only be "visible" if none of the
        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set
        if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
            // TODO: The system bars are visible. Make any desired
            // adjustments to your UI, such as showing the action bar or
            // other navigational controls.
            Log.i("Fullscreen", "system bars VISIBLE")
            Handler().postDelayed({
                this.hideSystemUI()
            }, 3000)
        } else {
            // TODO: The system bars are NOT visible. Make any desired
            // adjustments to your UI, such as hiding the action bar or
            // other navigational controls.
            Log.i("Fullscreen", "system bars INVISIBLE - NOT VISIBLE")
        }
    }

    private fun hideSystemUI() {
        // enable 'lean back' mode
        window.decorView.systemUiVisibility = (
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
