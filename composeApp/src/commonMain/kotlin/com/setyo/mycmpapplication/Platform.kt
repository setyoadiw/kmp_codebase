package com.setyo.mycmpapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform