package br.com.noartcode.unsplashapp.android.ui.extensions

import androidx.compose.foundation.lazy.grid.LazyGridState

internal fun LazyGridState.reachedBottom(buffer: Int = 1) : Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}