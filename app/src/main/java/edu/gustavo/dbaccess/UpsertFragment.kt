package edu.gustavo.dbaccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpsertFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upsert, container, false)
        val id = view.findViewById<TextView>(R.id.textView_idValue)
        val idVal = (activity as HomeActivity).clientModel.seleccionado?.id
        if (idVal == 0L) {
            activity?.setTitle(R.string.title_nuevo)
            id.visibility = View.GONE
            view.findViewById<TextView>(R.id.textView_id).visibility = View.GONE
        }
        else {
            activity?.setTitle(R.string.title_actualizar)
            id.text = idVal.toString()
        }
        // Inflate the layout for this fragment
        val name = view.findViewById<EditText>(R.id.textView_nameValue)
        name.setText((activity as HomeActivity).clientModel.seleccionado?.name)
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton_upsert).setOnClickListener { _ ->
            (activity as HomeActivity).clientModel.seleccionado?.name = name.text.toString()
            (activity as HomeActivity).clientModel.upsert()
            findNavController().navigateUp()
        }

        return view;
    }
}