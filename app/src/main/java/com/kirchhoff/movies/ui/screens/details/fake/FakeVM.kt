package com.kirchhoff.movies.ui.screens.details.fake

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.repository.fake.IFakeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FakeVM(private val fakeRepository: IFakeRepository): ViewModel() {

    //Последовательное выполнение
    fun first() {
        viewModelScope.launch {
            val resultOne = withContext(Dispatchers.IO) { fakeRepository.task1(3000) }
            val resultTwo = withContext(Dispatchers.IO) { fakeRepository.task2(2000) }

            Log.e("TAG", "ResultOne = $resultOne" + ", ResultTwo = $resultTwo")
        }
    }
}