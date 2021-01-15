package com.fairtask.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fairtask.R
import com.fairtask.models.User

class AllUsersAdapter(private val context: Context, private val users: List<User>, private val clickListener: (User) -> Unit): RecyclerView.Adapter<AllUsersAdapter.AllUsersViewHolder>() {

    inner class AllUsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val root = itemView.findViewById<ConstraintLayout>(R.id.root)
        private val name = itemView.findViewById<TextView>(R.id.tv_name)
        private val star = itemView.findViewById<ImageView>(R.id.star)

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            name.text = "${user.firstName} ${user.lastName}"
            star.background = if(user.saved) ContextCompat.getDrawable(context, R.drawable.ic_star) else ContextCompat.getDrawable(context, R.drawable.ic_unstar)
            root.setOnClickListener { clickListener }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AllUsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.all_contact_layout, parent, false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: AllUsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

}
