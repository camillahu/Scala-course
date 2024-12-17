package workbook.practise
import scala.annotation.tailrec
import scala.util.Try

//singly linked list
abstract class LList[A]   {
  def head : A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element:A): LList[A]

  def map[B](transformer: Transformer[A,B]): LList[B]
  def filter(predicate: Predicate[A]): LList[A]
  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]
}

class Empty[A] extends LList[A] {
  override def head: A = throw new NoSuchElementException("Empty list has no head")
  override def tail: LList[A] = throw new UnsupportedOperationException(("Empty list has no tail"))
  override def isEmpty: Boolean = true
  override def add(element: A): LList[A] = new Cons[A](element, this)
  override def toString = "[]"

  override def map[B](transformer: Transformer[A, B]): Empty[B] = Empty[B]
  override def filter(predicate: Predicate[A]): Empty[A] = Empty[A]
  override def flatMap[B](transformer: Transformer[A, LList[B]]): Empty[B] = Empty[B]
}

//make head and tail fields, so you can override them in the parentheses.
class Cons[A] (override val head:A, override val tail: LList[A]) extends LList[A] {
  override def isEmpty: Boolean = false
  override def add(element:A): LList[A] = new Cons[A](element, this)

  override def toString = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if(remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc ${remainder.head}")

    s"[${concatenateElements(tail, s"$head")}]"
  }

  override def map[B](transformer: Transformer[A, B]): LList[B] = {
    Cons[B](transformer.transform(head), tail.map(transformer))
  }


  override def filter(predicate: Predicate[A]): LList[A] = {
    if(predicate.test(head)) Cons[A](head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = {
    val firstList: LList[B] = transformer.transform(head)
    Cons[B](firstList.head, firstList.tail)
    
    tail.flatMap(transformer))
  }
}

class IntToListTransformer extends Transformer[Int, LList[Int]] {
  override def transform(a: Int): LList[Int] =
    new Cons(a, new Cons(a + 1, new Empty[Int]))

}

class EvenPredicate extends Predicate[Int] {
  override def test(i: Int): Boolean =
    i % 2 == 0
}

class StringToIntTransformer extends Transformer[String, Int] {
  override def transform(s:String): Int =
    s.toInt
}


trait Predicate[T] {
  def test(t: T): Boolean
}

trait Transformer[A, B] {
  def transform(a:A): B
}

object LListTest {

  def main(args: Array[String]): Unit = {

    val empty = new Empty[String]
    val stringInts: LList[String] = new Cons("1", new Cons("2", new Cons("3", empty)))
    val intLList = stringInts.map(new StringToIntTransformer)

    println(intLList.filter(new EvenPredicate))
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


