package com.kirchhoff.movies.screen.review.ui.screen.list.ui.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ReviewsAuthorBottomSheet(
    author: ReviewUIInfo.Author?,
    onDismiss: () -> Unit,
    onOpenProfileClick: (String) -> Unit,
    content: @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(author) {
        if (author != null) {
            sheetState.show()
            snapshotFlow { sheetState.currentValue }.first { it == ModalBottomSheetValue.Hidden }
            onDismiss()
        } else {
            sheetState.hide()
        }
    }

    BackHandler(enabled = author != null) {
        scope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = Colors.White,
        sheetContent = {
            if (author != null) {
                ReviewsAuthorSheetUI(
                    author = author,
                    onOpenProfileClick = onOpenProfileClick
                )
            } else {
                Spacer(Modifier.height(1.dp))
            }
        },
        content = content
    )
}
