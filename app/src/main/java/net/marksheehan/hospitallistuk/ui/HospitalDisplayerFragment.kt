package net.marksheehan.hospitallistuk.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_hospital_list.*
import net.marksheehan.hospitallistuk.R
import net.marksheehan.hospitallistuk.adapters.HospitalCardAdapter
import net.marksheehan.hospitallistuk.data.HospitalInfo
import net.marksheehan.hospitallistuk.ui.viewmodel.HospitalDisplayerViewModel

class HospitalDisplayerFragment : Fragment(R.layout.fragment_hospital_list) {

    lateinit var hospitalCardAdapter: HospitalCardAdapter

    private lateinit var viewModel: HospitalDisplayerViewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_button, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_filter_list -> {
                viewModel.filterClicked()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(HospitalDisplayerViewModel::class.java)

        val clickListener: (HospitalInfo) -> Unit = {hospitalInfo ->
            val navDirections = HospitalDisplayerFragmentDirections.actionHospitalInformationToSingleHospitalInfo(hospitalInfo)
            Navigation.findNavController(view).navigate(navDirections)
        }

        hospitalCardAdapter = HospitalCardAdapter(clickListener)
        hospital_list_recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        hospital_list_recycler.adapter = hospitalCardAdapter

        val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        hospital_list_recycler.addItemDecoration(itemDecoration)

        viewModel.hospitalDataSource.observe(viewLifecycleOwner, Observer { hospitalInfo ->
            hospitalCardAdapter.submitList(hospitalInfo)
            hospitalCardAdapter.notifyDataSetChanged()
        })
    }

}
