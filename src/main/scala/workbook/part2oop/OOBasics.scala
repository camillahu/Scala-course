package workbook.part2oop

object OOBasics {

  //classes
  class Person(val name:String, val age:Int) { // constructor signature
    //fields
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String): Unit = {
      println(s"$name says: Hi, $name")
    }
  }

  val aPerson: Person = new Person("John", 26)
  val john = aPerson.name // class parameter is not the same as class field in Scala.
  val johnYelling = aPerson.allCaps


  def main(args: Array[String]): Unit = {
    aPerson.greet("camilla")
  }
}
