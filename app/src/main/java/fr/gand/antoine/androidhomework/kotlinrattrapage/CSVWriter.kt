package fr.gand.antoine.androidhomework.kotlinrattrapage

import java.io.FileWriter
import java.io.IOException
import java.util.Arrays
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter

fun main(args: Array<String>?) {
    val customers = Arrays.asList(
        Customer("1", "Jack Smith", "Massachusetts", 23),
        Customer("2", "Adam Johnson", "New York", 27),
        Customer("3", "Katherin Carter", "Washington DC", 26),
        Customer("4", "Jack London", "Nevada", 33),
        Customer("5", "Jason Bourne", "California", 36))

    var fileWriter: FileWriter? = null
    var csvPrinter: CSVPrinter? = null

    try {
        fileWriter = FileWriter("customer.csv")
        csvPrinter = CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("id", "name", "address", "age"))

        for (customer in customers) {
            val data = Arrays.asList(
                customer.id,
                customer.name,
                customer.address,
                customer.age)

            csvPrinter.printRecord(data)
        }

        println("Write CSV successfully!")
    } catch (e: Exception) {
        println("Writing CSV error!")
        e.printStackTrace()
    } finally {
        try {
            fileWriter!!.flush()
            fileWriter.close()
            csvPrinter!!.close()
        } catch (e: IOException) {
            println("Flushing/closing error!")
            e.printStackTrace()
        }
    }
}