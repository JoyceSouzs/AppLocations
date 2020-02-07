
fun main(args:Array<String>){
    insertCustomer()
    val customer = Customer("Joyce", 29, System.nanoTime())
    checkInCustomer(customer)
    checkOutCustomer()
    sortCustomer()
}

val listPrincipalCustomer : MutableList<Customer> = mutableListOf()
fun insertCustomer(){
    val customer = Customer("João", 22, System.nanoTime())
    val customer1 = Customer("Maria", 30, System.nanoTime())
    val customer2 = Customer("José", 25, System.nanoTime())
    val customer3 = Customer("Alice", 22, System.nanoTime())

    listPrincipalCustomer.add(customer)
    listPrincipalCustomer.add(customer1)
    listPrincipalCustomer.add(customer2)
    listPrincipalCustomer.add(customer3)

}
fun sortCustomer(){
    println(listPrincipalCustomer.sortedWith(compareByDescending<Customer> {
        it.age
    }.thenBy { it.time }))
}

fun checkInCustomer(customer: Customer){
    listPrincipalCustomer.add(customer)
}
fun checkOutCustomer(){
    listPrincipalCustomer.removeAt(1)
}


