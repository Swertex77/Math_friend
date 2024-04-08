package com.example.math_friend.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.math_friend.R
import com.example.math_friend.data.Theory
import com.example.math_friend.databinding.ListItemBinding

class RecyclerViewAdapter(
    private val theories: List<Theory>,
    private val clickListener: (Theory) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.DictViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DictViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.list_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: DictViewHolder, position: Int) =
        holder.bind(theories[position], clickListener)

    override fun getItemCount() = theories.size

    inner class DictViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(theory: Theory, clickListener: (Theory) -> Unit) = with(binding) {
            wordTextView.text = theory.word
            translateTextView.text = theory.translate
            listItemLayout.setOnClickListener { clickListener(theory) }
        }
    }

}