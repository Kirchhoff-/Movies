package com.kirchhoff.movies.screen.credits

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class ArchitectureTests {

    @Test
    fun `all classes in the credits module should start with 'Credits' prefix`() {
        Konsist
            .scopeFromProduction("screen/credits")
            .classes(
                includeNested = false,
                includeLocal = false
            )
            .assert { klass -> klass.name.startsWith("Credits") }
    }
}
