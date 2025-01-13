package workbook.part4power
import workbook.practise.*

object AllThePatterns {

  object MySingleton

  //1 - constants
  val someValue: Any = "Scala"
  val constants = someValue match {
    case 42 => "a number"
    case "Scala" => "THE Scala"
    case true => "the truth"
    case MySingleton => "a singleton object"
  }

  //2 - match anything

  val matchAnythingVar = 2 + 3 match {
    case something => s"I've matched anything, it's $something"
  }

  val matchAnything = someValue match {
    case _ => "I can match anything at all"
  }

  // 3- tuples
  val aTuple = (1, 4)
  val matchTuple = aTuple match {
    case (1, somethingElse) => s"A tuple with 1 and $somethingElse"
    case (somethingElse, 2) => s"A tuple with 2 as it's second field"
  }

  //Pattern matching structures can be nested
  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => "A nested tuple ..."
  }

  //4- case classes
  val aList: LList[Int] = Cons(1, Cons(2, Empty()))
  val matchList = aList match {
    case Empty() => "an empty list"
    case Cons(head, Cons(_, tail)) => s"a non-empty list starting with $head"
  }

  // pm with options and try works like this because they are case classes.
  val anOption: Option[Int] = Option(2)
  val matchOptions = anOption match {
    case None => "an empty option"
    case Some(value) => s"non-empty, got $value"
  }

  //5 - list patterns
  val aStandardList = List(1,2,3,42)
  val macthStandarsList = aStandardList match {
    case List(1, _, _, _) => "list with 4 elements, first is 1" //list with a specified number of elements
    case List(1, _*) => "list starting with 1" //list with a non specified number of elements
    case List(1, 2, _) :+ 42 => "list ending in 42"
    case head :: tail => "deconstructed list"
  }

  def main(args: Array[String]): Unit = {

  }
}
