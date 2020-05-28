@file:Suppress("FunctionName", "PrivatePropertyName")

package com.ecu.ecufloodapp.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecu.ecufloodapp.model.WellData
import com.ecu.ecufloodapp.network.client.WellDataClient
import com.ecu.ecufloodapp.network.service.WellDataService
import com.ecu.ecufloodapp.util.combineLatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.observers.DisposableSingleObserver

class LocationDetailViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val wellDataClient: WellDataClient = WellDataService.service()

    private val well_id = MutableLiveData<Int>()
    fun wellId(): MutableLiveData<Int> = well_id

    private val well_lat_lng = MutableLiveData<String>()
    fun well_lat_lng() = well_lat_lng

    private val elevation = MutableLiveData<Double>()
    fun elevation() = elevation

    private val list_well_items = mutableListOf<WellData>()
    private val observable_well_items = MutableLiveData<List<WellData>>()

    fun fetchStaticWellData(id: Int) {
        Log.d("LocationDetailViewModel", "Fetch well data")
        compositeDisposable.add(
            wellDataClient.getWellData(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WellData>() {
                    override fun onSuccess(data: WellData) {
                        Log.d("LocationDetailViewModel", "Well data: ${data.id}")
                        well_id.postValue(data.id)
                        well_lat_lng.postValue(combineLatLng(data))
                        elevation.postValue(data.elevation)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("LocationDetailViewModel", "Error loading static well data")
                        Log.e("LocationDetailViewModel", e.localizedMessage)
                    }

                })
        )
    }

    fun addWellItem(well_item: WellData) {
        list_well_items.add(well_item)
        observable_well_items.value = list_well_items
    }

    fun fetchWellItems(): LiveData<List<WellData>> = observable_well_items

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
