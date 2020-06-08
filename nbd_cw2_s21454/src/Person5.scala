/**
 * Zadanie 5 - klasa Osoba
 */
class Person5 {
  private var _name: String = null
  private var _surname: String = null
  var _tax: Double = 0.11
  def name = _name
  def surname = _surname
  def tax = _tax
}
trait Employee extends Person5 {
  var salary: Double = 100;
  override def tax: Double = 0.2*salary
}
trait Student extends Person5 {
  override def tax: Double = 0;
}
trait Teacher extends Employee {
  override def tax: Double = 0.1 * salary
}