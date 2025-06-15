/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.nativelib

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.nativelib
 * @ClassName:      NativeLib
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/15 18:21
 * @UpdateUser:
 * @UpdateDate:     2025/6/15 18:21
 * @UpdateRemark:
 * @Version:        1.0
 */

object NativeLib {

    init {
        System.loadLibrary("mock_native_fun")
    }

    external fun testMallocLeak()
}