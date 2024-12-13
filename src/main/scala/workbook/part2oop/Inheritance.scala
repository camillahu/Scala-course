package workbook.part2oop

object Inheritance {

  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("nomnomnom")
  }

  class Cat extends Animal {
    def crunch():Unit = {
      eat()
      println("crunch, crunch")
    }
  }

  val cat = new Cat

  class Person(val name:String, age: Int) {
    def this(name: String) =
      this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name) //must specify super-constructor

  class Dog extends Animal {
    override val creatureType: String = "domestic"
//    override def eat(): Unit = super.eat() -- super keyword uses the supertype implementation of eat (nomnomnom)
    override def eat(): Unit = println("mmm, I like this bone")

    //popular overridable method
    override def toString: String = "a dog" //whenever printing (dog), this will appear.
  }

  //subtype polymorphism
  val dog = new Dog

  //overloading vs overriding


  class Crocodile extends Animal {

    //overriding- giving a child class other implementations of the parent's methods or feilds
    override val creatureType: String = "very wild"
    override def eat(): Unit = println("I can eat anything, I'm a croc")

    //overloading- giving a method a different signature--
    //different argument list(mandatory) + different return type(optional).
    def eat(animal:Animal): Unit = println("I'm eating this poor fella")
  }

  def main(args: Array[String]): Unit = {
    cat.eat()
    cat.crunch()
    dog.eat()
    println(dog) //printing the class implementation will print the toString method

  }
}
