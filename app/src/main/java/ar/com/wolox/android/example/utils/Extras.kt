package ar.com.wolox.android.example.utils

import android.app.Activity
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import ar.com.wolox.android.example.model.LoginData

/**
 * Util class to store keys to use with [SharedPreferences] or as argument between
 * [Fragment] or [Activity].
 */
object Extras {

    object UserLogin {
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }

    object MockTesting {
        const val INVALID_EMAIL = "camilo.ortiz"
        const val FAIL_EMAIL = "camilo.ortiz@wolox.co"
        const val FAIL_PASSWORD = "123"
        val FAIL_USERDATA = LoginData(FAIL_EMAIL, FAIL_PASSWORD)

        const val EMAIL = "clinton.harris59@example.com"
        const val PASSWORD = "123456"
        val USERDATA = LoginData(EMAIL, PASSWORD)
    }
}
