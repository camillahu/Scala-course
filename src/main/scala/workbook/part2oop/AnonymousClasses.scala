package workbook.part2oop

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  //classes used for just one instance are bolierplate-y
  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I'm a weird animal")
  }

  val someAnimal = new SomeAnimal

  val someAnimal_v2 = new Animal { //anonymous class
    override def eat(): Unit = println("I'm a weird animal")
  }

  /*
    This happens behind the scenes in someAnimal_v2:
    class AnonymousClasses.AnonClass$1 extends Animal {
      override def eat(): Unit = println("I'm a weird animal")
    }

    val someAnimal_v2 = new AnonymousClasses.AnonClass$1
   */


  //works for classes(abstract or not) and traits.
  class Person(name:String) {
    def sayHi(): Unit = println(s"Hi, my name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println("MY NAME IS JIM!")
  }

  def main(args: Array[String]): Unit = {

  }
}
