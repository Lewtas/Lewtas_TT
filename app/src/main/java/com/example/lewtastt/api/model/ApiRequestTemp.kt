package com.example.lewtastt.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//all from https://api.random.org/json-rpc/4/basic
@Serializable
internal data class ApiRequestTemp(
    @SerialName("id")
    val id: Int,
    @SerialName("jsonrpc")
    val jsonrpc: String,
    @SerialName("method")
    val method: String,
    @SerialName("params")
    val params: Params
) {
    @Serializable
    data class Params(
        @SerialName("apiKey")
        val apiKey: String,
        @SerialName("max")
        val max: Int,
        @SerialName("min")
        val min: Int,
        @SerialName("n")
        val n: Int
    )
}