package edu.gustavo.dbaccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        activity?.setTitle(R.string.title_detalle)
        val id = view.findViewById<TextView>(R.id.textView_idValue)
        id.text = (activity as HomeActivity).clientModel.seleccionado?.id.toString()
        val name = view.findViewById<TextView>(R.id.textView_nameValue)
        name.text = (activity as HomeActivity).clientModel.seleccionado?.name
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton_edit).setOnClickListener { view ->
            findNavController().navigate(R.id.action_detailFragment_to_upsertFragment)
        }
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton_delete).setOnClickListener { view ->
            (activity as HomeActivity).clientModel.delete()
            findNavController().navigateUp()
        }
        return view
    }
}