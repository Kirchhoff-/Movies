package com.kirchhoff.movies.data

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?
)

data class Person(
    val id: Int,
    val name: String,
    val profile_path: String?
)

data class Tv(
    val id: Int,
    val poster_path: String?,
    val name: String
)
