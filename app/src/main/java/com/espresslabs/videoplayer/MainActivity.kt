package com.espresslabs.videoplayer

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainLayout: ConstraintLayout
    private lateinit var openVideoPlayerButton: Button
    private lateinit var openVideoFullscreenButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("Main", "onCreate")

        mainLayout = layout_main
        mainLayout.setBackgroundColor(Color.WHITE)

        openVideoPlayerButton = button1
        openVideoPlayerButton.setOnClickListener(openVideoPlayer)

        openVideoFullscreenButton = button2
        openVideoFullscreenButton.setOnClickListener(openVideoFullscreenPlayer)
    }

    private var openVideoPlayer = View.OnClickListener {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        startActivity(intent)
    }

    private var openVideoFullscreenPlayer = View.OnClickListener {
        val intent = Intent(this, VideoFullscreenActivity::class.java)
        startActivity(intent)
    }
}

