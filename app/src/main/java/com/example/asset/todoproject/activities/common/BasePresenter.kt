/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 04/07/2018.
 */

package com.example.asset.todoproject.activities.common


interface BasePresenter<V> {

    var view: V?
    /**
     * Contains common setup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    fun start() {}

    /**
     * Contains common cleanup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    fun stop() {
        view = null
    }
}