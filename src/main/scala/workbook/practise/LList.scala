package workbook.practise
import scala.annotation.tailrec

//singly linked list
abstract class LList[A] {
  def head : A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element:A): LList[A]
}

class Empty[A] extends LList[A] {
  override def head: A = throw new NoSuchElementException("Empty list has no head")
  override def tail: LList[A] = throw new UnsupportedOperationException(("Empty list has no tail"))
  override def isEmpty: Boolean = true
  override def add(element: A): LList[A] = new Cons[A](element, this)
  override def toString = "[]"
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

    s"[${concatenateElements(this.tail, s"$head")}]"
  }
}

object LListTest {

  def main(args: Array[String]): Unit = {
    val empty = new Empty[Int]
    println(empty.isEmpty)
    println(empty)

    val first3numbers = new Cons(1, new Cons(2, new Cons(3, empty)))
    println(first3numbers)
    val first3numbers_v2 = empty.add(1).add(2).add(3)
    println(first3numbers_v2)
    println(first3numbers_v2.isEmpty)

    val someStrings = new Cons("dog", new Cons("cat", new Empty))
    println(someStrings)
  }
}



//class Cons (value:Int, next: LList) extends LList {
//  override def head:Int = value
//  override def tail: LList = next
//  override def isEmpty: Boolean = false
//  override def add(element:Int): LList = new Cons(element, this)
//}

