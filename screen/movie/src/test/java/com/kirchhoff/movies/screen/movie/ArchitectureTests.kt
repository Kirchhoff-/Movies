package com.kirchhoff.movies.screen.movie

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class ArchitectureTests {

    @Test
    fun `all classes in the movie module should start with 'Movie' prefix`() {
        Konsist
            .scopeFromProduction("screen/movie")
            .classes(
                includeNested = false,
                includeLocal = false
            )
            .assertTrue { klass -> klass.name.startsWith("Movie") }
    }
}
