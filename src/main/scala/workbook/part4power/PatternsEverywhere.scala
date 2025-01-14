package workbook.part4power

object PatternsEverywhere {

  //1 -- catches are actually matches

  val potentialFailure = try {
    //code
  } catch {
    case e: RuntimeException => "runtime e"
    case npe: NullPointerException => "npe"
    case _ => "some other exception"
  }

  //2 -- for comps( generators) are based on Pattern Matching
  val aList = List(1,2,3,4)
  val evenNumbers = for {
    n <- aList if n % 2 == 0
  } yield 10 * n

  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples if first < 3
  } yield second * 100

  //3 -- new syntax
  val aTuple = (1,2,3)
  val (a, b, c) = aTuple

  val head :: tail = tuples

  def main(args: Array[String]): Unit = {

  }
}
