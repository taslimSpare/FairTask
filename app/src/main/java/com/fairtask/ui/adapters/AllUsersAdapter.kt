package com.fairtask.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.fairtask.R
import com.fairtask.models.User

class AllUsersAdapter(private val users: List<User>, private val toggleSaveListener: (User) -> Unit, private val clickListener: (User) -> Unit): RecyclerView.Adapter<AllUsersAdapter.AllUsersViewHolder>() {

    inner class AllUsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val root = itemView.findViewById<ConstraintLayout>(R.id.root)
        private val name = itemView.findViewById<TextView>(R.id.tv_name)
        private val tvSave = itemView.findViewById<TextView>(R.id.tv_save)

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            name.text = "${user.firstName} ${user.lastName}"
            tvSave.text = if(user.saved) "Unsave" else "Save"
            tvSave.setOnClickListener { toggleSaveListener(user) }
            root.setOnClickListener { clickListener(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AllUsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.all_contact_layout, parent, false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: AllUsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

}
