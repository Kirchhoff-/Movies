package com.kirchhoff.movies.screen.review.factory

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal class ReviewDateFactory {

    fun createPublicationDate(createdAt: String): Date? {
        val pattern = if (createdAt.contains('.')) {
            DATE_PATTERN_WITH_MILLISECONDS
        } else {
            DATE_PATTERN_WITHOUT_MILLISECONDS
        }
        val parser = SimpleDateFormat(pattern, Locale.ROOT)
        parser.isLenient = false
        parser.timeZone = TimeZone.getTimeZone(UTC)

        return try {
            parser.parse(createdAt)
        } catch (_: ParseException) {
            null
        }
    }

    private companion object {
        const val DATE_PATTERN_WITH_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val DATE_PATTERN_WITHOUT_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val UTC = "UTC"
    }
}
