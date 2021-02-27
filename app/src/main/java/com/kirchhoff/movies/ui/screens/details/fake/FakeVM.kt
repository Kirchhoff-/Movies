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

    //Обработка ошибки с launch
    fun example5() {
        viewModelScope.launch {
            try {
                val resultOne = withContext(Dispatchers.IO) { fakeRepository.task1(3000) }
                val resultTwo = withContext(Dispatchers.IO) { fakeRepository.exception2(2000) }

                Log.e("TAG", "ResultOne = $resultOne" + ", ResultTwo = $resultTwo")
            } catch (e:Exception) {
                Log.e("TAG", "Handeled exception")
            }
        }
    }

    fun example6() {
        viewModelScope.launch {
            supervisorScope {
                //Он здесь не ловит exception а ловит только когда вызываешь await
              /*  try {
                    val deffered = async { fakeRepository.exception1(3000) }
                } catch (e: Exception) {
                    Log.e("TAG", "Before await exception")
                } */

                //Оригинальный пример
                val deffered = async { fakeRepository.exception1(3000) }
                try {
                    deffered.await()
                } catch (e: Exception) {
                    Log.e("TAG", "Handle exception")
                }
            }
        }
    }

    //все равно будет exception
    fun example7() {
        viewModelScope.launch {
            try {
                val resultOne = launch(Dispatchers.IO) { fakeRepository.task1(3000) }
                val resultTwo = launch(Dispatchers.IO) { fakeRepository.exception2(2000) }

                Log.e("TAG", "ResultOne = $resultOne" + ", ResultTwo = $resultTwo")
            } catch (e: Exception) {
                Log.e("TAG", "Handeled exception")
            }
        }
    }

    /* fun example() {
         viewModelScope.launch {
             try {
                 supervisorScope {
                     val result = async {
                         val result = fakeRepository.exception1(3000)
                         Log.e("TAG", "Result = $result")
                     }
                 }
             } catch (e: Exception) {
                 Log.e("TAG", "Handle exception")
             }
         }
     } */

    fun example() {
        viewModelScope.launch {
            supervisorScope {
                try {
                    val result1 = launch { fakeRepository.task1(5000) }
                    val result2 = launch { fakeRepository.exception1(2000) }

                    // Log.e(
                    //     "TAG",
                    //"ResultOne = ${result1.await()}" + ", ResultTwo = ${result2.await()}"
                    //  )

                    result1.join()
                    result2.join()

                    Log.e("TAG", "Finished")
                } catch (e: Exception) {
                    Log.e("TAG", "Exception")
                }
            }
        }
    }

    fun ProgressExample() {
        viewModelScope.launch {
            supervisorScope {
                try {
                    val result1 = async { fakeRepository.exception1(2000) }
                    val result2 = async { fakeRepository.task2(5000) }

                    val res1 = asyncWithCatch(result1)
                    val res2 = asyncWithCatch(result2)

                    Log.e(
                        "TAG",
                        "ResultOne = ${res1}" + ", ResultTwo = ${res2}"
                    )
                } catch (e: Exception) {
                    Log.e("TAG", "asdf")
                }
            }
        }
    }

    private suspend fun asyncWithCatch(action: Deferred<String>): String {
        /// return try {
        return action.await()
        // } catch (e: Exception) {
        //     "Stub result"
        // }
    }
}