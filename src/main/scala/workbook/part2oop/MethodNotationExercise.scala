package workbook.part2oop

object MethodNotationExercise {
  class Person(val name: String, val age: Int, val favoriteMovie: String) {

    infix def +(nickname: String): Person =
      new Person(s"$name ($nickname)", age, favoriteMovie)

    infix def unary_+ : Person =
      new Person(name, age + 1, favoriteMovie)

    def apply(n:Int): String =
      s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", 34, "Inception")
  val maryNickname = mary + "the rockstar"
  val olderMary = +mary

  def main(args: Array[String]): Unit = {
    println(maryNickname.name)
    println(olderMary.age)
    println(mary(2))
  }
}
