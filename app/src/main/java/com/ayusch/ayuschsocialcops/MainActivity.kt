package com.ayusch.ayuschsocialcops

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.VideoView
import com.sachinchandil.videodownloadandplay.VideoDownloadAndPlayService

import android.widget.MediaController
import com.ayusch.ayuschsocialcops.fragments.VideoPlayerFragment
import kotlinx.android.synthetic.main.activity_main.*

import pl.tajchert.nammu.Nammu
import pl.tajchert.nammu.PermissionCallback
import java.io.File


class MainActivity : AppCompatActivity() {

    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        Nammu.init(this)

        fragment = VideoPlayerFragment()
        if (supportFragmentManager.findFragmentById(R.id.content_frame) == null) {
            supportFragmentManager.beginTransaction().add(R.id.content_frame, fragment as VideoPlayerFragment).commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_open_download) {
            (fragment as VideoPlayerFragment).onOptionsItemSelected(item)
            return true
        } else
            return super.onOptionsItemSelected(item)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    override fun onPause() {
        super.onPause()
    }


}
