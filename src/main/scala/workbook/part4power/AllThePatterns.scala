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

  //6 - type specifiers
  val unknown: Any = 2
  val matchTyped = unknown match {
    case anInt: Int => s"I matched an int, I can add 2 to it: ${anInt + 2}"
    case aString: String => "I matched a string"
    case _: Double => "I matched a double I don't care about"
  }

  //7- name binding
  val bindingNames = aList match {
    case Cons(head, rest @Cons(_, tail)) => s"Can use $rest"
  }

  //8 -chained patterns
  val multiMatch = aList match {
    case Empty() | Cons(0, _) => "an empty list to me"
    case _ => "no match found"
  }

  //9 - if guards
  val secondElementSpecial = aList match {
  case Cons(_, Cons(specialElement, _)) if specialElement > 5 => "second element is big enough"
  case _ => "no match found"
  }

  //anit-pattern-- do not use!
  val aSimpleInt = 45
  val isEven = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  //use this again!
  val isEvenInt = aSimpleInt % 2 == 0



  def main(args: Array[String]): Unit = {
    println(isEvenInt)
  }
}
