package br.com.noartcode.breeze.android.data.remote.mapper

import br.com.noartcode.breeze.android.data.local.entity.PhotoEntity
import br.com.noartcode.breeze.android.data.remote.dto.PhotoDto

fun PhotoDto.toEntity() : PhotoEntity =
    PhotoEntity(
        originalId = this.id,
        photographerId = this.photographer?.id ?: "",
        altDescription = this.altDescription,
        description = this.description,
        color = this.color,
        createdAt = this.createdAt,
        width = this.width,
        height = this.height,
        likes = this.likes,
        urls = PhotoEntity.UrlsEntity(
            full = this.urls.full,
            raw = this.urls.raw,
            regular = this.urls.regular,
            small = this.urls.small,
            thumb = this.urls.thumb
        ),
    )