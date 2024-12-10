package workbook.part1basics

object Functions {
  //implementation of a function is always one expression.
  def aFunction(a: String, b: Int): String =
    a + " " + b


  //invocation
  val aFunctionInvocation = aFunction("Scala", 99999)

  //styles of functions taking no args/parameters:
  def aNoArgFunction(): Int = 45

  def aParameterLessFunction: Int = 45


  //functions can be recursive- a function can call itself inside its own function.
  //when loops are needed, use recursion.

  private def stringConcatination(str: String = "Scala", n: Int): String = {
    if (n == 0) ""
    else if (n == 1) str
    else str + stringConcatination(str, n - 1)
  }
  val scala3: String = stringConcatination(n = 3)


  def  aVoidFunction(aString:String): Unit = {
    println(aString)
  }

  //this type of code with side effects is discouraged.
  def computeDoubleStringWithSideEffects(aString: String): String = {
    aVoidFunction(aString) //Unit
    aString + aString //meaningful value
  }

  def aBigFunction(n:Int): Int = {
    // small, auxilary functions inside
    def aSmallerFunction(a: Int, b: Int):Int = a + b
    aSmallerFunction(n, n +1)
  }

  //Exercises:

  def greetingFunction(name: String, age:Int): String = "Hi my name is " + name + " and I am " + age + " years old"

  def factorialFunction(n:Int): Int = {
    if (n<=0) 0
    else if (n==1) n
    else n * factorialFunction(n-1)
  }

  def fibonacciFunction (n:Int): Int = {
    if (n<=2) 1
    else fibonacciFunction(n-1) + fibonacciFunction(n-2)
  }

  def isPrime (n:Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if(t <= 1) true
      else n % t !=0 && isPrimeUntil(t-1)

    isPrimeUntil(n/2)
  }


  def main(args: Array[String]): Unit = {
    println(greetingFunction("Camilla", 24))
    println(factorialFunction(5))
    println(fibonacciFunction(5))
    println(isPrime(8))
  }
}
