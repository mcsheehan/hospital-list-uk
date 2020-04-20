package net.marksheehan.hospitallistuk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.marksheehan.hospitallistuk.R
import net.marksheehan.hospitallistuk.data.HospitalInfo
import net.marksheehan.hospitallistuk.data.SectorTypes
import net.marksheehan.hospitallistuk.databinding.HospitalCardItemBinding

class HospitalCardAdapter(val onHospitalItemClicked : (HospitalInfo)-> Unit) : ListAdapter<HospitalInfo, HospitalCardAdapter.HospitalViewHolder>(HospitalInfoDiff())  {

    class HospitalInfoDiff : DiffUtil.ItemCallback<HospitalInfo>() {

        override fun areItemsTheSame(oldItem: HospitalInfo, newItem: HospitalInfo): Boolean {
            return (oldItem.OrganisationID == newItem.OrganisationID)
        }

        override fun areContentsTheSame(oldItem: HospitalInfo, newItem: HospitalInfo): Boolean {
            return (oldItem.OrganisationID != newItem.OrganisationID)
        }
    }

    inner class HospitalViewHolder(private val binding: HospitalCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCardModelToViewHolder(hospitalInfo: HospitalInfo){

            if(hospitalInfo.Sector == SectorTypes.NHS.sectorType) {
                binding.nhsLogo.setImageResource(R.drawable.ic_nhs_logo)
                binding.nhsLogo.visibility = View.VISIBLE
            }
            else{
                binding.nhsLogo.visibility = View.INVISIBLE
            }

            binding.hospitalInfo = hospitalInfo
            itemView.setOnClickListener{onHospitalItemClicked(hospitalInfo)}

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val binding : HospitalCardItemBinding = HospitalCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(binding)
    }

    override fun onBindViewHolder(hospitalViewHolder: HospitalViewHolder, position: Int) {
        val hospitalInfo = getItem(position)
        hospitalViewHolder.bindCardModelToViewHolder(hospitalInfo)
    }
}
