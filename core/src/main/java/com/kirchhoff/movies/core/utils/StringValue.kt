package com.kirchhoff.movies.core.utils

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed interface StringValue {
    data object Empty : StringValue

    data class SimpleText(val text: String) : StringValue

    class IdText(
        @StringRes val id: Int,
        vararg val formatArgs: Any?
    ) : StringValue

    class PluralText(
        @PluralsRes val id: Int,
        val quantity: Int,
        vararg val formatArgs: Any?
    ) : StringValue

    @Suppress("SpreadOperator")
    fun asString(context: Context): String = when (this) {
        is Empty -> ""
        is SimpleText -> text
        is IdText -> context.getString(id, *formatArgs)
        is PluralText -> context.resources.getQuantityString(id, quantity, *formatArgs)
    }
}
