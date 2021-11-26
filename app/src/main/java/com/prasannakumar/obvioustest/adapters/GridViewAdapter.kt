package com.prasannakumar.obvioustest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prasannakumar.obvioustest.R
import com.prasannakumar.obvioustest.databinding.ItemViewBinding
import com.prasannakumar.obvioustest.model.NasaPicDetails
import com.prasannakumar.obvioustest.view.DetailActivity
import com.prasannakumar.obvioustest.view.MainActivity


class GridViewAdapter(
    private var nasaPicDetailsList: List<NasaPicDetails>,
    private var context: MainActivity
) :
    RecyclerView.Adapter<GridViewAdapter.ViewHolder>() {
    private lateinit var binding: ItemViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewAdapter.ViewHolder {
        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: GridViewAdapter.ViewHolder, position: Int) {
        with(nasaPicDetailsList[position]) {
            val model: NasaPicDetails = nasaPicDetailsList[position]
            Glide.with(binding.root)
                .load(model.url)
                .centerCrop()
                .placeholder(R.drawable.monkey)
                .error(R.drawable.ic_broken_image)
                .fallback(R.drawable.ic_no_image)
                .transform()
                .into(binding.icImages)

            binding.imageLayout.setOnClickListener {
                DetailActivity.startAsIntent(context, model)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.nasaPicDetailsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}