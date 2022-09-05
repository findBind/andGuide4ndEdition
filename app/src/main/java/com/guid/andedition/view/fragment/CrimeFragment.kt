package com.guid.andedition.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guid.andedition.R
import com.guid.andedition.databinding.FragmentCrimeBinding
import com.guid.andedition.model.Crime
import com.guid.andedition.view.model.CrimeViewModel

class CrimeFragment : Fragment() {

    companion object {
        fun newInstance(): CrimeFragment {
            return CrimeFragment()
        }
    }

    private val viewModel: CrimeViewModel by lazy {
        ViewModelProvider(this)[CrimeViewModel::class.java]
    }

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

        crime = Crime()

        getLayoutBinding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        getLayoutBinding.crimeDate
            .apply {
                text = crime.date.toString()
                isEnabled = false
            }

        updateUI()
    }

    private fun updateUI() {
        getLayoutBinding.crimeRecyclerView.adapter = CrimeAdapter(crimes = viewModel.crimes)
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

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = itemView.findViewById(R.id.criem_name)
        val tvDate: TextView = itemView.findViewById(R.id.crime_time)
    }

    private inner class CrimeAdapter(var crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {

            val view = layoutInflater.inflate(R.layout.item_crime, parent, false)

            return CrimeHolder(view)
        }

        override fun getItemCount(): Int = crimes.size

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]

            holder.apply {
                tvTitle.text = crime.title
                tvDate.text = crime.date.toString()
            }
        }
    }
}