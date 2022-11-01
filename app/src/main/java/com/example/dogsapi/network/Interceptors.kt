package com.example.dogsapi.network

import android.content.Context
import com.example.dogsapi.NetworkUtils
import com.example.dogsapi.hasNetwork
import okhttp3.CacheControl.*
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Paoneking on 28/05/2021.
 */
class Interceptors {
    /**
     * Interceptor to cache data and maintain it for a minute.
     *
     *
     * If the same network request is sent within a minute,
     * the response is retrieved from cache.
     */
    class ResponseCacheInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalResponse = chain.proceed(chain.request())
            return originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=" + 60 * 2)
                .build()
        }
    }

    class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())
            val cacheControl = Builder()
                .maxAge(10, TimeUnit.DAYS)
                .build()
            return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }

    class ForceCacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder: Request.Builder = chain.request().newBuilder()
            if (!NetworkUtils.hasNetwork()) {
                builder.cacheControl(FORCE_CACHE);
            }
            return chain.proceed(builder.build());
        }
    }

    /**
     * Interceptor to cache data and maintain it for one weeks.
     *
     *
     * If the device is offline, stale (at most four one old)
     * response is fetched from the cache.
     */
    class OfflineResponseCacheInterceptor(private val mContext: Context) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            if (!mContext.hasNetwork()) {
                val maxStale = (60 * 60 * 24 * 7).toLong() // tolerate 4-weeks stale
                request = request.newBuilder()
                    .removeHeader("Pragma")
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$maxStale"
                    )
                    .build()
            }
            return chain.proceed(request)
        }
    }
}