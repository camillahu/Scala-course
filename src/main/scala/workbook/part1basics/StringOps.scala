package workbook.part1basics

object StringOps {

  val aString: String = "Hello, I am learning Scala"

  // string functions
  val secondChar = aString.charAt(1) //H
  val firstWords =  aString.substring(0, 5) //Hello
  val words = aString.split(" ") //Array- will seperate strings where there is a space.
  val startsWithHello = aString.startsWith("Hello") //true
  val allDashes = aString.replace(' ', '-') //Hello,-I-am-learning-Scala
  val allUppercase = aString.toUpperCase() // also toLowerCase()
  val nChars = aString.length

  //other functions

  val reversed = aString.reverse
  val aBunchOfChars = aString.take(10)

  //parse to numeric
  val numberAsString = "2"
  val number = numberAsString.toInt

  //interpolation
  val name = "Alice"
  val age = 12
  val greeting = "Hello, I'm " + name + " and I am " + age + " years old."
  val greeting_v2 = s"Hello I'm $name and I'm $age years old."
  //calculations are being made inside the string.
  val greeting_v3 = s"Hello I'm $name and I will be turning ${age + 1} years old."

  //f-interpolation- more powerful than s-interpolation. You can define the format inside the string
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.5f burgers per minute."

  //raw-interpolation- all characters are interoperated as string.
  val escapes = "this is a \n newline"
  val escapesRaw = raw"this is a \n newline"

  def main(args: Array[String]): Unit = {
    println(escapes)
    println(escapesRaw)
  }
}
