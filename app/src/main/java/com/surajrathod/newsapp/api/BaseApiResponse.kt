package com.surajrathod.newsapp.api

import android.app.Application
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            } else if (response.errorBody() != null) {
                try {
                    val jsonObject = JSONObject(response.errorBody()!!.string())
                    val firstKey = jsonObject.keys().next()
                    val firstElement  = jsonObject.get(firstKey)
                    if (firstElement is JSONArray){
                        val errorMessage = firstElement.get(0).toString()
                        return error(response.code(), errorMessage)
                    }else if (firstElement is String){
                        return  error(response.code(), firstElement)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
                return error(response.code(), null)
            }
            return error(response.code(), response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            if(e is NoConnectivityException){
                return error(null,e.message)
            }
            return error(null, null)
        }
    }

    private fun <T> error(errorCode: Int?, errorMessage: String?): NetworkResult<T> =
        NetworkResult.Error(null, ErrorResponse().apply {
            this.errorCode = errorCode
            this.errorMessage = errorMessage
        })

}