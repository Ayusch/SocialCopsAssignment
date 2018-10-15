package com.ayusch.ayuschsocialcops

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pl.tajchert.nammu.Nammu

class SocialCopsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Nammu.init(this)
    }

}
