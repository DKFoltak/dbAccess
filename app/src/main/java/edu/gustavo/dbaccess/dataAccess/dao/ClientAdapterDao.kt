package edu.gustavo.dbaccess.dataAccess.dao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.gustavo.dbaccess.R
import edu.gustavo.dbaccess.dataAccess.Client
import edu.gustavo.dbaccess.dataAccess.ClientViewModel

class ClientAdapterDao(val viewModel : ClientViewModel) : ListAdapter<Client, ClientAdapterDao.ViewHolder>(ClientComparer()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id : TextView = view.findViewById(R.id.textView_idValue) as TextView
        var name : TextView = view.findViewById(R.id.textView_nameValue) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_detail, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            viewModel.seleccionado = Client(holder.id.text.toString().toLong(), holder.name.text.toString())
            it.findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cliente = getItem(position)
        holder.id.text = cliente.id.toString()
        holder.name.text = cliente.name
    }

    class ClientComparer : DiffUtil.ItemCallback<Client>() {
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return ((oldItem.id == newItem.id) && (oldItem.name == newItem.name))
        }
    }
}
