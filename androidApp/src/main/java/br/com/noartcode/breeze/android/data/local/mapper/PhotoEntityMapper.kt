package br.com.noartcode.breeze.android.data.local.mapper

import br.com.noartcode.breeze.android.data.local.entity.PhotoEntity
import br.com.noartcode.breeze.android.domain.model.Photo

fun PhotoEntity.toDomain() : Photo =
    Photo(
        id = this.id,
        originalId = this.originalId,
        url = this.urls.regular
    )