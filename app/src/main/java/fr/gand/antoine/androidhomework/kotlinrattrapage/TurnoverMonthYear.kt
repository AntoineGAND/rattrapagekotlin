package fr.gand.antoine.androidhomework.kotlinrattrapage

fun addValueToMap(map: MutableMap<Int,MutableMap<String,MutableList<Number>>>,year: Int,month: String,value: Number){
    var yearMutable = mutableMapOf<String,MutableList<Number>>()
    if (map.contains(year)){
        yearMutable = map.get(year)!!
    }

    var monthMutable = mutableListOf<Number>()
    if (yearMutable.contains(month)){
        monthMutable = yearMutable.get(month)!!
    }

    monthMutable!!.add(value);

    yearMutable.put(month,monthMutable)
    map.put(year,yearMutable)
}


fun getSumMonth(
    map: MutableMap<Int,MutableMap<String,MutableList<Number>>>,
    year: Int,
    month: String
): Float{
    if (map.contains(year)){
        val yearMutable = map.get(year)!!

        if (yearMutable.contains(month)){
            val monthMutable = yearMutable.get(month)!!
            var callback = 0f;

            monthMutable.forEach {
                callback += it.toFloat()
            }

            return callback;
        }
    }

    return 0f
}

fun getSumYear(
    map: MutableMap<Int,MutableMap<String,MutableList<Number>>>,
    year: Int
): Float{
    if (map.contains(year)){
        val yearMutable = map.get(year)!!
        var callback = 0f;

        yearMutable.forEach { key,_ ->
            callback += getSumMonth(map,year,key)
        }

        return callback;
    }

    return 0f



}



