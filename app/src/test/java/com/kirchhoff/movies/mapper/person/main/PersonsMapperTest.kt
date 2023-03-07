package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson
import com.kirchhoff.movies.utils.nextString
import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PersonsMapperTest {

    private lateinit var personsMapper: PersonsMapper

    @Before
    fun setUp() {
        personsMapper = PersonsMapper()
    }

    @Test
    fun `verify creating ui persons from error`() {
        val networkError = Result.Error<NetworkPaginated<NetworkPerson>>(errorCode)

        val expectedError = personsMapper.createUIPersons(networkError)

        assertTrue(expectedError is Result.Error)
        assertEquals(errorCode, (expectedError as Result.Error).code)
        assertNull(expectedError.responseBody)
    }

    @Test
    fun `verify creating ui persons from exception`() {
        val networkException = Result.Exception<NetworkPaginated<NetworkPerson>>(exceptionMessage)

        val expectedException = personsMapper.createUIPersons(networkException)

        assertTrue(expectedException is Result.Exception)
        assertEquals(exceptionMessage, (expectedException as Result.Exception).message)
    }

    @Test
    fun `verify creating ui persons with empty result`() {
        val page = Random.nextInt()
        val totalPages = page + Random.nextInt()
        val networkResult = Result.Success(NetworkPaginated(page, emptyList<NetworkPerson>(), totalPages))

        val expectedUIPersons = personsMapper.createUIPersons(networkResult)

        assertTrue(expectedUIPersons is Result.Success)
        assertTrue((expectedUIPersons as Result.Success).data.results.isEmpty())
        assertEquals(expectedUIPersons.data.page, page)
        assertEquals(expectedUIPersons.data.totalPages, totalPages)
    }

    @Test
    fun `verify creating ui persons from not empty network result`() {
        val page = Random.nextInt()
        val totalPages = page + Random.nextInt()
        val person1 = NetworkPerson(Random.nextInt(), Random.nextString(), null)
        val person2 = NetworkPerson(Random.nextInt(), Random.nextString(), Random.nextString())
        val person3 = NetworkPerson(Random.nextInt(), Random.nextString(), Random.toString())
        val personsList = listOf(person1, person2, person3)
        val networkResult = Result.Success(NetworkPaginated(page, personsList, totalPages))

        val expectedUIPersons = personsMapper.createUIPersons(networkResult)

        assertTrue(expectedUIPersons is Result.Success)
        assertTrue((expectedUIPersons as Result.Success).data.results.isNotEmpty())
        assertEquals(expectedUIPersons.data.page, page)
        assertEquals(expectedUIPersons.data.totalPages, totalPages)
        assertEquals(expectedUIPersons.data.results.size, personsList.size)
        assertPersons(expectedUIPersons.data.results[0], person1)
        assertPersons(expectedUIPersons.data.results[1], person2)
        assertPersons(expectedUIPersons.data.results[2], person3)
    }

    private fun assertPersons(
        uiPerson: UIPerson,
        networkPerson: NetworkPerson
    ) {
        assertEquals(networkPerson.id, uiPerson.id)
        assertEquals(networkPerson.name, uiPerson.name)
        assertEquals(networkPerson.profilePath, uiPerson.profilePath)
    }

    companion object {
        private val errorCode = Random.nextInt()
        private val exceptionMessage = Random.nextString()
    }
}
