package br.com.noartcode.unsplashapp.android.data.local.mapper

import br.com.noartcode.unsplashapp.android.data.local.entity.PhotoEntity
import br.com.noartcode.unsplashapp.android.domain.model.Photo

fun PhotoEntity.toDomain() : Photo =
    Photo(
        id = this.originalId,
        url = this.urls.regular
    )