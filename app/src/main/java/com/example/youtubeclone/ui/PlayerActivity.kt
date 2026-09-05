package com.example.youtubeclone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.youtubeclone.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("TITLE") ?: ""
        val channel = intent.getStringExtra("CHANNEL") ?: ""
        val views = intent.getStringExtra("VIEWS") ?: ""
        val uploaded = intent.getStringExtra("UPLOADED") ?: ""
        val videoUrl = intent.getStringExtra("VIDEO_URL") ?: ""
        val avatar = intent.getStringExtra("AVATAR") ?: ""
        val description = intent.getStringExtra("DESCRIPTION") ?: ""

        binding.tvPlayerTitle.text = title
        binding.tvPlayerChannel.text = channel
        binding.tvPlayerViews.text = "$views views • $uploaded"
        binding.tvDescription.text = description

        Glide.with(this)
            .load(avatar)
            .circleCrop()
            .into(binding.ivPlayerChannel)

        setupPlayer(videoUrl)
    }

    private fun setupPlayer(url: String) {
        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            binding.playerView.player = exoPlayer
            val mediaItem = MediaItem.fromUri(url)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        }
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}
