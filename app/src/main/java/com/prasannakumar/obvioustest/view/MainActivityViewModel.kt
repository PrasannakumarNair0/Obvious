package com.prasannakumar.obvioustest.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.prasannakumar.obvioustest.model.NasaPicDetails
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken


class MainActivityViewModel : ViewModel() {
    private val picList: MutableLiveData<List<NasaPicDetails>> = MutableLiveData()
    private lateinit var list:List<NasaPicDetails>
    fun getPictures(): LiveData<List<NasaPicDetails>> {
        return picList
    }
    fun getDataFromJsonFile(jsonString: String?) {
        viewModelScope.launch {
            val gson = Gson()
            val myType: Type = object : TypeToken<List<NasaPicDetails>>() {}.type
             list = gson.fromJson(jsonString, myType)
            picList.value=list
        }
    }

}