package workbook.part3fp

object LinearCollections {

  //Seq = well defined ordering + indexing
  def testSeq(): Unit = {
    val aSequence = Seq(4, 3, 2, 1)

    //main API: index an element
    val thirdElement = aSequence.apply(2) // element 3 (gets the element on index 2)

    val anIncrementedSequence = aSequence.map(_ + 1)
    val flatmapSequence = aSequence.flatMap(x => Seq(x, x + 1))
    val aFilteredSequence = aSequence.filter(_% 2 == 0)

    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5,6,7)
    val sortedSequence = aSequence.sorted // [1,2,3,4]
    val sum = aSequence.foldLeft(0)(_ + _) //10
    val stringRep = aSequence.mkString("[", ",", "]")

    println(aSequence)
    println(reversed)
    println(concatenation)
    println(sortedSequence)
  }

  //lists -- implements the seq trait.
  def testLists(): Unit = {
    val aList = List(1,2,3)
    //same APIs as Seq as well as some other stuff:

    val firstElement = aList.head
    val rest = aList.tail

    //appending and prepending -- adding elements at the start and end of the list.
    val aBiggerList = 0 +: aList :+ 4
    val prepending = 0 :: aList // :: equivalent to Cons in out LList

    //utility methods
    val scalax5 = List.fill(5)("Scala")
  }

  //ranges
  def testRanges(): Unit = {
    val aRange = 1 to 10
    val aNonInclusiveRange = 1 until 10 //10 not included
    //same APIs as Seq
    (1 to 10).foreach(_ => println("Scala"))
  }

  //arrays
  def testArrays(): Unit = {
    val anArray = Array(1,2,3,4,5,6)
    //arrays are NOT SEQs, but they have most Seq APIs.
    val aSequence: Seq[Int] = anArray.toIndexedSeq //converting array to seq.
    // arrays are mutable.
    anArray.update(2, 30) // no new array is allocated- changing the existing one.
  }

  //vectors -- fast Seqs for a large amount of data.
  def testVectors(): Unit = {
    val aVector: Vector[Int] = Vector(1,2,3,4,5,6)
    //same API as Seq.
  }

  //sets -- no duplicates allowed
  def testSets(): Unit = {
    val aSet = Set(1,2,3,4,5,4) // no ordering guaranteed (some specific implementations of set has ordering)
    //equals + hash code = hash set
    // main API: test if an element is in the set
    val contains3 = aSet.contains(3) //true
    val contains3_v2 = aSet(3) //same as above
    // adding/removing
    val aBiggerSet = aSet + 4
    val aSmallerSet = aSet - 4
    // concatination == union
    val anotherSet = Set(4,5,6,7,8)
    val muchBiggerSet = aSet.union(anotherSet)
    val muchBiggerSet_v2 = aSet ++ anotherSet //same
    val muchBiggerSet_v3 = aSet | anotherSet //same
    //difference
    val aDiffSet = aSet.diff(anotherSet) // 1, 2, 3 (the numbers not in anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet //same
    //intersection
    val anIntersection = aSet.intersect(anotherSet) // 4, 5
    val anIntersection_v2 = aSet && anotherSet // 4, 5
  }

  def main(args: Array[String]): Unit = {
    testSets()
  }
}
