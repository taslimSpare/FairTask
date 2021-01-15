package com.fairtask.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fairtask.R
import com.fairtask.models.User
import de.hdodenhof.circleimageview.CircleImageView

class SavedUsersAdapter(private val context: Context, private val users: List<User>, private val clickListener: (User) -> Unit): RecyclerView.Adapter<SavedUsersAdapter.SavedUsersViewHolder>() {

    inner class SavedUsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val root = itemView.findViewById<CardView>(R.id.root)
        private val name = itemView.findViewById<TextView>(R.id.tv_name)
        private val photo = itemView.findViewById<CircleImageView>(R.id.iv_profile_picture)

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            name.text = "${user.firstName} ${user.lastName}"
            root.setOnClickListener { clickListener(user) }
            Glide.with(context)
                .load(user.picture)
                .placeholder(R.drawable.ic_person_purple)
                .into(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SavedUsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.saved_contact_layout, parent, false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: SavedUsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

}
