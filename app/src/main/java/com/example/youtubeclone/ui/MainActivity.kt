package com.example.youtubeclone.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeclone.adapter.VideoAdapter
import com.example.youtubeclone.data.FakeData
import com.example.youtubeclone.databinding.ActivityMainBinding
import com.example.youtubeclone.model.Video

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupBottomNav()
    }

    private fun setupRecyclerView() {
        binding.rvVideos.layoutManager = LinearLayoutManager(this)
        binding.rvVideos.adapter = VideoAdapter(FakeData.videos) { video ->
            openPlayer(video)
        }
    }

    private fun openPlayer(video: Video) {
        val intent = Intent(this, PlayerActivity::class.java).apply {
            putExtra("VIDEO_ID", video.id)
            putExtra("TITLE", video.title)
            putExtra("CHANNEL", video.channelName)
            putExtra("VIEWS", video.views)
            putExtra("UPLOADED", video.uploadedAt)
            putExtra("VIDEO_URL", video.videoUrl)
            putExtra("THUMBNAIL", video.thumbnailUrl)
            putExtra("AVATAR", video.channelAvatarUrl)
            putExtra("DESCRIPTION", video.description)
        }
        startActivity(intent)
    }

    private fun setupBottomNav() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            // Simple demo - just stay on home for now
            true
        }
        binding.bottomNav.selectedItemId = com.example.youtubeclone.R.id.nav_home
    }
}
