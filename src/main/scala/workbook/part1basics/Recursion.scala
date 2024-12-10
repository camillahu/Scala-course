package workbook.part1basics

import scala.annotation.tailrec

object Recursion {


  def sumUntil(n: Int): Int = {
    if (n<= 0) 0
    else n + sumUntil(n-1)  //here the addition is last in it's code path, which makes the code crash with big numbers.
  }

  def  sumUntil_v2(n:Int): Int = {
    @tailrec
    def sumUntilTailrec(x: Int, accumulator: Int): Int =
      if(x <=0) accumulator
      else sumUntilTailrec(x-1, accumulator+x)
    //tail recursion- recursive calls occur last in its code path.
      // no further stack frames necessary = no more risk of Stack Overflow.
    sumUntilTailrec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b)


  def sumNumbersBetween_v2(a: Int, b: Int): Int = {
    @tailrec
    def sumTailrec(currentNumber: Int, accumulator: Int): Int = {
      if(currentNumber > b) accumulator
      else sumTailrec(currentNumber + 1, currentNumber + accumulator)
    }
    sumTailrec(a, 0)
  }

  def stringConcat(s: String, n: Int): String = {
    @tailrec
    def stringTailrec(currentNum: Int, acc: String): String = {
      if (currentNum <= 0) acc
      else stringTailrec(currentNum - 1, s + acc)
    }

    stringTailrec(n, "")
  }

  def fibonacciFunc(n:Int): Int = {
    def fibTailrec(i: Int, last: Int, prev: Int):Int = {
      if (i >= n) last
      else fibTailrec(i + 1, last + prev, last)
    }

    if( n<= 2) 1
    else fibTailrec(2, 1, 1)
  }

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    println(stringConcat("Hei", 5))
    println(fibonacciFunc(7))
  }
}
