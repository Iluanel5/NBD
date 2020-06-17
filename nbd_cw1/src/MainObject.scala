import scala.annotation.tailrec

/**
 * Autor: Karolina Niemira s21454
 */
object MainObject {

  val week = List[String]("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")
  val products = Map("Pizza S" -> 21.99, "Pizza M" -> 26.99, "Pizza L" -> 31.99, "Pizza XL" -> 36.99)
  val numbers = List[Int](1, 2, 0, -3, 0, 5, 0, 3, -2, 1, 7, 0, -8)
  val numbersDouble = List[Double](1.3, 2.6, -2.3, -55.2, 113.4, 2.0, 11.3)

  def main(args: Array[String]): Unit = {
    println("Zadanie 1:")
    println("a)  " + weekToStringInFor())
    println("b)  " + weekToStringInForStartWithP())
    println("c)  " + weekToStringInWhile())
    println("Zadanie 2:")
    println("a)  " + weekToStringRecursion(week))
    println("b)  " + weekToStringRecursionBackward(week))
    println("Zadanie 3:")
    println(weekToStringRecursionTail(week))
    println("Zadanie 4:")
    println("a)  " + weekToStringFoldLeft())
    println("b)  " + weekToStringFoldRight())
    println("c)  " + weekToStringFoldLeftStartWithP())
    println("Zadanie 5:")
    println(discountedProducts().mkString("\n "))
    println("Zadanie 6:")
    println(printTuple(3.33, "Placek", 2))
    println("Zadanie 7:")
    println("Wartosc dla 'Pizza S': " + optionExample(products.get("Pizza S")))
    println("Wartosc dla 'Hamburger': " + optionExample(products.get("Hamburger")))
    println("Zadanie 8:")
    println(deleteZeros(numbers).mkString(", "))
    println("Zadanie 9:")
    println(incraseByOne(numbers).mkString(", "))
    println("Zadanie 10:")
    println(getAbsoluteVelue(numbersDouble).mkString(", "))
  }


  def weekToStringInFor(): String = {
    var result = ""
    for (day <- week) {
      result += day
      if (week.last != day)
        result += ", "
    }
    result
  }

  def weekToStringInForStartWithP(): String = {
    var result = ""
    for (day <- week if day.startsWith("P")) {
      result += day + ", "
    }
    result
  }

  def weekToStringInWhile(): String = {
    var result = ""
    val i = week.iterator
    while (i.hasNext) {
      result += i.next()
      if (i.hasNext)
        result += ", "
    }
    result
  }

  def weekToStringRecursion(week: List[String]): String = {
    week match {
      case Nil => ""
      case head :: tail =>
        if (weekToStringRecursionBackward(tail) == "")
          head + weekToStringRecursion(tail)
        else
          head + ", " + weekToStringRecursion(tail)
    }
  }

  def weekToStringRecursionBackward(week: List[String]): String = {
    week match {
      case Nil => ""
      case head :: tail =>
        if (weekToStringRecursionBackward(tail) == "")
          weekToStringRecursionBackward(tail) + head
        else
          weekToStringRecursionBackward(tail) + ", " + head
    }
  }

  @tailrec
  def weekToStringRecursionTail(week: List[String], result: String = ""): String = {
    week match {
      case Nil => result
      case head :: tail => weekToStringRecursionTail(tail, if (result != "") result + ", " + head else result + head)
    }
  }

  def weekToStringFoldLeft(): String = week.foldLeft("")(_ + ", " + _)

  def weekToStringFoldRight(): String = week.foldRight("")(_ + ", " + _)

  def weekToStringFoldLeftStartWithP(): String = week.filter(day => day.startsWith("P")).foldLeft("")(_ + ", " + _)

  def discountedProducts(): Map[String, Double] = products.map(product => (product._1, product._2 * 0.9))

  def printTuple(tuple: (Double, String, Int)) = {
    println("Tuple1 = " + tuple._1)
    println("Tuple2 = " + tuple._2)
    println("Tuple3 = " + tuple._3)
  }

  def optionExample(option: Option[Double]) = option match {
    case Some(s) => s
    case None => "brak"
  }

  @tailrec
  def deleteZeros(list: List[Int], result: List[Int] = List.empty[Int]): List[Int] = {
    list match {
      case Nil => result.reverse
      case head :: tail => if (head == 0) deleteZeros(tail, result) else deleteZeros(tail, head :: result)
    }
  }

  def incraseByOne(list: List[Int]): List[Int] = {
    list.map(_+1)
  }

  def getAbsoluteVelue (list: List[Double]): List[Double] = {
    list.filter(x => x >= -5 && x <= 12).map(_.abs)
  }

}
