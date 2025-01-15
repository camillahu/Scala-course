package workbook.part4power

object BracelessSyntax {

  //if-expressions
  val anIfExpression = if(2>3) "bigger" else "smaller"

  //java-style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  //compact style
  val anIfExpression_v3 =
    if (2>3) "bigger"
    else "smaller"

  //scala 3 exclusive -- indentation is important
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"

  //scala 3 exclusive for comprehensions

  val aForComp =
    for
      n <- List(1,2,3)
      s <- List("black", "white")
    yield s"$n$s"

  // scala 3 exclusive pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  //methods without braces

  def computeMeaningOfLife(arg:Int): Int =
    val partialResult = 40


    partialResult + arg


  //class definition with significant indentation (same for traits, objects, enums)
  class Animal:
    def eat(): Unit =
        println("I'm eating")

    def grow(): Unit =
       println("I'm growing")

    //3000 more lines of code
  end Animal //end token can be used for if, match, for, methods, classes, traits, enums, objects etc

  //anon classes
  val aSpoecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")


  def main(args: Array[String]): Unit = {
    println(computeMeaningOfLife(2))
  }
}
