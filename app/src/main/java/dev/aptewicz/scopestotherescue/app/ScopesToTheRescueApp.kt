package dev.aptewicz.scopestotherescue.app

import android.app.Application
import dev.aptewicz.scopestotherescue.library.store.AppStore

class ScopesToTheRescueApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppStore.init()
    }
}
