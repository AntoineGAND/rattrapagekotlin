package fr.gand.antoine.androidhomework.kotlinrattrapage

data class Data (
    var year: Int,
    var device: String,
    var order: Number,
    var print: Int,
    var click: Int,
    var cost: Number,
    var turnover: Number,
    var month: String) {

        override fun toString(): String {

            return "Data [Year=$year, Device=$device, Order=$order, Print=$print, Click=$click, Cost=$cost, CA=$turnover, Mounth=$month]"

        }

}
