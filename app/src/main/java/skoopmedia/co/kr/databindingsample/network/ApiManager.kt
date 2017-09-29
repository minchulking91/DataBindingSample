package skoopmedia.co.kr.databindingsample.network

import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import skoopmedia.co.kr.databindingsample.Comment
import java.util.concurrent.TimeUnit

/**
 * Created by MinChulKim on 2017-09-29.
 */
class ApiManager {

    private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    private val service: ApiService

    init {
        //network
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        service = retrofit.create(ApiService::class.java)
    }

    fun getComments(onResponse: ((result: List<Comment>) -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null) {
        service.comments().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onResponse?.invoke(it) }, { onError?.invoke(it) } )
    }

    object LazyHolder {
        val INSTANCE = ApiManager()
    }

    companion object {
        val instance: ApiManager by lazy { LazyHolder.INSTANCE }
    }
}