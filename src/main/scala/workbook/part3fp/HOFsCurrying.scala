package workbook.part3fp

object HOFsCurrying {

  //higher order functions-- can use other functions as arguments or give a function as a result.

  val aHof: (Int, Int => Int) => Int = (x: Int, func) => x+1
  val anotherHof: Int => Int => Int = x => y => y + 2 * x

  //1 func takes int and func2, returns a function that takes an Int and returns an Int.
  //2 func takes a String and a 3 function, returns an Int
  //3 takes an Int and returns a boolean,
  val superfunction: (Int, (String, Int => Boolean) => Int) => (Int => Int) =
    (x, func) => y => x + y

  //more examples
  def nTimes(f: Int => Int, n: Int, x:Int): Int =
    if(n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x:Int) => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)


  /*
    ntv2 explaination:
      ntv2(plusOne, 3) -- 3 <= 0 ?? false -- moving on to else
      (x:Int) => ntv2(plusOne, 3-1)(plusOne(x)) -- result is ntv2 applied to x + 1
        = po(po(po(x)))

      ntv2(plusOne, 2) -- 2 <= 0 ?? false -- moving on to else
      (x:Int) => ntv2(plusOne, 2-1)(plusOne(x)) result is ntv2 applied to x + 1
        = po(po(x))

      ntv2(plusOne, 1) -- 1 <= 0 ?? false -- moving on to else
      (x:Int) => ntv2(plusOne, 1-1)(plusOne(x)) result is ntv2 applied to x + 1
        = po(x)

      continue until n <= 0 -- will return (x:Int) => x
      NOT TAIL RECURSIVE, WILL GET SO ON HIGH NUMS
   */
  def nTimes_v2(f:Int => Int, n:Int): Int => Int =
    if (n <= 0) (x:Int) => x
    else (x:Int) => nTimes_v2(f, n-1)(f(x))

  val plusTenThousand = nTimes_v2(plusOne, 100)
  val tenThousand_v2 = plusTenThousand(0)

  //currying-- passing arguments one at a time, a higher order function returning function instance

  val superAdder: Int => Int => Int = (x:Int) => (y:Int) => x + y
  val add3: Int => Int = superAdder(3)
  val invokeSuperAdder = superAdder(3)(100)

  //curried methods = methods with mutiple arg list
  //can invoke a function with fewer arg lists than in signature to obtain a function taking the rest of the args as result.
  def curriedFormatter(fmt: String)(x:Double): String = fmt.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f") // same as (x:Double) => "%4.2f".format(x)
  val preciseFormat: Double => String = curriedFormatter("%10.8f") // same as (x:Double) => "%10.8f".format(x)

  //exercises

  def toCurry(f:(Int, Int) => Int): Int => Int => Int =
    (x: Int) => (y:Int) => f(x, y)

  def toCurryGeneralized[A, B, C](f: (A, B) => C): A => B => C =
    (x: A) => (y: B) => f(x, y)  //must specify type on implementation

  val superAdder_v2 = toCurry((x,y) => x + y) //implementation 1
  val superAdder_v3 = toCurry(_ + _) //implementation 2 -- shortest version

  def fromCurry(f:Int => Int => Int): (Int, Int) => Int =
    (x:Int, y:Int) => f(x)(y)

  val simpleAdder = fromCurry(superAdder)

  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  def generalizedCompose[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))

  def generalizedAndThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))


  val incrementer = (x:Int) => x + 1
  val doubler = (x:Int) => x * 2
  val composedApplication = compose(incrementer, doubler)
  val aSequencedApplication = andThen(incrementer, doubler)



  def main(args: Array[String]): Unit = {
//    println(tenThousand)
//    println(standardFormat(Math.PI))
//    println(preciseFormat(Math.PI))
    // println(tenThousand_v2) --stackoverflow

    println(simpleAdder(2, 78))
    println(composedApplication(14)) // 29 = 2 * 14 + 1 -- 14
    println(aSequencedApplication(14)) // 29 = (1 + 14) * 2 -- 15
  }
}
