package br.com.noartcode.unsplashapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform