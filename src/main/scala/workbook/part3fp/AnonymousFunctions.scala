package workbook.part3fp

object AnonymousFunctions {

  //instances of FunctionN
  val doubler: Int => Int = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }
  //syntactic sugar:
  //lambdas = anonymous function instances
  val doubler_v2: Int => Int = (x: Int) => x * 2 //identical
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y //Function2
  //zero-arg functions
  val justDoSomething: () => Int = () => 45
  val anInvocation = justDoSomething()

  //alt syntax with curly braces-- makes it a code block (you can define local variables etc.)
  val stringToInt = {(str: String) =>
    //implementation: code block
    str.toInt
  }
  val stringToIntBoring = (str: String) => {
    //code block
  }

  //type inference
  val doubler_v3: Int => Int = x => x * 2 //type inference by compiler
  val adder_v2: (Int, Int) => Int = (x, y) => x + y

  //shortest lambdas
  val doubler_v4: Int => Int = _ * 2 //x => x * 2 -- underscore is whatever x is.
  val adder_v3: (Int, Int) => Int = _ + _ // (x, y) => x + y -- we don't care about how the args are called
  //each underscore is a different argument. 

  def main(args: Array[String]): Unit = {
    println(anInvocation)
  }
}
