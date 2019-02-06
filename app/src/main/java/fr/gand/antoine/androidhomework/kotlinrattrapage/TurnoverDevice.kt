package fr.gand.antoine.androidhomework.kotlinrattrapage

import java.text.Normalizer

data class Device(
    val name: String,
    val values: MutableList<Number>
)
fun parseStringKey(name: String): String{
    return Normalizer.normalize(name, Normalizer.Form.NFD)
        .replace("[\\p{InCombiningDiacriticalMarks}]+", "")
        .replace(' ','_')
}

fun addDevice(map: MutableMap<String,Device>,deviceName: String,value: Number){
    val key = parseStringKey(deviceName)
    val deviceObject: Device?

    if (map.contains(key)){
        deviceObject = map.get(key)

        deviceObject!!.values.add(value)
    }else{
        deviceObject = Device(deviceName,mutableListOf(value))
    }

    map.put(key,deviceObject!!)
}
fun getSumOfDevice(device: Device): Float{
    var callback = 0f;

    device.values.forEach { value ->
        callback += value.toFloat()
    }

    return callback;
}