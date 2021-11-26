package com.prasannakumar.obvioustest.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prasannakumar.obvioustest.model.NasaPicDetails
import kotlinx.coroutines.launch
import java.lang.reflect.Type


class MainActivityViewModel : ViewModel() {
    private val picList: MutableLiveData<List<NasaPicDetails>> = MutableLiveData()
    fun getPictures(): LiveData<List<NasaPicDetails>> {
        return picList
    }

    fun getDataFromJsonFile(jsonString: String?) {
        viewModelScope.launch {
            val gson = Gson()
            val myType: Type = object : TypeToken<List<NasaPicDetails>>() {}.type
            picList.value = gson.fromJson(jsonString, myType)
        }
    }
}