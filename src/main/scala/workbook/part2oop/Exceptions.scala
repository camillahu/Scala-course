package workbook.part2oop

object Exceptions {
  //instances that can crash application in the  JVM gets thrown as Java exceptions.

  //crashes with a NullPointerException
  val aString: String = null

  // 1- throw exceptions
  // val a WeirdValue: Int = throw new NullPointerException //returns Nothing

  // exception must be of type Throwable:
  //    Error (for example StackOverflow or OutOfMemory)
  //    Exception (for example NullPointer or NoSuchElement)

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  //since Exceptions return Nothing, you have to catch it to handle the errors
  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    //because the cases will be evaluated in order, it's best practise to put the most specific case first.
    case e: NullPointerException => 35  //subtype of RuntimeException
    case e: RuntimeException => 54  //returns 54 if it fails.
      //the compiler infers types in try catch expressions, and will infer the type as the nearest common ancestor.
      // for example if 54 is a string, it will be inferred to Any because Any is the common ancestor of int and string.
  } finally {
    //executed no matter what
    // closing resources so the trycatch block doesn't stay open if it fails.
    // no impact on return type, returns unit.
  }

  //can define custom exception classes that inherits from another exception.
  class MyException extends RuntimeException {
    // fields or methods
    override def getMessage = "my exception"
  }

  val MyException = new MyException

  //Exercises:

  def getString(SOError: Boolean): Int =
    if (SOError) throw new StackOverflowError("Simulated so error!")
    else throw new OutOfMemoryError("Simulated oom error!")

//  val SOError = try {
//    getString(true)
//  } catch {
//    case e: OutOfMemoryError => "OOM"
//    case e: StackOverflowError => "SO"
//
//  } finally {
//    println("finally executed")
//  }
//
//  val OOMError = try {
//    getString(false)
//  } catch {
//    case e: OutOfMemoryError => "OOM"
//    case e: StackOverflowError => "SO"
//
//  } finally {
//    println("finally executed")
//  }

  def soCrash(): Unit = {
    def infinite(): Int = 1 + infinite()
    infinite()
  }

  def oomCrash(): Unit = {
    def bigString(n: Int, acc: String): String =
      if (n == 0) acc
      else bigString(n - 1, acc + acc)

    bigString(7349326, "Scala")
  }


  def main(args: Array[String]): Unit = {
//    println(potentialFail)
//    val throwMyException = throw MyException

    oomCrash()
//    println(SOError)
//    println(OOMError)
  }
}
