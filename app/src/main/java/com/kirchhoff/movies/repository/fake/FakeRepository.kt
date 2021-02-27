package com.kirchhoff.movies.repository.fake

import android.util.Log
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeRepository: IFakeRepository {

    override suspend fun task1(delay: Long): String {
        Log.e("TAG", "Start task 1. Delay = $delay")
        delay(delay)

        Log.e("TAG", "Finish task 1")
        return delay.toString()
    }

    override suspend fun task2(delay: Long): String {
        Log.e("TAG", "Start task 2. Delay = $delay")
        delay(delay)

        Log.e("TAG", "Finish task 2")
        return delay.toString()
    }

    override suspend fun exception1(delay: Long): String {
        Log.e("TAG", "Start exception1 task. Delay = $delay")
        delay(delay)

        Log.e("TAG", "Throw error (exception task 1)")
        error("Error 1")
    }

    override suspend fun exception2(delay: Long): String {
        Log.e("TAG", "Start exception2 task. Delay = $delay")
        delay(delay)

        Log.e("TAG", "Throw error (exception task 2)")
        error("Error 2")
    }

    override suspend fun getInfo(id: Int): String {
        Log.e("TAG", "Start get info, id = $id")

        delay(Random.nextLong(2_000))

        return if (id == 5) {
            error("error")
        } else {
            id.toString()
        }
    }
}