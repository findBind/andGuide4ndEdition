package com.guid.andedition.view

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.guid.andedition.R
import com.guid.andedition.databinding.ActivityMainBinding
import com.guid.andedition.view.fragment.CrimeFragment
import com.guid.andedition.view.model.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var _mainBinding: ActivityMainBinding? = null
    private val getLayoutBinding get() = _mainBinding!!

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(getLayoutBinding.root)

        Timber.tag("확인").e("메인뷰모델 : $mainViewModel")

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, CrimeFragment.newInstance())
            .setReorderingAllowed(true)
            .commit()
    }
}