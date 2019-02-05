package fr.gand.antoine.androidhomework.kotlinrattrapage

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.ArrayList
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser

fun main(args: Array<String>?) {
    var fileReader: BufferedReader? = null
    var csvParser: CSVParser? = null

    try {
        fileReader = BufferedReader(FileReader("customer.csv"))
        csvParser = CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

        val customers = ArrayList<Customer>()
        val csvRecords = csvParser.records

        for (csvRecord in csvRecords) {
            val customer = Customer(
                csvRecord.get("id"),
                csvRecord.get("name"),
                csvRecord.get("address"),
                Integer.parseInt(csvRecord.get("age"))
            )

            customers.add(customer)
        }
        for (customer in customers) {
            println(customer)
        }
    } catch (e: Exception) {
        println("Reading CSV Error!")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
            csvParser!!.close()
        } catch (e: IOException) {
            println("Closing fileReader/csvParser Error!")
            e.printStackTrace()
        }
    }
}