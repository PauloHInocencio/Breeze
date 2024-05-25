package br.com.noartcode.breeze

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform