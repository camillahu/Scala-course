package workbook.part2oop

object AnonymusClasses {

  abstract class Animal {
    def eat(): Unit
  }

  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I'm a weird animal")
  }

  val someAnimal = new SomeAnimal
  val someAnimal_v2 = new Animal {
    override def eat(): Unit = println("I'm a weird animal")
  }

  /*
    This happens behind the scenes in someAnimal_v2:
    class AnonymusClasses.AnonClass$1 extends Animal {
      override def eat(): Unit = println("I'm a weird animal")
    }

    val someAnimal_v2 = new AnonymusClasses.AnonClass$1
   */

  def main(args: Array[String]): Unit = {

  }
}
