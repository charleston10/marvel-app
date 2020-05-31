package com.charleston.marvelapp.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.charleston.marvelapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(), View.OnClickListener {

    private lateinit var databinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        databinding = FragmentDetailBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = this@DetailFragment
            clickListener = this@DetailFragment
        }

        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    override fun onClick(v: View?) {
        activity?.onBackPressed()
    }

    private fun initialize() {
        arguments?.let {
            val model = DetailFragmentArgs.fromBundle(it).model
            databinding.model = model
        }
    }
}