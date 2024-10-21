package com.interview.sample.presentation.ui.screens.movie_detail.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlaybackException
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.drm.DrmSession
import androidx.media3.exoplayer.offline.DownloadHelper
import com.interview.network.helper.ConnectivityObserver
import com.interview.sample.download_manager.DemoUtil
import com.interview.sample.download_manager.DownloadTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

@UnstableApi
@HiltViewModel
class MovieDetailViewModel @OptIn(UnstableApi::class) @Inject constructor(
    val player: ExoPlayer,
    @ApplicationContext private val context: Context,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {
    var isDownloaded = mutableStateOf(false)
        private set

    val isConnected: StateFlow<Boolean> = connectivityObserver.isConnected

    companion object {
        var videoUrl: String =
//            "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8"
            "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"  // Property to hold the video URL
        var drmLicenseUrl: String = "https://your-drm-license-url.com" // DRM License URL
    }

    private var downloadTracker: DownloadTracker? = null

    init {
        downloadTracker = DemoUtil.getDownloadTracker(context)
        if (downloadTracker?.isDownloaded(buildMediaItem(videoUrl, drmLicenseUrl)) == true) {
            isDownloaded.value = true
            // Clear any previously set media items
            player.clearMediaItems()
            val mediaSource = downloadTracker?.getDownloadRequest(Uri.parse(videoUrl))!!.let {
                DownloadHelper.createMediaSource(
                    it,
                    DemoUtil.getDataSourceFactory(context)
                )
            }
            player.setMediaSource(mediaSource)
            player.prepare()
        } else {
            isDownloaded.value = false

            preparePlayer(
                url = videoUrl, drmLicenseUrl = drmLicenseUrl
            )
            setPlayerListener()
        }
    }

    // Create MediaSource for HLS streaming
    private fun buildMediaItem(videoUrl: String, drmLicenseUrl: String): MediaItem {
        val widevineUuid = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed")
        return MediaItem.Builder().setUri(videoUrl).setDrmConfiguration(
            MediaItem.DrmConfiguration.Builder(widevineUuid).setLicenseUri(drmLicenseUrl)
                .build()
        ).setMediaId("dummyId").setMediaMetadata(
            MediaMetadata.Builder().setTitle("Demo Video").build()
        ).build()
    }

    @OptIn(UnstableApi::class)
    fun handlePlaybackError(error: PlaybackException) {
        when (error) {
            is ExoPlaybackException -> {
                when (error.type) {
                    ExoPlaybackException.TYPE_SOURCE -> {
                        if (error.sourceException is DrmSession.DrmSessionException) {
                            val drmException =
                                error.sourceException as DrmSession.DrmSessionException
                            Log.e("DRM_ERROR", "DRM error: ${drmException.message}")
                        } else {
                            // Handle other source-related errors
                            Log.e("PLAYER_ERROR", "Source error: ${error.sourceException.message}")
                        }
                        // Handle errors related to the media source (e.g., network, unsupported media)
                        Log.e("PLAYER_ERROR", "Source error: ${error.sourceException.message}")
                    }

                    ExoPlaybackException.TYPE_RENDERER -> {
                        // Handle renderer-related errors (e.g., codec errors)
                        Log.e("PLAYER_ERROR", "Renderer error: ${error.rendererException.message}")
                    }

                    ExoPlaybackException.TYPE_UNEXPECTED -> {
                        // Handle unexpected errors
                        Log.e(
                            "PLAYER_ERROR", "Unexpected error: ${error.unexpectedException.message}"
                        )
                    }

                    ExoPlaybackException.TYPE_REMOTE -> {
                        // Handle remote errors (related to remote processes)
                        Log.e("PLAYER_ERROR", "Remote error")
                    }

                    else -> {
                        Log.e("PLAYER_ERROR", "Unknown error: ${error.message}")
                    }
                }
            }

            else -> {
                Log.e("PLAYER_ERROR", "Non-ExoPlayer error: ${error.message}")
            }
        }
    }

    private fun preparePlayer(url: String, drmLicenseUrl: String) {
        // Clear any previously set media items
        player.clearMediaItems()

        if (player.playbackState == Player.STATE_IDLE) {
            player.setMediaItem(buildMediaItem(url, drmLicenseUrl))
            player.prepare()  // Prepare the media
        }
    }

    private fun setPlayerListener() {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                // Log or handle the error
                handlePlaybackError(error)
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> Log.d("PlayerState", "Buffering...")
                    Player.STATE_READY -> Log.d("PlayerState", "Ready to play")
                    Player.STATE_ENDED -> Log.d("PlayerState", "Playback ended")
                    Player.STATE_IDLE -> Log.d("PlayerState", "Player idle")
                }
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        if (player.playbackState != Player.STATE_IDLE) {
            player.stop()
            player.clearMediaItems()
        }
        connectivityObserver.unregisterCallback()
    }

    @OptIn(UnstableApi::class)
    fun downloadVideo(supportFragmentManager: FragmentManager) {
        val renderersFactory = DemoUtil.buildRenderersFactory(context)
        downloadTracker?.toggleDownload(
            supportFragmentManager, player.currentMediaItem, renderersFactory
        )
    }

}