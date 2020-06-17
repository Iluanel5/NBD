/**
 * Autor: Karolina Niemira s21454
 */
object MainObject {

  val week = List[String]("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")
  val person1 = new Person("Katarzyna", "Placowa")
  val person2 = new Person("Krzysztof", "Zielonka")
  val person3 = new Person("Karolina", "Niemira")
  val person = new Person5
  val sensei = new Person5 with Teacher
  val student = new Person5 with Student
  val employee = new Person5 with Employee
  val senseiEmployee = new Person5 with Teacher with Employee
  val employeeSensei = new Person5 with Employee with Teacher


  def main(args: Array[String]): Unit = {
    println("Zadanie 1:")
    for(day <- week){
      println(day + ": " + patternMatchingForWeeks(day))
    }
    println("execute order 66: " + patternMatchingForWeeks("execute order 66"))
    println("Zadanie 2:")
    var bankAccount = new BankAccount()
    println("Konstruktor ustawiający na zero - stanKonta: " + bankAccount.accountBalance)
    bankAccount = new BankAccount(100)
    println("Konstruktor ustawiający sumę 100 - stanKonta: " + bankAccount.accountBalance)
    bankAccount.paymentIntoAccount(20)
    println("Po wpłacie na konto 20 - stanKonta: " + bankAccount.accountBalance)
    bankAccount.withdrawMoneyFromAccount(80)
    println("Po wypłacie z konta 80 - stanKonta: " + bankAccount.accountBalance)
    println("Zadanie 3:")
    println("Unikalne powitanie imię + nazwisko: " + patternMatchingForPerson(person3))
    println("Unikalne powitanie imię: " + patternMatchingForPerson(person1))
    println("Domyślne powitanie: " + patternMatchingForPerson(person2))
    println("Zadanie 4:")
    println("Wywołanie dla x=2 funkcji x+3 (oczekiwane: x+3+x+3+x+3) - wynik: " + doSthThreeTimes(2, fun1))
    println("Wywołanie dla x=2 funkcji x*x (oczekiwane: x*x+x*x+x*x) - wynik: " + doSthThreeTimes(2, fun2))
    println("Zadanie 5:")
    println("Zadanie 5")
    println("Osoba podatek: "+ person.tax)
    println("osoba -> pracownik podatek: "+ employee.tax)
    println("osoba -> nauczyciel podatek: "+ sensei.tax)
    println("osoba -> student podatek: "+ student.tax)
    println("osoba -> pracownik -> nauczyciel podatek: "+ employeeSensei.tax)
    println("osoba -> nauczyciel -> pracownik podatek: "+ senseiEmployee.tax)
  }

  /**
   * Zadanie 1 - pattern Matching dla dni tygodnia
   * @param day
   * @return
   */
  def patternMatchingForWeeks (day: String): String = day match {
    case "Poniedzialek" | "Wtorek" | "Sroda" | "Czwartek" | "Piatek" => "Praca"
    case "Sobota" | "Niedziela" => "Weekend"
    case _ => "It's a trap!"
  }

  /**
   * Zadanie 3 - pattern matching dla Osoby
   * @param person
   * @return
   */
  def patternMatchingForPerson(person: Person): String = person match {
    case tmp: Person if tmp._name == "Katarzyna" => "Z Kaśką można było konie kraść~ Siema Kaśka!"
    case tmp: Person if tmp._name == "Jolanta" => "Jolka mnie Zagłaskałaby na smierć~ Hejka hej Jola!"
    case tmp: Person if tmp._name == "Agnieszka" => "A Agnieszka zdradzała mnie~ Cześć Aga!"
    case tmp: Person if tmp._name == "Karolina" && tmp._surname == "Niemira" =>
      "O, to studentka co to wszystko napisała. Witamy Karolinę!"
    case _ => "Dzień dobry, " + person.display()
  }

  /**
   * Zadanie 3 - zrobienie danej funkcji trzykrotnie (funkcje niżej)
   * @param arg
   * @param fun
   * @return
   */
  def doSthThreeTimes(arg: Int, fun: (Int) => Int): Int = {
    var toReturn = 0
    1.to(3).foreach(i => toReturn += fun(arg))
    toReturn
  }

  def fun1(x: Int) = x + 3
  def fun2(x: Int) = x * x

}
