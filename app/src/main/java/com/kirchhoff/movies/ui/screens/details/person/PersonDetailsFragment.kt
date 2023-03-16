package com.kirchhoff.movies.ui.screens.details.person

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.addTitleWithCollapsingListener
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.creditsview.CreditsView
import com.kirchhoff.movies.data.ui.details.person.UIMediaType
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredit
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.databinding.FragmentPersonDetailsBinding
import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsFragment
import com.kirchhoff.movies.ui.screens.details.tv.TvDetailsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class PersonDetailsFragment : BaseFragment(R.layout.fragment_person_details) {

    private val person: UIPerson by lazy { requireArguments().getParcelable(PERSON_ARG)!! }

    private val vm by viewModel<PersonDetailsVM>()
    private val viewBinding by viewBinding(FragmentPersonDetailsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadPersonDetails(person.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBackdrop.downloadPoster(person.profilePath)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            appbar.addTitleWithCollapsingListener(toolbar, person.name)
        }

        with(viewBinding.content) {
            tvPersonName.text = person.name
            bRetry.setOnClickListener { vm.loadPersonDetails(person.id) }
            vCredits.setCastClickListener { openMovieOrTvShowScreen(it) }
            vCredits.setCrewClickListener { openMovieOrTvShowScreen(it) }
        }

        with(vm) {
            personDetails.subscribe(::handlePersonDetails)
            personCredits.subscribe(::handlePersonCredits)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
        }
    }

    private fun handlePersonDetails(personDetails: UIPersonDetails) {
        with(viewBinding.content) {
            groupData.isVisible = true

            tvBorn.setTextOrNoInfo(personDetails.birthday)
            tvBirthplace.setTextOrNoInfo(personDetails.placeOfBirth)
            tvBio.setTextOrNoInfo(personDetails.biography)

            if (!personDetails.alsoKnownAs.isNullOrEmpty()) {
                cvAlsoKnowAs.isVisible = true
                vKeywords.displayItems(personDetails.alsoKnownAs)
            }
        }
    }

    private fun handlePersonCredits(personCredits: UIPersonCredits) {
        with(viewBinding.content.vCredits) {
            isVisible = true
            displayItems(personCredits.cast, personCredits.crew)
        }
    }

    private fun handleLoading(visible: Boolean) {
        with(viewBinding) {
            content.groupLoading.isVisible = visible

            if (visible) {
                content.groupException.isVisible = false
                content.groupError.isVisible = false
                content.groupData.isVisible = false
            }
        }
    }

    private fun handleError(error: String) {
        with(viewBinding) {
            content.groupError.isVisible = true
            content.tvError.text = error
        }
    }

    private fun handleException(exception: String) {
        with(viewBinding) {
            content.groupException.isVisible = true
            content.tvException.text = exception
        }
    }

    private fun openMovieOrTvShowScreen(creditsInfo: CreditsView.CreditsInfo) {
        if (creditsInfo is UIPersonCredit) {
            val fragment = when (creditsInfo.mediaType) {
                UIMediaType.MOVIE -> MovieDetailsFragment.newInstance(
                    UIMovie(
                        creditsInfo.id,
                        creditsInfo.title,
                        creditsInfo.posterPath,
                        creditsInfo.backdropPath,
                        null
                    )
                )
                UIMediaType.TV -> TvDetailsFragment.newInstance(
                    UITv(
                        creditsInfo.id,
                        creditsInfo.title,
                        creditsInfo.posterPath,
                        creditsInfo.backdropPath,
                        null
                    )
                )
            }

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance(person: UIPerson): PersonDetailsFragment {
            val fragment = PersonDetailsFragment()
            val arg = Bundle()
            arg.putParcelable(PERSON_ARG, person)
            fragment.arguments = arg
            return fragment
        }

        private const val PERSON_ARG = "PERSON_ARG"
    }

    private fun TextView.setTextOrNoInfo(txt: String?) {
        text = if (!txt.isNullOrEmpty()) txt else resources.getString(R.string.no_information)
    }
}
