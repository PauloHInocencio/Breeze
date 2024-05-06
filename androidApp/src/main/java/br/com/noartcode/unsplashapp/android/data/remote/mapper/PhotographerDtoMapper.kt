package br.com.noartcode.unsplashapp.android.data.remote.mapper

import br.com.noartcode.unsplashapp.android.data.local.entity.PhotographerEntity
import br.com.noartcode.unsplashapp.android.data.remote.dto.PhotographerDto

fun PhotographerDto.toEntity() : PhotographerEntity =
    PhotographerEntity(
        originalId = this.id,
        bio = this.bio,
        name = this.name,
        firstname = this.firstname,
        instagramUsername = this.instagramUsername,
        lastname = this.lastname,
        location = this.location,
        profileImage =  PhotographerEntity.ProfileImageEntity(
            large = this.profileImage.large,
            medium = this.profileImage.medium,
            small = this.profileImage.small
        ),
        totalLikes = this.totalLikes,
        totalPhotos = this.totalPhotos
    )
