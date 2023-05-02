package com.kirchhoff.movies.screen.person.ui.screen.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.addTitleWithCollapsingListener
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.creditsview.CreditsView
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.data.UIMediaType
import com.kirchhoff.movies.screen.person.data.UIPersonCredit
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.databinding.FragmentPersonDetailsBinding
import com.kirchhoff.movies.screen.person.ui.view.adapter.PersonImageAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PersonDetailsFragment : BaseFragment() {

    private val person: UIPerson by lazy { requireArguments().getParcelableExtra(PERSON_ARG)!! }

    private val vm by viewModel<PersonDetailsVM>()

    private var _viewBinding: FragmentPersonDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private var imagesAdapter: PersonImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadPersonDetails(person.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBackdrop.downloadPoster(person.profilePath)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
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
            personImages.subscribe(::handlePersonImages)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
        }
    }

    override fun onDestroyView() {
        viewBinding.vpImages.adapter = null
        super.onDestroyView()
        _viewBinding = null
    }

    private fun handlePersonDetails(personDetails: UIPersonDetails) {
        with(viewBinding.content) {
            groupData.isVisible = true

            tvBorn.setTextOrNoInfo(personDetails.birthday)
            tvBirthplace.setTextOrNoInfo(personDetails.placeOfBirth)
            tvBio.setTextOrNoInfo(personDetails.biography)

            personDetails.alsoKnownAs?.let {
                cvAlsoKnowAs.isVisible = true
                vKeywords.displayItems(it)
            }
        }
    }

    private fun handlePersonCredits(personCredits: UIPersonCredits) {
        with(viewBinding.content.vCredits) {
            isVisible = true
            displayItems(personCredits.cast, personCredits.crew)
        }
    }

    private fun handlePersonImages(personImages: List<UIPersonImage>) {
        with(viewBinding) {
            val imagesUrls = personImages.map { it.url }
            imagesAdapter = PersonImageAdapter(
                this@PersonDetailsFragment,
                imagesUrls
            ) { openPersonImagesFragment(imagesUrls) }

            vpImages.adapter = imagesAdapter
            tabLayout.attachToPager(vpImages)

            ivBackdrop.isVisible = personImages.isEmpty()
            vpImages.isVisible = personImages.isNotEmpty()
            tabLayout.isVisible = personImages.isNotEmpty()
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
            if (creditsInfo.mediaType == UIMediaType.MOVIE) {
                router.openMovieDetailsScreen(
                    UIMovie(
                        creditsInfo.id,
                        creditsInfo.title,
                        creditsInfo.posterPath,
                        creditsInfo.backdropPath,
                        null
                    )
                )
            } else if (creditsInfo.mediaType == UIMediaType.TV) {
                router.openTvDetailsScreen(
                    UITv(
                        creditsInfo.id,
                        creditsInfo.title,
                        creditsInfo.posterPath,
                        creditsInfo.backdropPath,
                        null
                    )
                )
            }
        }
    }

    private fun openPersonImagesFragment(imagesUrls: List<String>) {
        router.openPersonImagesScreen(imagesUrls, viewBinding.vpImages.currentItem)
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
        text = if (!txt.isNullOrEmpty()) txt else resources.getString(R.string.person_no_information)
    }
}
