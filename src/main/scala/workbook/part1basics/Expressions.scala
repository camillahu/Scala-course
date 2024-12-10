package workbook.part1basics

object Expressions {

  //expressions are structures that can be evaluated to a value
  val meaningOfLife: Int = 40 + 2

  // mathematical expressions: +, -, *, /,
  // |, &, <<, >>, >>> (bitwise or, and, left shift, right shift, right shift with zero extension)

  val mathExpression: Int = 2+3*4

  //comparison expressions: <, <=, >, >=, ==, !=
  val equalityTest: Boolean = 1 == 2

  //boolean expressions: !, ||, && (not, or, and)
  val nonEqualityTest: Boolean = !equalityTest

  //instructions vs expressions:
  //expressions are evaluated, instructions ae executed.
  //in FP we think in terms of expressions.

  //if are expressions in Scala because it is evaluated to a single value.
  val aCondition = true
  private val onIfExpression: Int = if(aCondition) 45 else 99

  //code blocks are a big expression in Scala. The last expression is what gets returned.
  val aCodeBlock: Int = {
    val localValue = 78
    localValue + 54
  }

  val someValue: Boolean = {
    2<3
  }

  val someOtherValue: Int = {
    if(someValue) 239 else 984
    42
  }

  val yetAnotherValue: Unit = println("Scala") //this will print scala because it happens before it is evaluated. 

  def main(args: Array[String]): Unit = {
  println(someValue); //true
  println(someOtherValue); //42
  println(yetAnotherValue); // () - the only value of Unit(the value of the ect of printing itself)
  }
}
