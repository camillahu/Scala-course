package workbook.part2oop

object OOExercise2 {

  class Counter(val n: Int) {
    def increment:Counter = new Counter(n + 1)
    def increment(numToAdd:Int):Counter = new Counter(n + numToAdd)
    def decrement:Counter = new Counter(n - 1)
    def decrement(numToAdd:Int):Counter = new Counter(n - numToAdd)
    def print(counterInstance:Counter):Unit = println(counterInstance.n)
  }

  class Counter_v2(val count: Int) {
    def increment(): Counter_v2 =
      new Counter_v2(count + 1)

    def decrement(): Counter_v2 =
      if (count == 0) this
      else new Counter_v2(count - 1)

    def increment(n: Int): Counter_v2 =
      if(n <= 0) this
      else increment().increment(n-1)

    def decrement(n: Int): Counter_v2 =
      if (n <= 0) this
      else decrement().decrement(n - 1)

    def print(counterInstance: Counter_v2): Unit = println(counterInstance.count)
  }

//  def count(from:Int, to:Int):Int = {
//    val counterInstance = new Counter(from)
//    def tailRecCounter(): Int =
//
//  }


  def main(args: Array[String]): Unit = {
    val counter1 = new Counter(1)
    counter1.print(counter1.increment)
    counter1.print(counter1.increment(23))
    counter1.print(counter1.decrement)
    counter1.print(counter1.decrement(23))
  }
}
