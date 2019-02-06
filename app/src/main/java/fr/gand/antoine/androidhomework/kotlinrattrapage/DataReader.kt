package fr.gand.antoine.androidhomework.kotlinrattrapage

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
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

        val perMonth = mutableMapOf<Int,MutableMap<String,MutableList<Number>>>()
        val perDevice = mutableMapOf<String,Device>()
        val perDevicePerYearPerMonth = mutableMapOf<String,DeviceYearMonth>()

        for (data in datas) {

            sommeClick += data.click
            sommePrint += data.print
            sommeCost += data.cost.toInt()
            sommeTurnover += data.turnover.toInt()
            sommeOrder += data.order.toInt()

            // #1 Chiffre d'affaire par mois par année, on ajoute le chiffre d'affaire du moi de l'année
            addValueToMap(perMonth,data.year,data.month,data.turnover)

            // #2 Chiffre d'affaire par appareil, on ajoute le chiffre d'affaire à l'appareil
            addDevice(perDevice,data.device, data.turnover)

            // #7
            addValueToDeviceYearAndMonth(perDevicePerYearPerMonth, data.device,data.year,data.month,data.turnover)



        }

        var midShop = 0
        var costClick = 0f
        var clickPrint = 0f
        var roi = 0f

        // #3 Panier Moyen
        midShop = sommeTurnover/sommeOrder

        // #4 Coût par clic
        costClick = sommeCost/sommeClick.toFloat()

        // #5 Taux de clic (Clic par impression)*100
        clickPrint = (sommeClick.toFloat()/sommePrint.toFloat())*100

        // #6 ROI : Chiffre d'affaire total par coût
        roi = sommeTurnover.toFloat() / sommeCost




        // print
        println("KPI")
        println("\n")

        perMonth.forEach { year, value ->
            println("#1 Le chiffre d'affaire de l'année "+year+" est de: "+ getSumYear(perMonth,year));
        }
        perMonth.forEach { year, value ->
            value.keys.forEach{ month ->
                println("#1 Le chiffre d'affaire du mois de "+month+" de l'année "+year+" est de: "+ getSumMonth(perMonth,year,month));
            }
        }
        println("\n")

        perDevice.forEach { key, device ->
            println("#2 Le chiffre d'affaire avec l'appareil "+device.name+" est de : "+ getSumOfDevice(device))
        }
        println("\n")

        println("#3 Panier Moyen = $midShop")
        println("\n")

        println("#4 Coût par clic = $costClick")
        println("\n")

        println("#5 Taux de clic (Clic par impression)*100 = $clickPrint")
        println("\n")

        println("#6 ROI : Chiffre d'affaire total par coût = $roi")
        println("\n")

        perDevicePerYearPerMonth.forEach{deviceKey, device ->
            if (device.values.contains(2017)){
                device.values.get(2017)!!.keys.forEach { month ->
                    println("#7 Le chiffre d'affaire du mois de "+month+" de l'année 2017 avec l'appareil "+device.name+" est de : "+getSumDeviceYearAndMonth(
                        perDevicePerYearPerMonth,deviceKey,2017,month))
                }
            }
        }


        println("\n")



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
