package com.kirchhoff.movies.ui.screens.details.person

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.responses.PersonDetails
import com.kirchhoff.movies.databinding.FragmentPersonDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PersonDetailsFragment : BaseFragment(R.layout.fragment_person_details) {

    private val person: Person by lazy { arguments!!.getParcelable<Person>(PERSON_ARG)!! }

    private val vm by viewModel<PersonDetailsVM>()
    private val viewBinding: FragmentPersonDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadPersonDetails(person.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            content.tvPersonName.text = person.name
            ivBackdrop.downloadPoster(person.profile_path)
            content.bRetry.setOnClickListener { vm.loadPersonDetails(person.id) }
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        with(vm) {
            personDetails.subscribe(::handlePersonDetails)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
        }
    }

    private fun handlePersonDetails(personDetails: PersonDetails) {
        with(viewBinding.content) {
            groupData.isVisible = true

            tvBorn.setTextOrNoInfo(personDetails.birthday)
            tvBirthplace.setTextOrNoInfo(personDetails.place_of_birth)
            tvBio.setTextOrNoInfo(personDetails.biography)
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

    companion object {
        fun newInstance(person: Person): PersonDetailsFragment {
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
