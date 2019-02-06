package fr.gand.antoine.androidhomework.kotlinrattrapage

data class DeviceYearMonth(
    val name: String,
    val values: MutableMap<Int,MutableMap<String,MutableList<Number>>>
)

fun addValueToDeviceYearAndMonth(map: MutableMap<String,DeviceYearMonth>,deviceName: String,year: Int,month: String,value: Number){
    val deviceKey = parseStringKey(deviceName)
    var deviceObject: DeviceYearMonth?

    if (map.contains(deviceKey)){
        deviceObject = map.get(deviceKey)!!

        val yearMutable: MutableMap<String,MutableList<Number>>?
        if (deviceObject.values.contains(year)){
            yearMutable = deviceObject.values.get(year)!!

            var monthMutable: MutableList<Number>?
            if (yearMutable.contains(month)){
                monthMutable = yearMutable.get(month)
                monthMutable!!.add(value)
            }else{
                monthMutable = mutableListOf(value)
            }


            yearMutable!!.put(month,monthMutable)
        }else{
            yearMutable = mutableMapOf(Pair(month, mutableListOf(value)))
        }
    }else{
        deviceObject = DeviceYearMonth(deviceName, mutableMapOf(Pair(year,mutableMapOf(Pair(month, mutableListOf(value))))))
    }
    map.put(deviceKey,deviceObject!!)
}

fun getSumDeviceYearAndMonth(map: MutableMap<String,DeviceYearMonth>,deviceKey: String,year: Int,month: String): Float{
    var callback = 0f;

    if (map.contains(deviceKey)){
        val deviceObject = map.get(deviceKey)!!;

        if (deviceObject.values.contains(year)){
            val yearMutable = deviceObject.values.get(year)!!

            var monthMutable: MutableList<Number>?
            if (yearMutable.contains(month)){
                val monthList = yearMutable.get(month)
                monthList!!.forEach { value ->
                    callback += value.toFloat()
                }
            }
        }
    }

    return callback;
}



