package com.guid.andedition.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guid.andedition.databinding.FragmentCrimeBinding
import com.guid.andedition.model.Crime
import com.guid.andedition.view.model.CrimeViewModel

class CrimeFragment : Fragment() {

    private lateinit var viewModel: CrimeViewModel

    private var _crimeBinding: FragmentCrimeBinding? = null
    private val getLayoutBinding get() = _crimeBinding!!

    private lateinit var crime: Crime

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _crimeBinding = FragmentCrimeBinding.inflate(inflater, container, false)
        return _crimeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CrimeViewModel::class.java]

        getLayoutBinding.crimeDate
            .apply {
                text = crime.date.toString()
                isEnabled = false
            }
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher =
            object : TextWatcher {
                override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                    crime.title = sequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {
                }
            }

        getLayoutBinding.crimeTitle.addTextChangedListener(titleWatcher)

        getLayoutBinding.crimeSolved
            .apply {
                setOnCheckedChangeListener { _, isChecked ->
                    crime.isSolved = isChecked
                }
            }
    }
}