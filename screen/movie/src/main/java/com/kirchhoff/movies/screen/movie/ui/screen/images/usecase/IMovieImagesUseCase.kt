package com.kirchhoff.movies.screen.movie.ui.screen.images.usecase

import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.repository.Result

internal interface IMovieImagesUseCase {
    suspend fun fetchImages(movieId: Int): Result<List<UIImage>>
}
