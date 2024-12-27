package workbook.part3fp

object WhatsAFunction {

  //FP: functions are "first-class" citizens
  // JVM

  //the apply method makes it so that instances of MyFunction can be invoked as functions.
  //now you can pass around instances and return instances as a result of MyFunction around,
  //making it a "first-class" citizen.
  trait MyFunction[A, B] {
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int) = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler(meaningOfLife)  //same as doubler.apply(meaningOfLife)

  // function types

  //Function1(2, 3 and so on...) are functions in the scala standard library that can be used to create
  //functions with different number of arguments.

  //Function1 takes one type arguments and specifies the return type.
  val doublerStandard = new Function1[Int,Int] {
    override def apply(arg: Int) = arg * 2
  }
  val meaningDoubled_v2 = doublerStandard(meaningOfLife)

  //Function2 takes two type arguments and specifies one return type.
  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int) = a+b
  }
  val anAddition = adder(2, 67)

  // (Int, String, Double, Boolean) => Int == Function4[Int, String, Double, Boolean, Int]
  val aFourArgFunction = new Function4[Int, String, Double, Boolean, Int] {
    override def apply(v1: Int, v2: String, v3: Double, v4: Boolean): Int = ???
  }

  //all functions are instances of FunctionX

  //Exercises

  val concatStrings = new Function2[String, String, String] {
    override def apply(a:String, b:String) = a + " " + b
  }

  val concatStrings_v2: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + " " + b
  }

  val concatStrings_v3: (String, String) => String = (a: String, b: String) => a + " " + b


  val returningFunction: Int => Int => Int = (i: Int) => (x: Int) => i + x

  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int) = x + y
    }
  }

  val superAdder_v2 = new (Int => Int => Int) {
    override def apply(x: Int) = (y: Int) => x + y
  }

  val superAdder_v3: Int => Int => Int = x => y => x + y //chatgpt
  // Int => Int => Int -- represents a curried function
  // The first Int is the input to the outer function
  // The second Int (Int => Int) is the type of the function returned by the outer function
  // The third Int is the result of applying the inner function

  //x => y => x + y
  //x=> -- The outer lambda takes an Int (x) and returns a function
  //y => x + y -- The inner lambda takes another Int (y) and returns the sum of x + y.

  val superAdder_v4 = (x: Int) => (y: Int) => x + y


  //currying
  val adder2 = superAdder_v2(2)
  val anAddition_v2 = adder2(67) // 69

  val anAddition_v3 = superAdder_v2(2)(67) //same as above

  //function values are not the same as methods.

  def main(args: Array[String]): Unit = {
//    println(meaningDoubled)
//    println(meaningDoubled_v2)

    println(concatStrings("Hei", "på deg"))
    println(anAddition_v3)
  }
}
