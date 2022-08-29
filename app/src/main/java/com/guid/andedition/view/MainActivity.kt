package com.guid.andedition.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.guid.andedition.R
import com.guid.andedition.view.model.MainView
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainView by lazy {
        ViewModelProvider(this)[MainView::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.tag("확인").e("메인뷰모델 : $mainViewModel")

        mainViewModel.moveToNext()
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = mainViewModel.currentQuestionText
    }

    private fun checkAnswer(userAnser: Boolean) {
        val correctAnswer = mainViewModel.currentQuestionAnswer
    }
}