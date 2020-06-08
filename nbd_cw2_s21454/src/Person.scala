class Person(name: String, surname: String) {

  val _name: String = name
  val _surname: String = surname

  def display(): String = _name + " " + _surname

}
