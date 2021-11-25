package com.prasannakumar.obvioustest.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prasannakumar.obvioustest.adapters.GridViewAdapter
import com.prasannakumar.obvioustest.databinding.ActivityMainBinding
import com.prasannakumar.obvioustest.model.NasaPicDetails
import com.prasannakumar.obvioustest.util.Utils

const val TOTAL_COLUMN = 2

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainActivityViewModel
    private lateinit var adapterList: GridViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
    }

    private fun setupViewModel() {
       model=ViewModelProvider(this)[MainActivityViewModel::class.java]
       model.getPictures().observe(this){list:List<NasaPicDetails>->
           Log.d("ABC", "setupViewModel:${list.size} ")
           binding.nasaPics.layoutManager = GridLayoutManager(this, TOTAL_COLUMN)
           adapterList= GridViewAdapter(list,this)
           binding.nasaPics.adapter=adapterList
       }

    }

    private fun setupUI() {
        val utilObj=Utils()
        val jsonString=utilObj.getJsonFromAssets(this,"nasa.json")
        Log.d("ABC", "setupViewModel: $jsonString ")
        model.getDataFromJsonFile(jsonString)
    }
}