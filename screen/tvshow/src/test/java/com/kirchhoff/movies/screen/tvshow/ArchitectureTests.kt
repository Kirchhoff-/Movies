package com.kirchhoff.movies.screen.tvshow

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class ArchitectureTests {

    @Test
    fun `all classes in the tvshow module should start with 'TvShow' prefix`() {
        Konsist
            .scopeFromProduction("screen/tvshow")
            .classes(
                includeNested = false,
                includeLocal = false
            )
            .assert { klass -> klass.name.startsWith("TvShow") }
    }
}
