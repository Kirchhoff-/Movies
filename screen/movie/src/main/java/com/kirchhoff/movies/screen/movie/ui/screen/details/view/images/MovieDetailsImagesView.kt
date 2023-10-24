package com.kirchhoff.movies.screen.movie.ui.screen.details.view.images

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.core.ui.recyclerview.decorations.TopBottomMarginItemDecoration
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.databinding.ViewMovieDetailsImagesBinding
import com.kirchhoff.movies.screen.movie.ui.screen.details.view.images.adapter.MovieDetailsImagesAdapter

internal class MovieDetailsImagesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val adapter = MovieDetailsImagesAdapter(MovieImageClickListener())

    private val binding =
        ViewMovieDetailsImagesBinding.inflate(LayoutInflater.from(context), this, true)

    private var itemClickListener: ItemClickListener? = null

    init {
        with(binding) {
            rvImages.apply {
                adapter = this@MovieDetailsImagesView.adapter
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                addItemDecoration(EdgesMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.movie_details_list_edges_margin)))
                addItemDecoration(
                    TopBottomMarginItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.movie_details_list_edges_margin),
                        resources.getDimensionPixelSize(R.dimen.movie_details_list_edges_margin)
                    )
                )
                isNestedScrollingEnabled = false
            }
            tvSeeAll.setOnClickListener { itemClickListener?.onSeeAllClick() }
        }
    }

    fun displayImages(list: List<UIImage>) {
        binding.notEmptyViewGroup.isVisible = list.isNotEmpty()
        binding.emptyViewGroup.isVisible = list.isEmpty()
        adapter.addItems(list)
    }

    fun itemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    private inner class MovieImageClickListener : BaseRecyclerViewAdapter.OnItemClickListener<UIImage> {
        override fun onItemClick(item: UIImage) {
            itemClickListener?.onImageClick(item.path)
        }
    }

    interface ItemClickListener {
        fun onSeeAllClick()
        fun onImageClick(imagePath: String)
    }
}
