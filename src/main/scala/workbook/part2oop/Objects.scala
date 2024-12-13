package workbook.part2oop

object Objects {

  object MySingleton { // type + the only instance of this type
    val aField = 45
    def aMethod(x:Int) = x + 1
  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton

  //objects can have feilds and methods
  val theSingletonField = MySingleton.aField
  val theSingletonMethodCall = MySingleton.aMethod(99)

  class Person(name:String) {
    def sayHi(): String = s"Hi, my name is $name"
  }
  //companions = class + object with the same name in the same file
  //can access each other's private fields and methods.
  object Person { // companion object
    val N_EYES = 2 //general practice to write upper case constants if they don't depend on an instance.
    def canFly(): Boolean = false
  }

  //methods and fields in classes are used for instance-dependent functionality
  val mary = new Person("Mary")
  val mary_v2 = new Person("Mary")
  val marysGreeting = mary.sayHi()

  //methods and fields in objects are used for instance-independent functionality - "static"
  val humansCanFly = Person.canFly()
  val nHumanEyes = Person.N_EYES

  //equality
  //1- equality of reference - usually defined as ==
  //  point to the exact same instance in memory (mary == mary, theSingleton == anotherSingleton, mary != mary_v2)
  val sameMary = mary eq mary_v2 //false, different instance
  val sameSingleton = MySingleton eq MySingleton // true

  //2- equality of "sameness" - in Java defined as .equals
  //equals method in Scala can be overridden.
  val sameMary_v2 = mary equals mary_v2 //false
  val sameMary_v3 = mary == mary_v2 // same as equals - false
  val sameSingleton_v2 = MySingleton == MySingleton // true

  // object can extend classes
  object BigFoot extends Person("Big Foot")

  //Scala application == object + def main(args: Array[String]): Unit

  def main(args: Array[String]): Unit = {
    println(isSameSingleton)
    println(sameMary_v2)
  }
}
