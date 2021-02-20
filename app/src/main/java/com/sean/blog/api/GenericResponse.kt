package com.sean.blog.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenericResponse {

    @SerializedName("response")
    @Expose
    lateinit var response: String
}