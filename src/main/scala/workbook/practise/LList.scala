package workbook.practise

//singly linked list
abstract class LList {
  def head : Int
  def tail: LList
  def isEmpty: Boolean
  def add(element:Int): LList
  override def toString: String = {
    if(isEmpty) "Empty"
    else s"$head -> ${tail.toString}"
  }
}

class Empty extends LList {
  override def head: Int = throw new NoSuchElementException("Empty list has no head")
  override def tail: LList = throw new UnsupportedOperationException(("Empty list has no tail"))
  override def isEmpty: Boolean = true
  override def add(element: Int) = new Cons(element, this)
}

class Cons (value:Int, next: LList) extends LList {
  override def head:Int = value
  override def tail: LList = next
  override def isEmpty: Boolean = false
  override def add(element:Int): LList = new Cons(element, this)
}

object LListTest {

  def main(args: Array[String]): Unit = {
    val list = Empty().add(1).add(2).add(3)
    println(list)
  }
}

