package workbook.part2oop

import scala.language.postfixOps // - heavily discouraged

object MethodNotation {

  class Person( val name: String, age: Int, favoriteMovie: String) {
    infix def likes(movie:String):Boolean =
      favoriteMovie == movie

    infix def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    infix def !!(progLanguage:String): String =
      s"$name wonders how can $progLanguage be so cool!"

    //prefix position
      //unary ops: -, +, ~, !
    def unary_- : String =
      s"$name's alter ego"

    //postfix method

    def isAlive:Boolean = true

    //apply method makes it so you can call the method like this: instance()
    //can also take arguments.
    def apply():String =
      s"Hi, my name is $name and I really enjoy $favoriteMovie"
  }

  val mary = new Person("Mary", 34, "Inception")
  val john = new Person("John", 36, "Fight Club")

  def main(args: Array[String]): Unit = {
    println(mary.likes("Fight Club"))

    //infix notation- for methods with ONE argument
    println(mary likes "Fight Club") //identical

    //"operator" = plain method with that name
    println(mary + john)
    println(mary.+(john)) //identical

    println(2 + 3)
    println(2 .+(3)) //identical

    println(mary !! "Scala")

    //prefix position
    println(-mary)
    println(mary.unary_-) //the same

    //postfix position
    println(mary.isAlive)
    println(mary isAlive) //import postfixOps- heavily discouraged, stick with dot notation.

    println(mary.apply())
    println(mary()) //identical
  }
}
