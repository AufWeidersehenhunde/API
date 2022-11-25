package com.example.api

import retrofit2.Response

data class ResponseExcep<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
){
    companion object{
        fun<T> success(data: Response<T>):ResponseExcep<T>{
            return ResponseExcep(
                status = Status.Success,
                data = data,
                exception = null
            )
        }
        fun<T> failed(exception:Exception):ResponseExcep<T>{
            return ResponseExcep(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }


    sealed class Status{
        object Success: Status()
        object Failure:Status()
    }

    val failed:Boolean
    get() = this.status == Status.Failure

    val isSuccessful: Boolean
    get() = !!failed && this.data?.isSuccessful == true

    val body:T
    get() = this.data!!.body()!!



}
