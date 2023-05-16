package com.kirchhoff.movies.screen.person.ui.screen.list

import android.content.Context
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.personModule
import com.kirchhoff.movies.screen.person.ui.screen.list.adapter.PersonsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class PersonsFragment : PaginatedScreenFragment<UIPerson, UIPaginated<UIPerson>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIPerson> {

    override val vm by viewModel<PersonsVM>()

    override val listAdapter = PersonsListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.person_empty_persons,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(personModule)
        super.onAttach(context)
    }

    override fun onDestroy() {
        unloadKoinModules(personModule)
        super.onDestroy()
    }

    companion object {
        private const val SPAN_COUNT = 3
        private const val THRESHOLD = SPAN_COUNT * 2
    }

    override fun onItemClick(item: UIPerson) {
        router.openPersonDetailsScreen(item)
    }
}
