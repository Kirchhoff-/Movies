package com.kirchhoff.movies.screen.person.ui.screen.details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.person.data.UIMediaType
import com.kirchhoff.movies.screen.person.data.UIPersonCredit
import com.kirchhoff.movies.screen.person.router.IPersonRouter
import com.kirchhoff.movies.screen.person.ui.screen.details.ui.PersonDetailsUI
import com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel.PersonDetailsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf
import timber.log.Timber

internal class PersonDetailsFragment : BaseFragment() {

    private val person: UIPerson by lazy {
        requireArguments().getParcelableExtra(PERSON_ARG)
            ?: error("Should provide person info in arguments")
    }

    private val personRouter: IPersonRouter by inject { parametersOf(requireActivity()) }

    private val viewModel: PersonDetailsViewModel by viewModel { parametersOf(person) }

    override fun onAttach(context: Context) {
        loadKoinModules(personDetailsModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetails()
    }

    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
        )

        setContent {
            val screenState by viewModel.screenState.observeAsState()

            PersonDetailsUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onCreditItemClick = { onCreditItemClick(it) },
                onImageClick = { onImageClick(it) },
                onLocationClick = { onLocationClick() },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(personDetailsModule)
        super.onDestroy()
    }

    private fun onCreditItemClick(credit: UIPersonCredit) {
        if (credit.mediaType == UIMediaType.MOVIE) {
            router.openMovieDetailsScreen(
                UIMovie(
                    MovieId(credit.id),
                    credit.title,
                    credit.posterPath,
                    credit.backdropPath,
                    null
                )
            )
        } else if (credit.mediaType == UIMediaType.TV) {
            router.openTvDetailsScreen(
                UITv(
                    TvId(credit.id),
                    credit.title,
                    credit.posterPath,
                    credit.backdropPath,
                    null
                )
            )
        }
    }

    private fun onImageClick(position: Int) {
        personRouter.openImagesScreen(
            imagesUrls = viewModel.screenState.value?.images?.map { it.url } ?: error("Can't open images screen without images"),
            currentPosition = position
        )
    }

    private fun onLocationClick() {
        try {
            val birthplace = viewModel.screenState.value?.details?.placeOfBirth ?: error("Can't open location without place of birth")
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$birthplace"))
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            Timber.e("Can't find map application")
        }
    }

    companion object {
        fun newInstance(person: UIPerson): PersonDetailsFragment = PersonDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PERSON_ARG, person)
            }
        }

        private const val PERSON_ARG = "PERSON_ARG"
    }
}
