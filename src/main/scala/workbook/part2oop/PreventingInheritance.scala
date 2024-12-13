package workbook.part2oop

object PreventingInheritance {

  class Person(name:String) {
    //final keyword makes sure this method can't be overridden.
    final def enjoyLife(): Int = 42
  }

  class Adult(name: String) extends Person(name) {
//    override def enjoyLife(): Int = 999 -- cannot override final method
  }

  final class Animal //can't be inherited
//  class Cat extends Animal -- illegal

  //sealing a type hierarchy = inheritance is only permitted within the file
  sealed class Guitar(nStrings:Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // no modifier = "not encouraging" inheritance
  // good practise to use open keyword to mark a class as extendable, and provide good documentation.
  // not mandatory, but good practise 

  open class ExtensibleGuitar(nStrings:Int)


  def main(args: Array[String]): Unit = {

  }
}
