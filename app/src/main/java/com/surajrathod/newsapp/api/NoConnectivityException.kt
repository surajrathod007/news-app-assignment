package com.surajrathod.newsapp.api

import android.content.Context
import java.io.IOException

class NoConnectivityException(val context: Context) : IOException() {
    override val message: String
        get() = "The Internet connection appears to be offline."
}
