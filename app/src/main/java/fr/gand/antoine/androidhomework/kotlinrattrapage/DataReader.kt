package fr.gand.antoine.androidhomework.kotlinrattrapage

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.lang.Integer.parseInt
import java.text.NumberFormat
import java.util.*

fun main(args: Array<String>?) {
    var fileReader: BufferedReader? = null
    var csvParser: CSVParser? = null

    try {
        fileReader = BufferedReader(FileReader("data_csv.csv"))
        csvParser = CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withDelimiter(';').withIgnoreEmptyLines())

        val datas = ArrayList<Data>()
        val csvRecords = csvParser.records

        for (csvRecord in csvRecords) {
            val data = Data(

                Integer.parseInt(csvRecord.get("year")),
                csvRecord.get("device"),
                NumberFormat.getNumberInstance(Locale.FRANCE).parse(csvRecord.get("order")),
                Integer.parseInt(csvRecord.get("print")),
                Integer.parseInt(csvRecord.get("click")),
                NumberFormat.getNumberInstance(Locale.FRANCE).parse(csvRecord.get("cost")),
                NumberFormat.getNumberInstance(Locale.FRANCE).parse(csvRecord.get("turnover")),
                csvRecord.get("month")
            )

            datas.add(data)
        }




        var sommeClick = 0
        var sommePrint = 0
        var sommeCost = 0
        var sommeTurnover = 0
        var sommeOrder = 0
        var one = 0
        var two = 0
        var three = 0
        var four = 0
        var five = 0
        var six = 0
        var seven = 0


        for (data in datas) {

            sommeClick += data.click
            sommePrint += data.print
            sommeCost += data.cost.toInt()
            sommeTurnover += data.turnover.toInt()
            sommeOrder += data.order.toInt()

            // #1 Chiffre d'affaire par mois par année

            // #2 Chiffre d'affaire par appareil

            // #3 Panier Moyen
            three = sommeTurnover/sommeOrder

            // #4 Coût par clic
            four = sommeCost/sommeClick

            // #5 Taux de clic (Clic par impression)*100
            five = (sommeClick/sommePrint)*100

            // #6 ROI : Chiffre d'affaire total par coût
            six = sommeTurnover / sommeCost

            // #7 ROI par appareil par mois (2017)

        }


        println("KPI")
        println("\n")
        println("#1 Chiffre d'affaire par mois par année = $one")
        println("#2 Chiffre d'affaire par appareil = $two")
        println("#3 Panier Moyen = $three")
        println("#4 Coût par clic = $four")
        println("#5 Taux de clic (Clic par impression)*100 = $five")
        println("#6 ROI : Chiffre d'affaire total par coût = $six")
        println("#7 ROI par appareil par mois (2017) = $seven")




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
