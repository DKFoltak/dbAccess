package edu.gustavo.dbaccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import edu.gustavo.dbaccess.dataAccess.Client
import edu.gustavo.dbaccess.dataAccess.ClientAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        activity?.setTitle(R.string.title_listado)
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton_add).setOnClickListener { view ->
            Snackbar.make(view, resources.getString(R.string.crearCliente), Snackbar.LENGTH_LONG)
                .setAction(resources.getString(R.string.nuevoCliente), {
                    (activity as HomeActivity).clientModel.seleccionado = Client()
                    findNavController(requireActivity(), R.id.nav_host_fragment)?.navigate(R.id.action_listFragment_to_upsertFragment)
                }).show()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recyclerView.adapter = ClientAdapter((activity as HomeActivity).clientModel)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(getContext())
    }
}