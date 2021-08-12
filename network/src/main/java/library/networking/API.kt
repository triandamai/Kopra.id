package library.networking

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("")
    fun getUsers(): Call<ResponseBody>

}