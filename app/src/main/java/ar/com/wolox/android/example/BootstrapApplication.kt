package ar.com.wolox.android.example

import ar.com.wolox.android.BuildConfig
import ar.com.wolox.android.example.BaseConfiguration.Companion.HEADER_CLIENT
import ar.com.wolox.android.example.BaseConfiguration.Companion.HEADER_TOKEN
import ar.com.wolox.android.example.BaseConfiguration.Companion.HEADER_UID
import ar.com.wolox.android.example.di.DaggerAppComponent
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.WolmoApplication
import ar.com.wolox.wolmo.networking.di.DaggerNetworkingComponent
import ar.com.wolox.wolmo.networking.di.NetworkingComponent
import com.google.gson.FieldNamingPolicy
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import javax.inject.Inject

class BootstrapApplication : WolmoApplication() {

    @Inject
    lateinit var userSession: UserSession

    override fun onInit() {
        // Initialize Application stuff here
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    override fun applicationInjector(): AndroidInjector<BootstrapApplication> {
        return DaggerAppComponent.builder().networkingComponent(buildDaggerNetworkingComponent())
                .sharedPreferencesName(BaseConfiguration.SHARED_PREFERENCES_NAME).application(this)
                .create(this)
    }

    private fun buildDaggerNetworkingComponent(): NetworkingComponent {
        val builder = DaggerNetworkingComponent.builder().baseUrl(
                BaseConfiguration.EXAMPLE_CONFIGURATION_KEY)
                .gsonNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        val interceptors = mutableListOf(authInterceptor())

        if (BuildConfig.DEBUG) {
            interceptors.add(buildHttpLoggingInterceptor(Level.BODY))
            interceptors.add(ChuckInterceptor(this))
        }

        builder.okHttpInterceptors(*interceptors.toTypedArray())
        return builder.build()
    }

    private fun authInterceptor() = Interceptor { chain ->
        var request = chain.request()
        if (!request.url.encodedPath.equals("/auth/sign_in", ignoreCase = true)) {
            userSession.tokenInfo?.let {
                request = request.newBuilder()
                    .addHeader(HEADER_TOKEN, it.token.toString())
                    .addHeader(HEADER_CLIENT, it.client.toString())
                    .addHeader(HEADER_UID, it.uid.toString())
                    .build()
            }
        }

        chain.proceed(request)
    }

    /**
     * Returns a [HttpLoggingInterceptor] with the newLevel given by **newLevel**.
     *
     * @param newLevel - Logging newLevel for the interceptor.
     * @return New instance of interceptor
     */
    private fun buildHttpLoggingInterceptor(newLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { this.level = newLevel }
    }
}
