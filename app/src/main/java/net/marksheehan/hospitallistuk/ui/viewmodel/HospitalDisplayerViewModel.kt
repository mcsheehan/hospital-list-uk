package net.marksheehan.hospitallistuk.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.marksheehan.hospitallistuk.data.HospitalInfo
import net.marksheehan.hospitallistuk.data.SectorTypes
import net.marksheehan.hospitallistuk.download_and_csv_tools.HospitalDataDownloader
import net.marksheehan.hospitallistuk.download_and_csv_tools.convertCsvToHospitalInfo

class HospitalDisplayerViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                scheduleDownloadHospitalData()
            }
        }
    }

    suspend fun scheduleDownloadHospitalData() {
        // This should be part of a repository, and cached, so it isn't re-fetched on recreation of viewmodel
        // Additionally error conditions / some user feedback should happen whilst this file downloads
        // the UX of nothing happening with no feedback isn't so good.
        // Additionally this should be a dependency injected repository to aid testability.
        val downloader = HospitalDataDownloader()

        val url = "http://media.nhschoices.nhs.uk/data/foi/Hospital.csv"
        val downloadedCsv = downloader.downloadUrl(url)
        val hospitalInfoList = convertCsvToHospitalInfo(downloadedCsv)

        withContext(Dispatchers.Main){
            allHospitalInfo.value = hospitalInfoList
        }
    }

    private val allHospitalInfo: MutableLiveData<List<HospitalInfo>> = MutableLiveData()

    private val filterClicked : MutableLiveData<Boolean> = MutableLiveData(false)

    val hospitalDataSource : LiveData<List<HospitalInfo>> = Transformations.switchMap(filterClicked){
        val result : MutableLiveData<List<HospitalInfo>>
        if(it) {
            val a = allHospitalInfo.value?.filter {it.Sector == SectorTypes.NHS.sectorType}
            result = MutableLiveData(a!!)
        }
        else{
            result = allHospitalInfo
        }
        return@switchMap result
    }

    fun filterClicked() {
        filterClicked.value = !filterClicked.value!!
    }
}
