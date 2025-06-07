/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.api

import com.ideacode.apmtools.lib.BuildConfig;

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.api
 * @ClassName:      Env
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/2 12:01
 * @UpdateUser:
 * @UpdateDate:     2025/6/2 12:01
 * @UpdateRemark:
 * @Version:        1.0
 */
object Env {

    val APM_TAG = "Ideacode_APM_Tools"

    val VERSION = BuildConfig.VERSION

    fun getVersionName(): String {
        return VERSION
    }

}