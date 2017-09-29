package skoopmedia.co.kr.databindingsample.network

import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import skoopmedia.co.kr.databindingsample.Comment

/**
 * Created by MinChulKim on 2017-09-29.
 */
interface ApiService {

    @GET("/comments") //used
    fun comments(): Observable<List<Comment>>
}