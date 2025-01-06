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
      n.flatMap(nElem => c.flatMap(cElem => s.map(sElem => s"\n$nElem$cElem - $sElem")))
  }

  //same as superList- use for-comprehensions for better readability
  val combinations = numbers.flatMap(number => chars.flatMap(char => colors.map(color => s"\n$number$char - $color")))
  val combinationsEven = numbers.filter(_ % 2 == 0).flatMap(number => chars.flatMap(char => colors.map(color => s"\n$number$char - $color")))

  //for-comprehensions-- expressions NOT iteration
  val combinationsFor = for {
    number <- numbers if number % 2 == 0 //does same thing as combinationsEven.
    char <- chars
    color <- colors
  } yield s"$number$char - $color"
    // for every number in numbers, every char in chars and every color in colors yield(return) interpolated string
    // and return them all under the same collection.
    // every line works as a flatMap, except the last which is a map.




  def main(args: Array[String]): Unit = {
//    print(combinations)
//    print(combinationsFor)

    // for comp with side effects(returning unit)
    numbers.foreach(println)
    for {
      num <- numbers
    } println(num)
  }

}
