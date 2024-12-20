package workbook.part2oop

//can have top-level fields and methods in Scala 3, but it not recommended as they are "global".
val meaningOfLife = 42
def computeMyLife: String = "Scala"

object PackagesImports { //top-level
  //packages are "folders"

  val aList: workbook.practise.LList[Int] = ???  //throws NotImplementedError

  //import
  import workbook.practise.LList
  val anotherList: LList[Int] = ??? //import package to be able to use only LList name.

  //importing under alias
  import java.util.List as JList
  val aJavaList: JList[Int] = ???

  //importing everything
//  import workbook.practise.*
//  val aPredicate: Predicate[Int] = ???

  //import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  //import everything but something
  object PlayingPhysics {
    import PhysicsConstants.{PLACK as _, *}
    // val plank = PLANK -- will not work
  }

  //default imports:
  //  scala.*, scala.Predef.*, java.lang.*

  //exports 
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelength:Double): Double =
      PLACK / wavelength
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    export physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
  }

  def main(args: Array[String]): Unit = {

  }
}

object PhysicsConstants {
  val SPEED_OF_LIGHT = 299792458
  val PLACK = 6.62e-34
  val EARTH_GRAVITY = 9.8
}
