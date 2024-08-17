package com.mishra.group15

data class FlightInfo(
    val airlineName: String,
    val arrivalTime: String,
    val terminalNumber: String,
    val status: Boolean = true
)