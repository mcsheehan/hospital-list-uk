package net.marksheehan.hospitallistuk.download_and_csv_tools

import org.junit.Assert
import org.junit.Test


internal class HospitalDataDownloaderTest {

    @Test
    fun downloadingReturnsAStringOfLengthGreaterThanOne() {
        val url = "http://media.nhschoices.nhs.uk/data/foi/Hospital.csv"

        val hospitalInfoDownloader = HospitalDataDownloader();
        val result: String = hospitalInfoDownloader.downloadUrl(url)

        Assert.assertTrue(result.length > 1)
    }
}