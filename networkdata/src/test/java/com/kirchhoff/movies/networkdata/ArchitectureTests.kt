package com.kirchhoff.movies.networkdata

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class ArchitectureTests {

    @Test
    fun `all properties in the networkdata module should contain 'SerializedName' annotation`() {
        Konsist.scopeFromModule("networkdata")
            .classes()
            .properties()
            .assert { property -> property.annotations.any { annotation -> annotation.name == "SerializedName" } }
    }
}
