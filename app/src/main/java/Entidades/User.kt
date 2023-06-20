package Entidades

data class User(
    var id: String = "",
    var nickName: String = "",
    var password: String = "",
    var name: String = "",
    var surname: String = "",
    var money: Double = 0.0,
    var createdDate: String = ""
) {
    fun restarMoney(compra:Double) {
        this.money = compra
    }
    fun reintegroMoney(cashback:Double) {
        this.money += cashback
    }
    override fun toString(): String {
        return "User(Id:='${id}', Nickname = '$nickName',Name = '$name', Surname = '$surname' , Money ='${money}', Fecha de alta ='$createdDate')"
    }
}