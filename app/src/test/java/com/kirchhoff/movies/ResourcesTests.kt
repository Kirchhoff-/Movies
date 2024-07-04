package com.kirchhoff.movies

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertNot
import org.junit.Test

class ResourcesTests {

    @Test
    fun `should not use compose Color class`() {
        val temporaryDisabled = setOf(
            "VoteViewComposable",
            "RatingTextComposable",
            "LinkifyText"
        )

        Konsist.scopeFromProject()
            .files
            .filterNot { it.containsObject { objectName -> objectName.name == "Colors" } }
            .filterNot { temporaryDisabled.contains(it.name) }
            .assertNot { fileDeclaration ->
                fileDeclaration.hasImportWithName("androidx.compose.ui.graphics.Color")
            }
    }

    @Test
    fun `should not use colorResources method`() {
        Konsist.scopeFromProject()
            .files
            .assertNot { fileDeclaration ->
                fileDeclaration.hasImportWithName("androidx.compose.ui.res.colorResource")
            }
    }
}
