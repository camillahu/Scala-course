package workbook.part2oop

object CaseClasses {

  //lightweight data structures, not suitable for big classes.
  //1- class args are feilds, don't need val.
  case class Person(name:String, age:Int) { //can add curly braces to aff functionality to case class
    //do some stuff
  }

  val daniel = Person("Daniel", 99)
  val danielsAge = daniel.age

  //2- toString, equals and hashCode
  //    toString automatically prints: Person(Daniel,99)
  //    equals will check if the values are same in case classes, not if it points to the same object in memory(like class)
  val danielToString = daniel.toString
  val danielDuped = Person("Daniel", 99)
  val isSameDaniel = daniel == danielDuped //true

  //3- utility methods
  val danielYounger = daniel.copy(age = 78) //will create new person instance where age is changed.

  //4- case classes have companion objects.
  val thePersonSingleton = Person
  val daniel_v2 = Person("Daniel", 99) //apply method again, basically a "constructor".

  // 5- case classes are serializable-- use case: Akka

  // 6- case classes have extractor patterns for PATTERN MATCHING

  //  case class CCWithNoArgs {} -- not allowed
  // to use case with no args, use case object instead which takes no arguments (useful only with note 2 and 5):
  case object UnitedKingdom {
    // fields and methods
    def name: String = "UK"
  }

  // legal, but does not make sense from an equality perspective, but can make sense with generics:
  case class CCWithArgListNoArgs()

  def main(args: Array[String]): Unit = {
      println(daniel)
  }
}
