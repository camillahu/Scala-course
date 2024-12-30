package workbook.practise
import scala.annotation.tailrec
import scala.util.Try

//singly linked list
abstract class LList[A]   {
  def head : A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element:A): LList[A] = Cons(element, this)

  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: A => B): LList[B]
  def filter(predicate: A => Boolean): LList[A]
  def flatMap[B](transformer: A => LList[B]): LList[B]
  def foreach(applyUnit: A => Unit): Unit
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException("Empty list has no head")
  override def tail: LList[A] = throw new UnsupportedOperationException(("Empty list has no tail"))
  override def isEmpty: Boolean = true
  override def toString = "[]"

  override infix def ++(anotherList: LList[A]):LList[A] = anotherList

  override def map[B](transformer: A => B): LList[B] = Empty[B]()
  override def filter(predicate: A => Boolean): LList[A] = this
  override def flatMap[B](transformer: A => LList[B]): LList[B] = Empty[B]()
  override def foreach(applyUnit: A => Unit): Unit = println()
}

case class Cons[A](head:A, tail: LList[A]) extends LList[A] {
  override def isEmpty: Boolean = false
  override def add(element:A): LList[A] = new Cons[A](element, this)

  override def toString = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if(remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc ${remainder.head}")

    s"[${concatenateElements(tail, s"$head")}]"
  }

  override infix def ++(anotherList: LList[A]):LList[A] = {
    Cons(head, tail ++ anotherList)
  }

  override def map[B](transformer: A => B): LList[B] = {
    Cons(transformer(head), tail.map(transformer))
  }

  override def filter(predicate:A => Boolean): LList[A] = {
    if(predicate(head)) Cons(head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def flatMap[B](transformer:A => LList[B]): LList[B] = {
    transformer(head) ++ tail.flatMap(transformer)
  }

  override def foreach(applyTo: A => Unit): Unit = {
    applyTo(head)
    tail.foreach(applyTo)
  }
}

object LList {
  def find[A](list: LList[A], predicate: A => Boolean): A = {
    if(list.isEmpty) throw new NoSuchElementException
    else if (predicate(list.head)) list.head
    else find(list.tail, predicate)
  }
}

object LListTest {

  def main(args: Array[String]): Unit = {

    //NEW TESTS
    val intList: LList[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Empty()))))

    //map test
    val doubler_v2: Int => Int = _ * 2
    println(intList.map(doubler_v2))

    //filter test
    val evenPredicate_v2: Int => Boolean = _ % 2 == 0
    println(intList.filter(evenPredicate_v2))

    //flatmap test
    val doublerList_v2: Int => LList[Int] = x => Cons(x, Cons(x * 2, Empty()))
    println(intList.flatMap(doublerList_v2))

    //foreach test
    val printHeart: Int => Unit = x => print(s"$x <3 ")
    intList.foreach(printHeart)
  }
}







//    // map test
//    val stringInts: LList[String] = Cons("1", Cons("2", Cons("3", Cons("4", Empty()))))
//    val intLList = stringInts.map(StringToIntTransformer)
//    val doubler = new Function1[Int, Int] {
//      override def apply(i: Int) = i * 2
//    }
//
//    //filter test
//    println(intLList.filter(EvenPredicate))
//    val evenPredicate = new Function1[Int, Boolean] {
//      override def apply(i:Int) = i % 2 == 0
//    }
//
//
//    //flatMap test
//    val nameList: LList[String] = Cons("Camilla.", Cons("Paal.", Cons("Line.", Empty())))
//    println(intLList.flatMap(IntToListTransformer))
//    println(nameList.flatMap(Hello))
//    val doublerList = new Function1[Int, LList[Int]] {
//    override def apply(i: Int) = Cons(i, Cons(i * 2, Empty()))
//
//    //find test
//    println(LList.find(intLList, EvenPredicate))
//    println(LList.find(intLList, IsNumber10))


//(replaced with function types)
//trait Predicate[A] {
//  def test(element: A): Boolean
//}
//
//case object EvenPredicate extends Predicate[Int] {
//  override def test(i: Int): Boolean =
//    i % 2 == 0
//}
//
//trait Transformer[A, B] {
//  def transform(value:A): B
//}
//
//case object StringToIntTransformer extends Transformer[String, Int] {
//  override def transform(s: String): Int =
//    s.toInt
//}
//
//val stringToIntTransformer = new Function1[String, Int] {
//  override def apply(s: String): Int =
//    s.toInt
//}
//
//case object IntToListTransformer extends Transformer[Int, LList[Int]] {
//  override def transform(a: Int): LList[Int] =
//    Cons(a, Cons(a + 1, Empty()))
//}
//
//case object Hello extends Transformer[String, LList[String]] {
//  override def transform(s: String): LList[String] =
//    Cons(s, Cons("hei, " + s, Empty()))
//}
//
//case object IsNumber2 extends Predicate[Int] {
//  override def test(i: Int): Boolean =
//    i == 2
//}
//
//case object IsNumber10 extends Predicate[Int] {
//  override def test(i: Int): Boolean =
//    i == 10
//}


