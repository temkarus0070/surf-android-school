package com.example.surfandroidschool.memes

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.surfandroidschool.MemesFragment
import com.example.surfandroidschool.R
import com.example.surfandroidschool.mvp.views.MemesView
import com.example.surfandroidschool.vm.MemesViewModel

class memesAdapter(activity: Activity, val clickListener:(MemeData,Int)->Unit)
    :RecyclerView.Adapter<memesAdapter.MemeHolder>() {
    private var memeViewModel:MemesViewModel = ViewModelProviders.of(activity as AppCompatActivity)
    .get(MemesViewModel::class.java)
    private var useActivity = activity
    private var  data:List<MemeData> = listOf()

    fun setData(list:List<MemeData>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.memes_list_item,
            parent,
            false
        )
        return MemeHolder(view)
    }



    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: MemeHolder, position: Int) {
        val url = data.get(position).photoUrl
        holder.textView.text=data[position].title
       Glide.with(useActivity)
            .load(url)
            .into(holder.imageView)

        if(data.get(position).isFavorite){
            holder.likeButton.setBackgroundResource(R.drawable.ic_like)
        }
        val item = data.get(position)
        holder?.itemView.setOnClickListener{
            clickListener(item,position)
        }
        holder.likeButton.setOnClickListener {
            setLike(data.get(position))
        }
    }

    fun setLike(meme:MemeData){
        meme.isFavorite = true
        memeViewModel.insert(meme)
    }

    inner class MemeHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView:ImageView= view.findViewById(R.id.memeImage)
        var textView:TextView = view.findViewById(R.id.memeText)
        var likeButton:TextView = view.findViewById(R.id.likeButton)



    }

}