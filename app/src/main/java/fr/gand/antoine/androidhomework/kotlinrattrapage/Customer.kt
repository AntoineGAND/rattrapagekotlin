package fr.gand.antoine.androidhomework.kotlinrattrapage

class Customer(var id: String?, var name: String?, var address: String?, var age: Int) {

    override fun toString(): String {
        return "Customer [id=$id, name=$name, address=$address, age=$age]"
    }
}