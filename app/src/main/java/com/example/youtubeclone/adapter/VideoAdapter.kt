package com.example.youtubeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeclone.databinding.ItemVideoBinding
import com.example.youtubeclone.model.Video

class VideoAdapter(
    private val videos: List<Video>,
    private val onVideoClick: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.tvTitle.text = video.title
            binding.tvChannel.text = video.channelName
            binding.tvViews.text = "${video.views} views • ${video.uploadedAt}"
            binding.tvDuration.text = video.duration

            Glide.with(binding.root.context)
                .load(video.thumbnailUrl)
                .centerCrop()
                .into(binding.ivThumbnail)

            Glide.with(binding.root.context)
                .load(video.channelAvatarUrl)
                .circleCrop()
                .into(binding.ivChannel)

            binding.root.setOnClickListener {
                onVideoClick(video)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int = videos.size
}
