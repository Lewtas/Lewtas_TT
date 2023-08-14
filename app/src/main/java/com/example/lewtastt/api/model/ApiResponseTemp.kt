package com.example.lewtastt.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//all from https://api.random.org/json-rpc/4/basic

@Serializable
internal data class ApiResponseTemp(
    @SerialName("id")
    val id: Int,
    @SerialName("jsonrpc")
    val jsonrpc: String,
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("advisoryDelay")
        val advisoryDelay: Int,
        @SerialName("bitsLeft")
        val bitsLeft: Int,
        @SerialName("bitsUsed")
        val bitsUsed: Int,
        @SerialName("random")
        val random: Random,
        @SerialName("requestsLeft")
        val requestsLeft: Int
    ) {
        @Serializable
        data class Random(
            @SerialName("completionTime")
            val completionTime: String,
            @SerialName("data")
            val `data`: List<Int>
        )
    }
}