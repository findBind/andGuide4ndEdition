package com.guid.andedition.view.model

import androidx.lifecycle.ViewModel
import com.guid.andedition.model.Crime

class CrimeViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 100) {

            val crime = Crime()
            crime.title = "Crime : $i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }
}