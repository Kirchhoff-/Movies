package com.kirchhoff.movies.screen.review.ui.transition

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private const val REVIEW_TRANSITION_DURATION_MILLIS = 300

@SuppressLint("ModifierFactoryExtensionFunction")
internal class ReviewsTransitionScope(
    val sharedTransitionScope: SharedTransitionScope,
    val animatedVisibilityScope: AnimatedVisibilityScope,
    private val reviewKey: String
) {
    @Composable
    fun avatarModifier(): Modifier {
        with(sharedTransitionScope) {
            val sharedState = rememberSharedContentState(key = reviewKey to "avatar")
            return Modifier.sharedElement(
                sharedContentState = sharedState,
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = { _, _ -> tween(REVIEW_TRANSITION_DURATION_MILLIS) }
            )
        }
    }

    @Composable
    fun authorModifier(): Modifier = sharedTransitionScope.createBoundsModifier(
        visibilityScope = animatedVisibilityScope,
        elementKey = "author"
    )

    @Composable
    fun ratingModifier(): Modifier = sharedTransitionScope.createBoundsModifier(
        visibilityScope = animatedVisibilityScope,
        elementKey = "rating"
    )

    @Composable
    fun contentModifier(): Modifier = sharedTransitionScope.createBoundsModifier(
        visibilityScope = animatedVisibilityScope,
        elementKey = "content"
    )

    @Composable
    private fun SharedTransitionScope.createBoundsModifier(
        visibilityScope: AnimatedVisibilityScope,
        elementKey: String
    ): Modifier {
        val sharedState = rememberSharedContentState(key = reviewKey to elementKey)
        return Modifier.sharedBounds(
            sharedContentState = sharedState,
            animatedVisibilityScope = visibilityScope,
            boundsTransform = { _, _ -> tween(REVIEW_TRANSITION_DURATION_MILLIS) },
            enter = fadeIn(tween(REVIEW_TRANSITION_DURATION_MILLIS)),
            exit = fadeOut(tween(REVIEW_TRANSITION_DURATION_MILLIS)),
            resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds(alignment = Alignment.TopStart)
        )
    }

    companion object {
        val contentTransform: ContentTransform
            get() = fadeIn(tween(REVIEW_TRANSITION_DURATION_MILLIS)) togetherWith
                fadeOut(tween(REVIEW_TRANSITION_DURATION_MILLIS))
    }
}
