package com.kirchhoff.movies.ui.screens.details.fake

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.repository.fake.IFakeRepository
import kotlinx.coroutines.*

class FakeVM(private val fakeRepository: IFakeRepository): ViewModel() {

    val supervisorJob = SupervisorJob()

    //Последовательное выполнение
    fun first() {
        viewModelScope.launch {
            val resultOne = withContext(Dispatchers.IO) { fakeRepository.task1(3000) }
            val resultTwo = withContext(Dispatchers.IO) { fakeRepository.task2(2000) }

            Log.e("TAG", "ResultOne = $resultOne" + ", ResultTwo = $resultTwo")
        }
    }

    //Параллельное выполнение
    fun second() {
        viewModelScope.launch {
            val resultOne = async { fakeRepository.task1(4000) }
            val resultTwo = async { fakeRepository.task2(2000) }

            Log.e("TAG", "ResultOne = ${resultOne.await()}" + ", ResultTwo = ${resultTwo.await()}")
        }
    }

    //Параллельное выполнение (без await)
    fun third() {
        viewModelScope.launch {
            val resultOne = async { fakeRepository.task1(4000) }
            val resultTwo = async { fakeRepository.task2(2000) }

            Log.e("TAG", ", ResultTwo = ${resultTwo.await()}")
        }
    }

    //Вообще без await
    fun example1() {
        viewModelScope.launch {
            val resultOne = async { fakeRepository.task1(4000) }
            val resultTwo = async { fakeRepository.task2(2000) }

            Log.e("TAG", "print text")
        }
    }

    fun example2() {
        viewModelScope.launch {
            val resultOne = async { fakeRepository.exception1(2000) }
            val resultTwo = async { fakeRepository.task2(4000) }

            Log.e("TAG", "ResultOne = ${resultOne.await()}" + ", ResultTwo = ${resultTwo.await()}")
        }
    }

    fun example3() {
        viewModelScope.launch {
            try {
                val resultOne = async { fakeRepository.exception1(2000) }
                val resultTwo = async { fakeRepository.task2(4000) }

                Log.e(
                    "TAG",
                    "ResultOne = ${resultOne.await()}" + ", ResultTwo = ${resultTwo.await()}"
                )
            } catch (e: Exception) {
                Log.e("TAG", "Get exception")
            }
        }
    }

    fun example4() {
       viewModelScope.launch {
            try {
                //Если его закомментить - будет креш, с ним - все ок
                supervisorScope {
                    val resultOne = async { fakeRepository.exception1(2000) }
                    val resultTwo = async { fakeRepository.task2(4000) }

                    Log.e(
                        "TAG",
                        "ResultOne = ${resultOne.await()}" + ", ResultTwo = ${resultTwo.await()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("TAG", "Get exception")
            }
        }
    }
}