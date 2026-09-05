package com.example.youtubeclone.model

data class Video(
    val id: String,
    val title: String,
    val channelName: String,
    val channelAvatarUrl: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val views: String,
    val uploadedAt: String,
    val duration: String,
    val description: String = ""
)
