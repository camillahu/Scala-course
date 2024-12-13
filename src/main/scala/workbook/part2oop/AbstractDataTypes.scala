package workbook.part2oop

object AbstractDataTypes {

  abstract class Animal {
    val creatureType: String //abstract -- no implementation
    def eat(): Unit

    //an abstract class can have non-abstract fields or methods
    def preferredMeal: String = "anything" // "accessor method"-- method without args/parenthesis
  }

//  abstract classes can't be instansiated
//  val anAnimal: Animal = new Animal -- illegal

  //non-abstract classes must implement the abstract fields/methods
  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat(): Unit = println("crunching this bone")

    //overriding is legal for everything
    //overriding accessor method (without args/parenthesis) with a field is allowed in Scala.
    override val preferredMeal: String = "bones"
  }

  //trait -- a datatype that describes a behavior
  trait Carnivore { /// Scala 3- traits can have constructor args
    //can provide implementation, but can also be left abstract for each class that has the trait to implement.
    def eat(animal: Animal): Unit
  }

  class TRex extends Carnivore {
    override def eat(animal:Animal): Unit = println("I'm a T-Rex, I eat animals.")
  }

  //difference between abstract class vs trait:

  // practical- class can inherit from one class, but have multiple traits.
  // traits can be chained using "with" keyword

  //philosophical- abstract class is a thing, trait is a description of behavior. 


  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat(): Unit = println("I'm a croc, I just crunch stuff")
    override def eat(animal:Animal): Unit = println("croc eating animal")
  }

  def main(args: Array[String]): Unit = {

  }
}
