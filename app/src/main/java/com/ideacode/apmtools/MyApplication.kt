/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.ideacode.apmtools.lib.api.IdeacodeApmClient
import com.ideacode.apmtools.lib.core.ConfigBuilder

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools
 * @ClassName:      MyApplication
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/12 00:01
 * @UpdateUser:
 * @UpdateDate:     2025/6/12 00:01
 * @UpdateRemark:
 * @Version:        1.0
 */
class MyApplication : Application() {


    val handler: Handler = Handler(Looper.getMainLooper())

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        handler.postDelayed({ initApm()}, 2000)
    }

    fun initApm() {
        val builder = ConfigBuilder.newBuilder()
        builder.setDebugLogOpen(true)
        builder.setMonitorMemory(true)

        val config = builder.build()

        IdeacodeApmClient.attach(config)
        IdeacodeApmClient.startWork()
    }

}