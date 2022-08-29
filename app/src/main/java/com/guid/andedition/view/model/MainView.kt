package com.guid.andedition.view.model

import androidx.lifecycle.ViewModel
import com.guid.andedition.R
import com.guid.andedition.model.Question
import timber.log.Timber

class MainView : ViewModel() {

    init {
        Timber.tag("확인").e("view 모델 시작")
    }

    var currentIndex: Int = 0

    private val questionBank =
        listOf(Question(R.string.question_australia, true))

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }


    override fun onCleared() {
        super.onCleared()
        Timber.tag("확인").e("view 모델 종료")
    }
}