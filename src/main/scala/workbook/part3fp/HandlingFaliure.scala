package workbook.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFaliure {

  val aTry: Try[Int] = Try(42)
  val aFailedTry: Try[Int] = Try(throw new RuntimeException)
  //will not crash app because Try's arg is call by name and will not be evaluated before it's used.

  //alt construction- not recommended
  val aTry_v2: Try[Int] = Success(42)
  val aFailedTry_v2: Try[Int] = Failure(new RuntimeException) //holds an instance of exception, but will not throw it.

  //main API
  val checkSuccess = aTry.isSuccess
  val checkFailure = aTry.isFailure
  val aChain = aFailedTry.orElse(aTry)

  //map, flatMap, filter, for comp
  val anIncrementedTry = aTry.map(_ + 1)
  val aFlatMappedTry = aTry.flatMap(mol => Try(s"My meaning of life is $mol"))
  val aFilteredTry = aTry.filter(_ % 2 == 0) //if try is successful, but element doesn't pass filter= NoSuchElementException

  //WHY: avoid unsafe APIs which can throw exceptions
  def unsafeMethod(): String =
    throw new RuntimeException("No string for you!")

  //defensive: try-catch-finally (not recommended)
  val stringLengthDefensive = try {
    val aString = unsafeMethod()
    aString.length
  } catch {
    case e: RuntimeException => -1
  }

  // purely functional
  val stringLengthPure = Try(unsafeMethod()).map(_.length).getOrElse(-1)

  //DESIGN
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("No string for you!"))
  def betterBackupMethod(): Try[String] = Success("Scala")
  val stringLengthPure_v2 = betterUnsafeMethod().map(_.length)
  val aSafeChain = betterUnsafeMethod().orElse(betterBackupMethod()).map(_.length)

  //exercise
  val host = "localhost"
  val port = "8081"
  val myDesiredURL = "rockthejvm.com/home"

  class Connection {
    val random = new Random()

    def get(url: String): String = {
      if(random.nextBoolean()) "<html>Success</html>"
      else throw new RuntimeException("Cannot fetch page right now")
    }
  }

  object HttpService {
    val random = new Random()

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Cannot access host/port combination.")
    }
  }

  //purely functional approach
  val aConnection: Try[Connection] = Try(HttpService.getConnection(host, port))
  val getHTML = aConnection.flatMap(c => Try(c.get(myDesiredURL)))
  val finalResult = getHTML.fold(e => s"<html>${e.getMessage}</html>", s => s)
  //gets the result as text, not a Try by collapsing the values into results for failure and success.

  //exercise with methods returning try.
  class TryConnection {
    val random = new Random()

    def get(url: String): String = {
      if (random.nextBoolean()) "<html>Success</html>"
      else throw new RuntimeException("Cannot fetch page right now")
    }
    def getSafe(url: String): Try[String] =
      Try(get(url))
  }

  object TryHttpService {
    val random = new Random()

    def getConnection(host: String, port: String): TryConnection = {
      if (random.nextBoolean()) new TryConnection
      else throw new RuntimeException("Cannot access host/port combination.")
    }

    def getConnectionSafe(host: String, port: String): Try[TryConnection] = {
      Try(getConnection(host: String, port: String))
    }
  }

  val maybeHtml = for {
    conn <- TryHttpService.getConnectionSafe(host, port)
    html <- conn.getSafe(myDesiredURL)
  } yield html 

  def main(args: Array[String]): Unit = {
    println(finalResult)
    println(maybeHtml)
  }
}
