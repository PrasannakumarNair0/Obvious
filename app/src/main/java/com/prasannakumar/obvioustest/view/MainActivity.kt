package com.prasannakumar.obvioustest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prasannakumar.obvioustest.adapters.GridViewAdapter
import com.prasannakumar.obvioustest.databinding.ActivityMainBinding
import com.prasannakumar.obvioustest.model.NasaPicDetails
import com.prasannakumar.obvioustest.util.Utils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

const val TOTAL_COLUMN = 2
const val JSON = "nasa.json"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainActivityViewModel
    private lateinit var adapterList: GridViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupViewModel()
        setupUI()
    }

    private fun setupViewModel() {
        model = ViewModelProvider(this)[MainActivityViewModel::class.java]
        model.getPictures().observe(this) { list: List<NasaPicDetails> ->
            binding.nasaPics.layoutManager = GridLayoutManager(this, TOTAL_COLUMN)
            adapterList = GridViewAdapter(list, this)
            binding.nasaPics.adapter = adapterList
        }

    }

    @DelicateCoroutinesApi
    private  fun setupUI() {
        val utilObj = Utils(this)
        GlobalScope.launch {
            val jsonString = utilObj.getJsonFromAssets(JSON)
            model.getDataFromJsonFile(jsonString)
        }
    }
}