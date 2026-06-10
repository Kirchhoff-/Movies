package com.kirchhoff.movies.screen.person

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class ArchitectureTests {

    @Test
    fun `all classes in the person module should start with 'Person' prefix`() {
        Konsist
            .scopeFromProduction("screen/person")
            .classes(
                includeNested = false,
                includeLocal = false
            )
            .assert { klass -> klass.name.startsWith("Person") }
    }
}
