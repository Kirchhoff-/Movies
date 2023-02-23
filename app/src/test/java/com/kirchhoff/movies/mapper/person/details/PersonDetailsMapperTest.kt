package com.kirchhoff.movies.mapper.person.details

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredit
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCastCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCrewCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.utils.nextString
import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PersonDetailsMapperTest {

    private lateinit var personDetailsMapper: PersonDetailsMapper

    @Before
    fun setUp() {
        personDetailsMapper = PersonDetailsMapper()
    }

    @Test
    fun `verify creating person details from error`() {
        val networkError = Result.Error<NetworkPersonDetails>(errorCode)

        val expectedError = personDetailsMapper.createUIPersonDetails(networkError)

        assertTrue(expectedError is Result.Error)
        assertEquals(errorCode, (expectedError as Result.Error).code)
        assertNull(expectedError.responseBody)
    }

    @Test
    fun `verify creating person details from exception`() {
        val networkException = Result.Exception<NetworkPersonDetails>(exceptionMessage)

        val expectedException = personDetailsMapper.createUIPersonDetails(networkException)

        assertTrue(expectedException is Result.Exception)
        assertEquals(exceptionMessage, (expectedException as Result.Exception).message)
    }

    @Test
    fun `verify creating person details from network person details`() {
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(null, null, null))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(null, null, emptyList()))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(null, null, listOf(Random.nextString(), Random.nextString())))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(null, Random.nextString(), null))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(null, Random.nextString(), emptyList()))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(null, Random.nextString(), listOf(Random.nextString(), Random.nextString())))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(Random.nextString(), null, null))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(Random.nextString(), null, emptyList()))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(Random.nextString(), null, listOf(Random.nextString(), Random.nextString())))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(Random.nextString(), Random.nextString(), null))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(Random.nextString(), Random.nextString(), emptyList()))
        verifyPersonDetailsResults(createNetworkPersonDetailsResult(Random.nextString(), Random.nextString(), listOf(Random.nextString(), Random.nextString())))
    }

    @Test
    fun `verify creating person credits from error`() {
        val networkError = Result.Error<NetworkPersonCredits>(errorCode)

        val expectedError = personDetailsMapper.createUIPersonCredits(networkError)

        assertTrue(expectedError is Result.Error)
        assertEquals(errorCode, (expectedError as Result.Error).code)
        assertNull(expectedError.responseBody)
    }

    @Test
    fun `verify creating person credits from exception`() {
        val networkException = Result.Exception<NetworkPersonCredits>(exceptionMessage)

        val expectedException = personDetailsMapper.createUIPersonCredits(networkException)

        assertTrue(expectedException is Result.Exception)
        assertEquals(exceptionMessage, (expectedException as Result.Exception).message)
    }

    @Test
    fun `verify creating person credits with null cast credits and null crew credits`() {
        val networkPersonCreditsResult = Result.Success(NetworkPersonCredits(null, null))

        val expectedUICredits =
            personDetailsMapper.createUIPersonCredits(networkPersonCreditsResult)

        assertTrue(expectedUICredits is Result.Success)
        assertNull((expectedUICredits as Result.Success).data.cast)
        assertNull(expectedUICredits.data.crew)
    }

    @Test(expected = IllegalStateException::class)
    fun `verify exception for wrong media type in cast credits`() {
        val networkPersonCreditsResult = Result.Success(
            NetworkPersonCredits(
                listOf(createNetworkPersonCastCredits(mediaType = badMediaType)),
                null
            )
        )

        personDetailsMapper.createUIPersonCredits(networkPersonCreditsResult)
    }

    @Test(expected = IllegalStateException::class)
    fun `verify exception for wrong media type in crew credits`() {
        val networkPersonCreditsResult = Result.Success(
            NetworkPersonCredits(
                null,
                listOf(createNetworkPersonCrewCredits(mediaType = badMediaType))
            )
        )

        personDetailsMapper.createUIPersonCredits(networkPersonCreditsResult)
    }

    @Test
    fun `verify creating ui credits from network credits`() {
        val cast1 = createNetworkPersonCastCredits(Random.nextString(), mediaType = movieMediaType)
        val cast2 = createNetworkPersonCastCredits(Random.nextString(), mediaType = tvMediaType)
        val cast3 = createNetworkPersonCastCredits(Random.nextString(), Random.nextString(), Random.nextString(), Random.nextString(), movieMediaType)

        val crew1 = createNetworkPersonCrewCredits(Random.nextString(), mediaType = movieMediaType)
        val crew2 = createNetworkPersonCrewCredits(Random.nextString(), mediaType = tvMediaType)
        val crew3 = createNetworkPersonCrewCredits(Random.nextString(), Random.nextString(), Random.nextString(), movieMediaType)

        val castList = listOf(cast1, cast2, cast3)
        val crewList = listOf(crew1, crew2, crew3)

        val networkPersonCreditsResult = Result.Success(NetworkPersonCredits(castList, crewList))

        val expectedUICredits = personDetailsMapper.createUIPersonCredits(networkPersonCreditsResult)

        assertTrue(expectedUICredits is Result.Success)
        assertNotNull((expectedUICredits as Result.Success).data.cast)
        assertNotNull(expectedUICredits.data.crew)
        assertEquals(expectedUICredits.data.cast?.size, castList.size)
        assertEquals(expectedUICredits.data.crew?.size, crewList.size)
        assertPersonActor(expectedUICredits.data.cast?.get(0)!!, cast1)
        assertPersonActor(expectedUICredits.data.cast?.get(1)!!, cast2)
        assertPersonActor(expectedUICredits.data.cast?.get(2)!!, cast3)
        assertPersonCreator(expectedUICredits.data.crew?.get(0)!!, crew1)
        assertPersonCreator(expectedUICredits.data.crew?.get(1)!!, crew2)
        assertPersonCreator(expectedUICredits.data.crew?.get(2)!!, crew3)
    }

    private fun createNetworkPersonDetailsResult(
        birthday: String?,
        placeOfBirth: String?,
        alsoKnownAs: List<String>?
    ) = Result.Success(
        NetworkPersonDetails(
            birthday,
            placeOfBirth,
            Random.nextString(),
            alsoKnownAs
        )
    )

    private fun verifyPersonDetailsResults(
        networkResult: Result<NetworkPersonDetails>
    ) {
        val uiResult = personDetailsMapper.createUIPersonDetails(networkResult)
        assertTrue(networkResult is Result.Success)
        assertTrue(uiResult is Result.Success)
        assertPersonDetails((networkResult as Result.Success).data, (uiResult as Result.Success).data)
    }

    private fun assertPersonDetails(
        networkPersonDetails: NetworkPersonDetails,
        uiPersonDetails: UIPersonDetails
    ) {
        assertEquals(uiPersonDetails.birthday, networkPersonDetails.birthday)
        assertEquals(uiPersonDetails.placeOfBirth, networkPersonDetails.placeOfBirth)
        assertEquals(uiPersonDetails.biography, uiPersonDetails.biography)
        assertEquals(uiPersonDetails.alsoKnownAs, networkPersonDetails.alsoKnownAs)
    }

    private fun createNetworkPersonCastCredits(
        title: String? = null,
        character: String? = null,
        posterPath: String? = null,
        backdropPath: String? = null,
        mediaType: String
    ) = NetworkPersonCastCredit(
        Random.nextInt(),
        title,
        character,
        posterPath,
        backdropPath,
        mediaType
    )

    private fun createNetworkPersonCrewCredits(
        title: String? = null,
        posterPath: String? = null,
        backdropPath: String? = null,
        mediaType: String
    ) = NetworkPersonCrewCredit(
        Random.nextInt(),
        title,
        Random.nextString(),
        posterPath,
        backdropPath,
        mediaType
    )

    private fun assertPersonActor(
        uiPersonActor: UIPersonCredit.Actor,
        networkPersonCastCredits: NetworkPersonCastCredit
    ) {
        assertEquals(uiPersonActor.id, networkPersonCastCredits.id)
        assertEquals(uiPersonActor.title, networkPersonCastCredits.title)
        assertEquals(uiPersonActor.posterPath, networkPersonCastCredits.posterPath)
        assertEquals(uiPersonActor.backdropPath, networkPersonCastCredits.backdropPath)
        assertEquals(uiPersonActor.mediaType.name, networkPersonCastCredits.mediaType)
        assertEquals(uiPersonActor.character, networkPersonCastCredits.character)
    }

    private fun assertPersonCreator(
        uiPersonCreator: UIPersonCredit.Creator,
        networkPersonCrewCredits: NetworkPersonCrewCredit
    ) {
        assertEquals(uiPersonCreator.id, networkPersonCrewCredits.id)
        assertEquals(uiPersonCreator.title, networkPersonCrewCredits.title)
        assertEquals(uiPersonCreator.posterPath, networkPersonCrewCredits.posterPath)
        assertEquals(uiPersonCreator.backdropPath, networkPersonCrewCredits.backdropPath)
        assertEquals(uiPersonCreator.mediaType.name, networkPersonCrewCredits.mediaType)
        assertEquals(uiPersonCreator.job, networkPersonCrewCredits.job)
    }

    companion object {
        private val errorCode = Random.nextInt()
        private val exceptionMessage = Random.nextString()
        private val badMediaType = Random.nextString()
        private val movieMediaType = "MOVIE"
        private val tvMediaType = "TV"
    }
}
