package com.prasannakumar.obvioustest.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.prasannakumar.obvioustest.R
import com.prasannakumar.obvioustest.databinding.DetailActvityBinding
import com.prasannakumar.obvioustest.model.NasaPicDetails
import com.prasannakumar.obvioustest.util.Utils

const val EXTRA_DETAIL_OBJECT = "details"
const val BUNDLE = "Bundle"
const val NO_COPYRIGHT = "No Copyright"

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: DetailActvityBinding
    private lateinit var nasaObj: NasaPicDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.getBundleExtra(BUNDLE)
        if (bundle != null) {
            nasaObj = bundle.getParcelable(EXTRA_DETAIL_OBJECT)!!
            setupUI(nasaObj)
        }
    }

    private fun setupUI(nasaObj: NasaPicDetails) {

        if (nasaObj.copyright != null) {
            binding.copyright.text = nasaObj.copyright
        } else {
            binding.copyright.text = NO_COPYRIGHT
        }
        val utilObj = Utils(this)
        val progressCircle = utilObj.getCircle()
        Glide.with(binding.root)
            .load(nasaObj.hdurl)
            .centerCrop()
            .placeholder(progressCircle)
            .error(R.drawable.ic_broken_image)
            .fallback(R.drawable.ic_no_image)
            .transform()
            .into(binding.detailImage)
        binding.date.text = nasaObj.date
        binding.title.text = nasaObj.title
        binding.description.text = nasaObj.explanation
    }

    companion object {
        fun startAsIntent(context: MainActivity, model: NasaPicDetails) {
            val activityIntent = getIntent(context)
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_DETAIL_OBJECT, model)
            activityIntent.putExtra(BUNDLE, bundle)
            context.startActivity(activityIntent)
        }

        private fun getIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }
    }

}