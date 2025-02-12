package workbook.part4power

import scala.util.Random

object PatternMatching {

  val random = new Random()
  val aValue = random.nextInt(100)

  val description = aValue match {
    case 1 => "the first"
    case 2 => "the second"
    case 3 => "the third"
    case _ => s"something else: $aValue" //will throw match error if we don't have default case.
  }

  //decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  //must be a CASE class to work with pattern matching like this.
  val greeting = bob match {
    case Person(n, a) if a < 18 => s"Hi, my name is $n and I'm $a years old."
    case Person(n, a) => s"Hello there, my name is $n and I'm not allowed to say my age."
    case _ => "I don't know who I am."
  }

  //Patterns are matched in order: put most specific case first.
  //What if no cases match? -- match error.
  //What is the type returned? -- lowest common ancestor of all types on each branch.

  //PM on sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Cat(meowStyle: String) extends Animal

  val anAnimal: Animal = Dog("Terra Nova")

  val animalPM = anAnimal match {
    case Dog(someBreed) => "I've detected a dog"
    case Cat(aMeow) => "I've detected a cat"
  }

  //exercise
  sealed trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String =
    expr match {
      case Number(n) => s"$n"
      case Sum(left, right) => show(left) + " + " + show(right)
      case Prod(left, right) => {
        def maybeShowParentheses(exp: Expr): String = exp match {
          case Prod(_, _) => show(exp)
          case Number(_) => show(exp)
          case Sum(_, _) => s"(${show(exp)})"
        }

        maybeShowParentheses(left) + " * " + maybeShowParentheses(right)
      }
    }


  def main(args: Array[String]): Unit = {
    println(description)
    println(greeting)
    println(show(Sum(Number(2), Number(3))))
    println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
    println(show(Prod(Sum(Number(2), Number(3)), Number(4))))
    println(show(Sum(Prod(Number(2), Number(3)), Number(4))))
  }
}
