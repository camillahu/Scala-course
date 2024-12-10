package workbook.part1basics

object CBNvsCBV {

  //CBV- call by value = arguments are evaluated before function invocation
  def aFunction(arg:Int):Int = arg + 1 //this is computed as 90+1
  val aComputation = aFunction(23+67) // 23+67 is evaluated first.

  //CBN - call by name = arguments are passed literally.
  def aByNameFunction(arg: => Int): Int = arg + 1 //the arrow makes it so the code is equivalent to 23 + 67 + 1.
  val anotherComputation = aByNameFunction(23+67) //23+67 is evaluated when the function is run.

  def printTwiceByValue(arg: Long): Unit = {
    println("By value: " + arg)
    println("By value: " + arg)
    //siden argumentet blir evaluert først,så blir resultatet alltid det samme.
    // vi kan sjekke ved å printe System.nanoTime().
  }

  def printTwiceByName(arg: => Long): Unit = {
    println("By name: " + arg)
    println("By name: " + arg)
    //siden argumentet blir evaluert på nytt hver gang det blir brukt, vil System.nanoTime() bli forskjellig.
  }

  //"farlige" funksjoner kan kalles ved navn for at de ikke skal kræsje programmet.
  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)


  def main(args: Array[String]): Unit = {
    printFirst(42, infinite())
  }
}
