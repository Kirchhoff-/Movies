package com.kirchhoff.movies.voteview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible

class VoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val rbRating: RatingBar
    private val tvRating: TextView
    private val tvVoteCount: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_vote, this)

        rbRating = findViewById(R.id.rbRating)
        tvRating = findViewById(R.id.tvRating)
        tvVoteCount = findViewById(R.id.tvVoteCount)
    }

    fun displayRatingAndVoteCount(voteAverage: Float?, voteCount: Int?) {
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
