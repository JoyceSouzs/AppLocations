package com.jmfs.locations.fragment


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jmfs.locations.R
import com.jmfs.locations.viewModel.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_button_nav.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class ButtonNavFragment : Fragment() {

    private val viewModel by sharedViewModel(PlaceViewModel::class)
    private lateinit var binding: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflater.inflate(R.layout.fragment_button_nav
            , container, false)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.tool_bar)

        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(requireActivity(),R.id.button_nav_host)
        button_nav_view.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter -> {
                itemsAlert()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun itemsAlert() {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle(R.string.category)
        builder?.setItems(R.array.categories) { _, which ->
            viewModel.setSelectedType(which, requireContext())
        }
        val dialog = builder?.create()
        dialog?.show()
    }
}
