package com.ayusch.ayuschsocialcops.fragments


import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder

import com.ayusch.ayuschsocialcops.R
import com.ayusch.ayuschsocialcops.Utils.Utils
import com.ayusch.ayuschsocialcops.fragments.base.BaseFragment
import com.ayusch.ayuschsocialcops.mvpcontract.VideoPlayerContract
import com.sachinchandil.videodownloadandplay.VideoDownloadAndPlayService
import pl.tajchert.nammu.Nammu
import pl.tajchert.nammu.PermissionCallback
import java.io.File


class VideoPlayerFragment : BaseFragment(), VideoPlayerContract.View {


    lateinit var videoPlayServie: VideoDownloadAndPlayService
    val url = "https://socialcops.com/images/old/spec/home/header-img-background_video-1920-480.mp4"
    var presenter: VideoPlayerContract.Presenter? = null

    @BindView(R.id.videoView)
    lateinit var videoView: VideoView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_video_player, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initializing the presenter
        presenter = VideoPlayerPresenter(this, activity)
        (presenter as VideoPlayerPresenter).initP();
    }

    override fun initV() {


        videoView.setOnCompletionListener {
            it.reset()
            videoView.setVideoURI(Uri.parse(Utils.getSdCardDirectory()))
        }

        // checking if the permissions exist
        presenter?.checkPermission()
    }

    override fun noPermissionError() {
        //if permissions are denied this toast is displayed.
        "No permissions granted, sorry!!".toast(context)
    }

    override fun playVideo() {
        var file = File(Utils.getSdCardDirectory())

        //if file has already been downloaded play directly
        if (file.exists()) {
            videoView.setVideoURI(Uri.parse(Utils.getSdCardDirectory()))
            videoView.setOnPreparedListener {
                val mediaController = MediaController(context)
                videoView.setMediaController(mediaController);
                videoView.start()
                mediaController.setAnchorView(videoView)
                videoView.start()
            }

        } else {
            // if file is not downloaded, stream it
            videoPlayServie = VideoDownloadAndPlayService.startServer(activity, url, Utils.getSdCardDirectory(), "127.0.0.1") { url ->
                videoView.setVideoURI(Uri.parse(url))
                videoView.setOnPreparedListener {
                    //Setting up media controls
                    val mediaController = MediaController(context)
                    videoView.setMediaController(mediaController);
                    videoView.start()
                    mediaController.setAnchorView(videoView)
                }
            }


        }
    }

    // video is saved in the root of external storage
    // Name of the video file is "video.mp4"


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Utils.openFolder(activity)
        return super.onOptionsItemSelected(item)
    }

}

//Kotlin extension function to create a toast easily
private fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

