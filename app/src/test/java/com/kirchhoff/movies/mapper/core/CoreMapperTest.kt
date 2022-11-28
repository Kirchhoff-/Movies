package com.kirchhoff.movies.mapper.core

import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentPerson
import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentPerson
import com.kirchhoff.movies.networkdata.core.NetworkObjectWithName
import com.kirchhoff.movies.utils.nextString
import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CoreMapperTest {

    private lateinit var coreMapper: CoreMapper

    @Before
    fun setUp() {
        coreMapper = CoreMapper()
    }

    @Test
    fun `verify creating UIGenre object from NetworkObjectWithName object`() {
        val firstGenre = Random.nextString(10)
        val secondGenre = Random.nextString(11)
        val thirdGenre = Random.nextString(12)
        val forthGenre = Random.nextString(13)
        val fifthGenre = Random.nextString(14)

        val firstObject = NetworkObjectWithName(firstGenre)
        val secondObject = NetworkObjectWithName(secondGenre)
        val thirdObject = NetworkObjectWithName(thirdGenre)
        val forthObject = NetworkObjectWithName(forthGenre)
        val fifthObject = NetworkObjectWithName(fifthGenre)

        assertEquals(UIGenre(firstGenre), coreMapper.createUIGenre(firstObject))
        assertEquals(UIGenre(secondGenre), coreMapper.createUIGenre(secondObject))
        assertEquals(UIGenre(thirdGenre), coreMapper.createUIGenre(thirdObject))
        assertEquals(UIGenre(forthGenre), coreMapper.createUIGenre(forthObject))
        assertEquals(UIGenre(fifthGenre), coreMapper.createUIGenre(fifthObject))
    }

    @Test
    fun `verify creating ui entertainment credits with null values from network entertainment credits with null values`() {
        assertEquals(
            UIEntertainmentCredits(null, null),
            coreMapper.createUIEntertainmentCredits(NetworkEntertainmentCredits(null, null))
        )
    }

    @Test
    fun `verify creating ui entertainment credits with empty values from network entertainment credits with empty values`() {
        assertEquals(
            UIEntertainmentCredits(emptyList(), emptyList()),
            coreMapper.createUIEntertainmentCredits(
                NetworkEntertainmentCredits(
                    emptyList(),
                    emptyList()
                )
            )
        )
    }

    @Test
    fun `verify creating ui entertainment credits with not empty cast credits and null crew credits`() {
        val networkCastCredits = listOf(firstNetworkActor, secondNetworkActor, thirdNetworkActor)
        val networkCredits = NetworkEntertainmentCredits(networkCastCredits, null)
        val expectedUICredits =
            UIEntertainmentCredits(listOf(firstUIActor, secondUIActor, thirdUIActor), null)

        assertEquals(expectedUICredits, coreMapper.createUIEntertainmentCredits(networkCredits))
    }

    @Test
    fun `verify creating ui entertainment credits with not empty cast credits and empty crew credits`() {
        val networkCastCredits = listOf(thirdNetworkActor, firstNetworkActor, secondNetworkActor)
        val networkCredits = NetworkEntertainmentCredits(networkCastCredits, emptyList())
        val expectedUICredits =
            UIEntertainmentCredits(listOf(thirdUIActor, firstUIActor, secondUIActor), emptyList())

        assertEquals(expectedUICredits, coreMapper.createUIEntertainmentCredits(networkCredits))
    }

    @Test
    fun `verify creating ui entertainment credits with not empty crew credits and null cast credits`() {
        val networkCrewCredits =
            listOf(firstNetworkCreator, secondNetworkCreator, thirdNetworkCreator)
        val networkCredits = NetworkEntertainmentCredits(null, networkCrewCredits)
        val expectedUICredits =
            UIEntertainmentCredits(null, listOf(firstUICreator, secondUICreator, thirdUICreator))

        assertEquals(expectedUICredits, coreMapper.createUIEntertainmentCredits(networkCredits))
    }

    @Test
    fun `verify creating ui entertainment credits with not empty crew credits and empty cast credits`() {
        val networkCrewCredits =
            listOf(thirdNetworkCreator, firstNetworkCreator, secondNetworkCreator)
        val networkCredits = NetworkEntertainmentCredits(null, networkCrewCredits)
        val expectedUICredits =
            UIEntertainmentCredits(null, listOf(thirdUICreator, firstUICreator, secondUICreator))

        assertEquals(expectedUICredits, coreMapper.createUIEntertainmentCredits(networkCredits))
    }

    @Test
    fun `verify creating ui entertainment credits with cast and crew credits`() {
        val networkCastCredits = listOf(thirdNetworkActor, firstNetworkActor, secondNetworkActor)
        val networkCrewCredits =
            listOf(thirdNetworkCreator, firstNetworkCreator, secondNetworkCreator)
        val networkCredits = NetworkEntertainmentCredits(networkCastCredits, networkCrewCredits)
        val expectedUICredits =
            UIEntertainmentCredits(
                listOf(thirdUIActor, firstUIActor, secondUIActor),
                listOf(thirdUICreator, firstUICreator, secondUICreator)
            )

        assertEquals(expectedUICredits, coreMapper.createUIEntertainmentCredits(networkCredits))
    }

    companion object {
        private val firstActorId = Random.nextInt()
        private val firstActorName = Random.nextString()
        private val firstActorProfilePath = Random.nextString()
        private val firstActorCharacter = Random.nextString()
        private val secondActorId = Random.nextInt()
        private val secondActorName = Random.nextString()
        private val secondActorProfilePath = Random.nextString()
        private val thirdActorId = Random.nextInt()
        private val thirdActorName = Random.nextString()

        private val firstNetworkActor = NetworkEntertainmentPerson.Actor(
            firstActorId,
            firstActorName,
            firstActorProfilePath,
            firstActorCharacter
        )
        private val secondNetworkActor = NetworkEntertainmentPerson.Actor(
            secondActorId,
            secondActorName,
            secondActorProfilePath,
            null
        )
        private val thirdNetworkActor =
            NetworkEntertainmentPerson.Actor(thirdActorId, thirdActorName, null, null)

        private val firstUIActor = UIEntertainmentPerson.Actor(
            firstActorId,
            firstActorName,
            firstActorProfilePath,
            firstActorCharacter
        )
        private val secondUIActor = UIEntertainmentPerson.Actor(
            secondActorId,
            secondActorName,
            secondActorProfilePath,
            null
        )
        private val thirdUIActor =
            UIEntertainmentPerson.Actor(thirdActorId, thirdActorName, null, null)

        private val firstCreatorId = Random.nextInt()
        private val firstCreatorName = Random.nextString()
        private val firstCreatorProfilePath = Random.nextString()
        private val firstCreatorJob = Random.nextString()
        private val secondCreatorId = Random.nextInt()
        private val secondCreatorName = Random.nextString()
        private val secondCreatorProfilePath = Random.nextString()
        private val thirdCreatorId = Random.nextInt()
        private val thirdCreatorName = Random.nextString()

        private val firstNetworkCreator = NetworkEntertainmentPerson.Creator(
            firstCreatorId,
            firstCreatorName,
            firstCreatorProfilePath,
            firstCreatorJob
        )
        private val secondNetworkCreator = NetworkEntertainmentPerson.Creator(
            secondCreatorId,
            secondCreatorName,
            secondCreatorProfilePath,
            null
        )
        private val thirdNetworkCreator =
            NetworkEntertainmentPerson.Creator(thirdCreatorId, thirdCreatorName, null, null)

        private val firstUICreator = UIEntertainmentPerson.Creator(
            firstCreatorId,
            firstCreatorName,
            firstCreatorProfilePath,
            firstCreatorJob
        )
        private val secondUICreator = UIEntertainmentPerson.Creator(
            secondCreatorId,
            secondCreatorName,
            secondCreatorProfilePath,
            null
        )
        private val thirdUICreator =
            UIEntertainmentPerson.Creator(thirdCreatorId, thirdCreatorName, null, null)
    }
}
