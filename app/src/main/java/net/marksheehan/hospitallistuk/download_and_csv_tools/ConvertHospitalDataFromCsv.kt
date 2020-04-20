package net.marksheehan.hospitallistuk.download_and_csv_tools;

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.databind.RuntimeJsonMappingException
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import net.marksheehan.hospitallistuk.data.HospitalInfo


fun convertCsvToHospitalInfo(csvData: String): List<HospitalInfo> {
    val mapper = CsvMapper()
    mapper.registerKotlinModule()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val schema: CsvSchema = mapper.schemaFor(HospitalInfo::class.java).withHeader()
        .withColumnReordering(true)
        .withSkipFirstDataRow(false)
        .withColumnSeparator('�') //Because they're using ANSI not UTF8
        .withStrictHeaders(false)

    val reader: ObjectReader = mapper.readerFor(HospitalInfo::class.java).with(schema)

    val csvToHospitalMapper = reader.readValues<HospitalInfo>(csvData)

    val hospitalList: MutableList<HospitalInfo> = mutableListOf()

    while (csvToHospitalMapper.hasNextValue()) {
        try {
            val item = csvToHospitalMapper.next()
            hospitalList.add(item)
        } catch (ex: RuntimeJsonMappingException) {
            val lineNumber = csvToHospitalMapper.currentLocation.lineNr
            //TODO this should be caught with an error logging utility, but skip it for now.
            // There is actually one corrupted line in the NHS data
            println("Line number $lineNumber is invalid")
        }
    }

    return hospitalList
}