/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 09/07/2018.
 */

package com.example.asset.todoproject.activities.common

/**
 * Base View interface
 */
interface BaseView<out P : BasePresenter<*>> {
    val presenter: P
}