package com.kirchhoff.movies.repository.fake

interface IFakeRepository {
    suspend fun task1(delay: Long): String
    suspend fun task2(delay: Long): String
    suspend fun exception1(delay: Long): String
    suspend fun exception2(delay: Long): String
}