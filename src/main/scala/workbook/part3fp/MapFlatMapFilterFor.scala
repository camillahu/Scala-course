package workbook.part3fp

object MapFlatMapFilterFor {

  // standard list
  val aList = List(1,2,3) // [1] -> [2] -> [3] -> Nil
  val firstElement = aList.head //works in the same way as created in LList
  val restOfElements = aList.tail //works in the same way as created in LList

  //map
  val anIncrementedList = aList.map(_ + 1)

  //filter
  val onlyOddNumbers = aList.filter(_ % 2 !=0)

  //flatMap
  val toPair = (x: Int) => List(x, x+1)
  val aFlatMappedList = aList.flatMap(toPair) // [1,2, 2,3, 3,4]

  //
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  def superList(n: List[Int], c: List[Char], s: List[String]):List[String] = {
    val newList = n.flatMap()
  }


  def main(args: Array[String]): Unit = {

  }

}
