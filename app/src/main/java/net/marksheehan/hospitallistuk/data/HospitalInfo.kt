package net.marksheehan.hospitallistuk.data

import java.io.Serializable

data class HospitalInfo(
    val OrganisationID: Int,
    val OrganisationCode: String,
    val OrganisationType: String,
    val SubType: String,
    val Sector: String,
    val OrganisationStatus: String,
    val IsPimsManaged: Boolean,
    val OrganisationName: String,
    val Address1: String,
    val Address2: String,
    val Address3: String,
    val City: String,
    val Postcode: String,
    val Latitude: Double,
    val Longitude: Double,
    val Phone: String,
    val Email: String,
    val Website: String
) : Serializable

enum class SectorTypes(val sectorType: String){
    NHS("NHS Sector"),
    IndependentSector("Independent Sector")
}