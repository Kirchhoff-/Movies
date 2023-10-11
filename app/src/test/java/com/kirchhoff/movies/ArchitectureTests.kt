package com.kirchhoff.movies

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutSealedModifier
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class ArchitectureTests {

    @Test
    fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
        Konsist.scopeFromProject()
            .classes()
            .withAllParentsOf(ViewModel::class)
            .assert { it.name.endsWith("ViewModel") }
    }

    @Test
    fun `'ScreenState' classes should be located in 'model' package`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("ScreenState")
            .assert { it.resideInPackage("..model") }
    }

    @Test
    fun `interfaces (not service and listener) should start with 'I'`() {
        Konsist.scopeFromProject()
            .interfaces()
            .withoutSealedModifier()
            .filterNot { it.hasNameEndingWith("Service") }
            .filterNot { it.hasNameEndingWith("Listener") }
            .assert { it.hasNameStartingWith("I") }
    }

    @Test
    fun `'Service' interfaces shouldn't have parent`() {
        Konsist.scopeFromProject()
            .interfaces()
            .filter { it.hasNameEndingWith("Service") }
            .assert { !it.hasParents() }
    }

    @Test
    fun `'Repository' classes should have a corresponding base class and an interface`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .filterNot { it.name == "BaseRepository" }
            .assert {
                it.numParents == 2 &&
                it.hasParentWithName("BaseRepository") &&
                it.parents.any { parent -> parent.hasNameStartingWith("I") }
            }
    }

    @Test
    fun `'Repository' classes should be located in 'repository' package`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .assert { it.resideInPackage("..repository") }
    }

    @Test
    fun `functions in 'Repository' classes should return only the instance of the 'Result' class`() {
        Konsist.scopeFromProject()
            .interfaces()
            .withNameEndingWith("Repository")
            .assert { interfaceDeclaration ->
                interfaceDeclaration.containingFile.hasImportWithName("com.kirchhoff.movies.core.repository.Result") &&
                interfaceDeclaration.functions().all { it.returnType?.hasNameStartingWith("Result") == true }
            }
    }

    @Test
    fun `'Router' classes should be located in 'repository' package`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("Router")
            .assert { it.resideInPackage("..router") }
    }

    @Test
    fun `'Service' interfaces should contain only annotated functions`() {
        Konsist.scopeFromProject()
            .interfaces()
            .withNameEndingWith("Service")
            .assert { interfaceDeclaration ->
                interfaceDeclaration.functions().all { it.annotations.isNotEmpty() }
            }
    }

    @Test
    fun `'Fragment' classes should have a corresponding base class`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("Fragment")
            .filterNot { it.name == "BaseFragment" }
            .assert { it.hasParentWithName("BaseFragment") || it.hasParentWithName("PaginatedScreenFragment") }
    }
}
