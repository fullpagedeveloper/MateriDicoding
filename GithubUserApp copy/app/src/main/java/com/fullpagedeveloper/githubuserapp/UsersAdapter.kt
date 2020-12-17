package com.fullpagedeveloper.githubuserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fullpagedeveloper.githubuserapp.model.Users
import kotlinx.android.synthetic.main.item_list.view.*

class UsersAdapter(private val adapterOnClick: (Users) -> Unit, private val listItem: List<Users>): RecyclerView.Adapter<UsersAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(list: Users){
            val avatar = itemView.resources.getIdentifier(list.avatar,"drawable", itemView.context.packageName )
            itemView.imageView_Avatar.setImageDrawable(ContextCompat.getDrawable(itemView.context, avatar))
            itemView.textView_Name.text = list.name
            itemView.textView_userName.text = list.username
            itemView.root.setOnClickListener { adapterOnClick(list) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listItem[position])
    }
}