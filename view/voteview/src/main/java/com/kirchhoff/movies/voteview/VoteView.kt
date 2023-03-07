package com.kirchhoff.movies.voteview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.kirchhoff.movies.voteview.databinding.ViewVoteBinding

class VoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewVoteBinding = ViewVoteBinding.inflate(LayoutInflater.from(context), this, true)

    fun displayRatingAndVoteCount(voteAverage: Float?, voteCount: Int?) {
        with(binding) {
            if (voteAverage != null) {
                rbRating.rating = voteAverage / 2
                tvRating.text = resources.getString(R.string.vote_view_rating_format, voteAverage)
            }

            if (voteCount != null) {
                tvVoteCount.text = voteCount.toString()
            }

            rbRating.isVisible = voteAverage != null
            tvRating.isVisible = voteAverage != null
            tvVoteCount.isVisible = voteCount != null
        }
    }
}
