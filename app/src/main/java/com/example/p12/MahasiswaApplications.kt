package com.example.p12

import android.app.Application
import com.example.p12.Container.AppContainer
import com.example.p12.Container.MahasiswaContainer
import com.example.p12.model.Mahasiswa

class MahasiswaApplications:Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container=MahasiswaContainer()
    }
}