package net.marksheehan.hospitallistuk.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import net.marksheehan.hospitallistuk.R
import net.marksheehan.hospitallistuk.data.SectorTypes
import net.marksheehan.hospitallistuk.databinding.SingleHospitalFragmentBinding
import net.marksheehan.hospitallistuk.ui.viewmodel.SingleHospitalViewModel

/**
 * A simple [Fragment] subclass.
 */
class SingleHospitalFragment : Fragment() {

    //TODO data should be in this viewmodel
    private lateinit var viewModel: SingleHospitalViewModel

    private val args: SingleHospitalFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SingleHospitalFragmentBinding.inflate(inflater,container, false)
        binding.hospitalInfo = args.hospitalInfo

        if(args.hospitalInfo.Sector == SectorTypes.NHS.sectorType) {
            binding.nhsLogo.setImageResource(R.drawable.ic_nhs_logo)
            binding.nhsLogo.visibility = View.VISIBLE
        }
        else{
            binding.nhsLogo.visibility = View.INVISIBLE
        }
        return binding.root
    }
}
