package edu.gustavo.dbaccess.dataAccess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.gustavo.dbaccess.R

class ClientAdapter(val viewModel : ClientViewModel) : RecyclerView.Adapter<ClientAdapter.ViewHolder>() {
    lateinit var list : MutableList<Client>

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        list = viewModel.repositorio.list()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id = view.findViewById<View>(R.id.textView_idValue) as TextView
        var name = view.findViewById<View>(R.id.textView_nameValue) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_detail, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            viewModel.seleccionado = Client(holder.id.text.toString().toLong(), holder.name.text.toString())
            it.findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cliente = list[position]
        holder.id.text = cliente.id.toString()
        holder.name.text = cliente.name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
