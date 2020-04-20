package net.marksheehan.hospitallistuk.download_and_csv_tools

import net.marksheehan.hospitallistuk.data.HospitalInfo
import org.junit.Test
import org.junit.Assert.*

internal class ConvertHospitalDataFromCsv {

    val header: String =
        "OrganisationID�OrganisationCode�OrganisationType�SubType�Sector�OrganisationStatus�IsPimsManaged�OrganisationName�Address1�Address2�Address3�City�County�Postcode�Latitude�Longitude�ParentODSCode�ParentName�Phone�Email�Website�Fax\n"
    val item1: String =
        "17970�NDA07�Hospital�Hospital�Independent Sector�Visible�True�Walton Community Hospital - Virgin Care Services Ltd��Rodney Road��Walton-on-Thames�Surrey�KT12 3LD�51.379997253417969�-0.40604206919670105�NDA�Virgin Care Services Ltd�01932 414205���01932 253674\n"
    val item2: String =
        "17981�NDA18�Hospital�Hospital�Independent Sector�Visible�True�Woking Community Hospital (Virgin Care)��Heathside Road��Woking�Surrey�GU22 7HS�51.315132141113281�-0.55628949403762817�NDA�Virgin Care Services Ltd�01483 715911���\n"

    val singleItem: String = header + item1
    val twoItems: String = header + item1 + item2

    @Test
    fun convertSingleItemOfHospitalData() {
        val hospitalList: List<HospitalInfo> = convertCsvToHospitalInfo(singleItem)
        assertEquals(1, hospitalList.size)
    }

    @Test
    fun singleHospitalItemHasCorrectFields() {
        val hospitalList: List<HospitalInfo> = convertCsvToHospitalInfo(singleItem)
        val firstItem = hospitalList.get(0)

        assertEquals(
            firstItem.OrganisationName,
            "Walton Community Hospital - Virgin Care Services Ltd"
        )
        assertEquals(firstItem.City, "Walton-on-Thames")
        assertEquals(firstItem.Latitude, 51.379997253417969, 1E-4)
    }

    @Test
    fun twoItemsInReturnsListSizeTwo() {
        val hospitalList: List<HospitalInfo> = convertCsvToHospitalInfo(twoItems)
        assertEquals(2, hospitalList.size)
    }
}
