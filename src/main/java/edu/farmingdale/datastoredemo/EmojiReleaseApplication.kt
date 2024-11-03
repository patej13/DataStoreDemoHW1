package edu.farmingdale.datastoredemo

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import edu.farmingdale.datastoredemo.data.local.UserPreferencesRepository


private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private const val THEME_PREFERENCE_NAME = "theme_preferences"
private val Context.layoutDataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)

private val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(
    name = THEME_PREFERENCE_NAME
)
class EmojiReleaseApplication: Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(layoutDataStore)
    }
}