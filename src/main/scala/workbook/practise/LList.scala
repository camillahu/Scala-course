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

  def map[B](transformer: Transformer[A,B]): LList[B]
  def filter(predicate: Predicate[A]): LList[A]
  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException("Empty list has no head")
  override def tail: LList[A] = throw new UnsupportedOperationException(("Empty list has no tail"))
  override def isEmpty: Boolean = true
  override def toString = "[]"

  override infix def ++(anotherList: LList[A]):LList[A] = anotherList

  override def map[B](transformer: Transformer[A, B]): LList[B] = Empty[B]()
  override def filter(predicate: Predicate[A]): LList[A] = this
  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = Empty[B]()
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

  override def map[B](transformer: Transformer[A, B]): LList[B] = {
    Cons(transformer.transform(head), tail.map(transformer))
  }

  override def filter(predicate: Predicate[A]): LList[A] = {
    if(predicate.test(head)) Cons(head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = {
    transformer.transform(head) ++ tail.flatMap(transformer)
  }

//  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = {
//    def concat(list1: LList[B], list2:LList[B]):LList[B] = {
//      if(list1.isEmpty) list2
//      else new Cons(list1.head, concat(list1.tail, list2))
//    }
//    val transformedHead = transformer.transform(head)
//    concat(transformedHead, tail.flatMap(transformer))
//  }
}



trait Predicate[T] {
  def test(element: T): Boolean
}

case object EvenPredicate extends Predicate[Int] {
  override def test(i: Int): Boolean =
    i % 2 == 0
}

trait Transformer[A, B] {
  def transform(value:A): B
}

case object IntToListTransformer extends Transformer[Int, LList[Int]] {
  override def transform(a: Int): LList[Int] =
    Cons(a, Cons(a + 1, Empty()))
}

case object Hello extends Transformer[String, LList[String]] {
  override def transform(s: String): LList[String] =
    Cons(s, Cons("hei, " + s, Empty()))
}

case object StringToIntTransformer extends Transformer[String, Int] {
  override def transform(s: String): Int =
    s.toInt
}

object LListTest {

  def main(args: Array[String]): Unit = {

    val stringInts: LList[String] = Cons("1", Cons("2", Cons("3", Empty())))
    val intLList = stringInts.map(StringToIntTransformer)
    val nameList: LList[String] = Cons("Camilla.", Cons("Paal.", Cons("Line.", Empty())))

    println(intLList.filter(EvenPredicate))
    println(intLList.flatMap(IntToListTransformer))
    println(nameList.flatMap(Hello))

  }
}

//    val empty = new Empty[Int]
//    println(empty.isEmpty)
//    println(empty)
//
//    val first3numbers = new Cons(1, new Cons(2, new Cons(3, empty)))
//    println(first3numbers)
//    val first3numbers_v2 = empty.add(1).add(2).add(3)
//    println(first3numbers_v2)
//    println(first3numbers_v2.isEmpty)
//
//    val someStrings = new Cons("dog", new Cons("cat", new Empty))
//    println(someStrings)


