package workbook.part2oop

object Generics {

  //Generics is used to reuse code on different types.
  //To not lose type safety, we use a type argument. Using A in head and tail ensures that the function will
  //use the data type the cass was instantiated with.
  abstract class MyList[A] { // generic list
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException()
    override def tail: MyList[A] = throw new NoSuchElementException()
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  //Specify type when instantiating.
  val listOfIntegers: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))

  val firstNum = listOfIntegers.head
  val adding = firstNum + 3

  // Multiple generic types
  trait MyMap[Key, Value]

  //Generic methods (companion object) 
  object MyList {
    def from2Elements[A](elem1: A, elem2:A): MyList[A] =
      new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
  }
  
  val first2numbers = MyList.from2Elements[Int](1, 2)
  val first2numbers_v2 = MyList.from2Elements(1, 2) //compiler will automatically infer this type as int.
  val first2numbers_v3 = new NonEmpty(1, new NonEmpty(2, new Empty))

  def main(args: Array[String]): Unit = {
    println(firstNum)
    println(adding)
  }
}
