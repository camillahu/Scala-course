package workbook.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  @tailrec
  def sumUntilTailrec(x: Int, accumulator: Int = 0): Int =
    if (x <= 0) accumulator
    else sumUntilTailrec(x - 1, accumulator + x)

  val sumUntil100 = sumUntilTailrec(100)

  def savePicture(dirPath: String, name: String, format: String = "jpg", width: Int = 1920, height: Int = 1080):Unit = {
    println("Saving picture in format " + format + " in path " + dirPath)
  }

  def main(args: Array[String]): Unit = {
    //default args are injected
    savePicture("/users/Camilla", "myPhoto")
    // pass explicit different values for default args
    savePicture("/users/Camilla", "myPhoto", "png")
    // pass values after the default argument
    savePicture("/users/Camilla", "myPhoto", width=800, height=600)
    // when naming args, the order doesn't matter. 
    savePicture("/users/Camilla", "myPhoto", height=600, width=800)
  }
}
