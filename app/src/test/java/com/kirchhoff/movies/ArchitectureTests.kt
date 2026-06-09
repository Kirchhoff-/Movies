package com.kirchhoff.movies

import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
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
    fun `'Service' interfaces shouldn't have parent`() {
        Konsist.scopeFromProject()
            .interfaces()
            .filter { it.hasNameEndingWith("Service") }
            .assert { !it.hasParents() }
    }

    @Test
    fun `'Repository' classes should have a corresponding base class`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .filterNot { it.name == "BaseRepository" }
            .assert {
                it.numParents == 1 &&
                    it.hasParentWithName("BaseRepository")
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
    fun `functions in 'Repository' classes should return only the instance of the 'RepositoryResult' class`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .filterNot { klass -> klass.name == "BaseRepository" }
            .assert { interfaceDeclaration ->
                interfaceDeclaration.containingFile.hasImportWithName("com.kirchhoff.movies.core.repository.RepositoryResult") &&
                    interfaceDeclaration.functions().all { it.returnType?.hasNameStartingWith("RepositoryResult") == true }
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

    @Test
    fun `methods with '@Preview' annotation should have a 'private' visibility modifier`() {
        Konsist.scopeFromProject()
            .files
            .flatMap { it.functions() }
            .withAnnotationOf(Preview::class)
            .assert { it.hasPrivateModifier }
    }

    @Test
    fun `ViewModels classes should be located in the 'viewmodel' package`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .assert { it.resideInPackage("..viewmodel") }
    }

    @Test
    fun `data classes should not have constructor parameters with default values`() {
        Konsist.scopeFromProject()
            .classes()
            .filter { it.hasDataModifier }
            .forEach { dataClass ->
                dataClass.primaryConstructor?.parameters?.forEach { param ->
                    assert(
                        value = !param.hasDefaultValue(),
                        lazyMessage = { "Data class '${dataClass.name}' constructor parameter '${param.name}' has a default value" }
                    )
                }
            }
    }

    @Test
    fun `Repository public methods should not use fetch prefix`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .filter { klass -> klass.hasNameEndingWith("Repository") }
            .flatMap { klass -> klass.functions() }
            .filterNot { functions -> functions.hasPrivateModifier }
            .assert { function -> !function.hasNameStartingWith("fetch") }
    }

    @Test
    fun `data classes should have only val properties`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .filter { klass -> klass.hasDataModifier }
            .assert { klass ->
                klass.properties().all { property ->
                    property.hasValModifier
                }
            }
    }
}
