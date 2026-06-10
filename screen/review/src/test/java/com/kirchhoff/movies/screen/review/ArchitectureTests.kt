package com.kirchhoff.movies.screen.review

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class ArchitectureTests {

    @Test
    fun `all classes in the review module should start with 'Review' prefix`() {
        Konsist
            .scopeFromProduction("screen/review")
            .classes(
                includeNested = false,
                includeLocal = false
            )
            .assert { klass -> klass.name.startsWith("Review") }
    }
}
