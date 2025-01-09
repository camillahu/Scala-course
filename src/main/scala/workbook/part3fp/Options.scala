package workbook.part3fp

import scala.util.Random

object Options {

  //options = "collections" with at most one value
  val anOption: Option[Int] = Option(42)
  val anEmptyOption: Option[Int] = Option.empty

  //alt version
  val aPresentValue: Option[Int] = Some(4)
  val anEmptyOption_v2: Option[Int] = None

  //"standard" API
  val isEmpty = anOption.isEmpty
  val innerValue = anOption.getOrElse(90) //if option is empty, the output will be 90.
  val anotherOption = Option(46)
  val aChainedOption = anEmptyOption.orElse(anotherOption) //if first option is empty, choose the other one

  //processing values inside options: map, flatMap, filter, for comp
  val anIncrementedOption = anOption.map(_ + 1) //Some(43)
  val aFilteredOption = anIncrementedOption.filter(_ % 2 == 0) //None -- doesn't pass the predicate
  val aFlatMappedOption = anOption.flatMap(value => Option(value*10)) // Some(420)

  //WHY use options: work with unsafe API
  def unsafeMethod():String = null
  def fallbackMethod():String = "some valid result"

  //defensive style -- can be impossible to debug, especially if something in the application is
  //dependent on the result of the method
  val stringLength = {
    val potentialString = unsafeMethod()
    if(potentialString == null) -1
    else potentialString.length
  }

  //option style
  val stringLengthOption = Option(unsafeMethod()).map(_.length)

  //use case for orElse
  val someResult = Option(unsafeMethod()).orElse(Option(fallbackMethod()))

  // DESIGN -- instead of working with nulls, design methods so they return options instead
  def betterUnsafeMethod(): Option[String] = None //still an instance in memory, that signifies the absence of value
  def betterFallbackMethod(): Option[String] = Some("A valid result")
  val betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  //example: Map.get
  val phoneBook = Map(
    "Daniel" -> 1234
  )
  val marysPhoneNumber = phoneBook.get("Mary") //None -- no need to crash, check for nulls or if Mary is present.

  //exercise
  val aConfig: Map[String, String] = Map(
    "host" -> "176.45.32.1",
    "port" -> "8081"
  )

  class Connection {
    def connect(): String = "Connection Successful"
  }

  object Connection {
    val random = new Random()

    def apply(host: String, port:String): Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }







  def main(args: Array[String]): Unit = {
    val host = aConfig("host")
    val port = aConfig("port")
    
  }
}
