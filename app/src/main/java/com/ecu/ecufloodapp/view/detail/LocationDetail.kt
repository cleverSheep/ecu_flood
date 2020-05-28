package com.ecu.ecufloodapp.view.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.ecu.ecufloodapp.R
import com.ecu.ecufloodapp.databinding.LocationDetailFragmentBinding
import com.ecu.ecufloodapp.extensions.formatWellId
import com.ecu.ecufloodapp.view.detail.LocationDetailArgs
import timber.log.Timber

class LocationDetail : Fragment() {

    private lateinit var viewModel: LocationDetailViewModel
    private val args: LocationDetailArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<LocationDetailFragmentBinding>(
                inflater,
                R.layout.location_detail_fragment,
                container,
                false
            )
        viewModel = ViewModelProviders.of(this).get(LocationDetailViewModel::class.java)
        binding.apply {
            detailViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.fetchStaticWellData(args.sensorName.formatWellId())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchStaticWellData(args.sensorName.formatWellId())
        Log.d("LocationDetail", "Well Id : ${args.sensorName.formatWellId()}")
    }

}
