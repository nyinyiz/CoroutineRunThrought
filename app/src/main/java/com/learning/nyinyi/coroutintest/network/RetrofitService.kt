package com.learning.nyinyi.coroutintest.network

import com.learning.nyinyi.coroutintest.model.OwnerData
import retrofit2.Response
import retrofit2.http.*
import java.security.acl.Owner

interface RetrofitService {
    @FormUrlEncoded
    @POST("search")
    suspend fun getCarNumberData(
        @Field("key") key : String,
        @Field("query") carNumber : String
    ) : Response<OwnerData>
}