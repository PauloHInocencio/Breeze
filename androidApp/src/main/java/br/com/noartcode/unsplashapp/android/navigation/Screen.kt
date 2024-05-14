package br.com.noartcode.unsplashapp.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Random: Screen() {

        @Serializable
        data object List:Screen()

        @Serializable
        data class Detail(val id:Long): Screen()
    }

    @Serializable
    data object Search: Screen() {

        @Serializable
        data object List:Screen()

        @Serializable
        data class Detail(val id:Long): Screen()
    }

    @Serializable
    data object Linked: Screen() {

        @Serializable
        data object List:Screen()

        @Serializable
        data class Detail(val id:Long): Screen()
    }

}