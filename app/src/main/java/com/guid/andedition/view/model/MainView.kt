package com.guid.andedition.view.model

import androidx.lifecycle.ViewModel
import com.guid.andedition.R
import timber.log.Timber

class MainView : ViewModel() {

    init {
        Timber.tag("확인").e("view 모델 시작")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.tag("확인").e("view 모델 종료")
    }
}