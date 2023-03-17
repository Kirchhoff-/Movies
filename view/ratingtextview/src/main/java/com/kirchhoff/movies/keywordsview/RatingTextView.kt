package com.kirchhoff.movies.keywordsview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.kirchhoff.movies.ratingtextview.R
import java.text.DecimalFormat

class RatingTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    private val ratingFormat = DecimalFormat("#.0")

    init {
        setBackgroundResource(R.drawable.background_rating)
        setTextColor(ContextCompat.getColor(context, R.color.rating_text_view_text_color))
    }

    fun displayRating(rating: Float?) {
        isVisible = rating != null
        text = ratingFormat.format(rating)
    }
}
