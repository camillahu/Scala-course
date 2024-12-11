package workbook.part2oop

object OOBasics {

  //classes
  class Person(val name:String, val age:Int) { // constructor signature
    //fields
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String): String =
      s"${this.name} says: Hi, $name"
    // this.name refers to the name in the Person class
    // name refers to the name parameter of the greet method.

    //overload method- different method signature, this one takes no arguments.
    //an overload method has to have either different argument types or number of arguments.
    def greet(): String =
      s"Hi, everyone. My name is $name"
      //this. keyword is not needed in this instance, the compiler understands what name is referring to.

    // aux constructor (quite rarely used, basically the same as default values.)
    def this(name: String) =
      this(name, 0)

    def this() =
      this("Jane Doe")
  }

  val aPerson: Person = new Person("John", 26)
  val john = aPerson.name // class parameter is not the same as class field in Scala.
  val johnYelling = aPerson.allCaps
  val johnSayHiToDaniel = aPerson.greet("Daniel")

  def main(args: Array[String]): Unit = {
    println(johnSayHiToDaniel)
  }
}
