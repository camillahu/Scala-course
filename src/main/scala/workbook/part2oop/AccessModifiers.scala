package workbook.part2oop

object AccessModifiers {

  class Person(val name:String) {
    protected def sayHi(): String = s"Hi, my name is $name"
    private def watchNetflix(): String = "I'm binge watching my favorite show"
  }

  // protected = access to inside the class and it's children
  // public = default if no modifier is specified.
  // private = only accessible inside class.

  class Kid(override val name: String, age:Int) extends Person(name) {
    def greetPolitely(): String =
      sayHi() + "I love to play!"
  }

  class KidWithParents(override val name:String, age: Int, momName: String, dadName: String) extends Person(name) {
    val mom = new Person(momName)
    val dad = new Person(dadName)

//    def everyoneSayHi(): String =
//     s"Hi, I'm $name, and here are my parents: " + mom.sayHi() + dad.sayHi()
    //     -- not legal because kid does not inherit from mom or dad.
  }

  val aPerson = new Person("Alice")
  val aKid = new Kid("David", 5)

  def main(args: Array[String]): Unit = {
    println(aKid.greetPolitely())
  }
}
