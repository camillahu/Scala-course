package workbook.part2oop

import java.security.Permissions

object Enums {

  enum Permissions {
    //sealed data type- the only instances of Permission are the cases.
    case READ, WRITE, EXECUTE, NONE

    //add fields/methods
    def openDocument(): Unit =
    if(this == READ) println("opening document...")
    else println("reading not allowed")
  }
  val somePermissions: Permissions = Permissions.READ


  //constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) //100
    case WRITE extends PermissionsWithBits(2) //010
    case EXECUTE extends PermissionsWithBits(1) //001
    case NONE extends PermissionsWithBits(0) //000
  }

  //can have companion objects
  object PermissionsWithBits {
    // can be used for factory methods(methods that return one possible instance)
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  //standard API
  val somePermissionsOrdinal = somePermissions.ordinal // (ordinal returns the index where this particular instance occurs)
  val allPermissions = PermissionsWithBits.values // returns array of all possible values of the enum.
  val readPermission: Permissions = Permissions.valueOf("READ") // Can get the enum from a string-- Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal) //0-- index of READ
    println(allPermissions.mkString(", "))
    println(readPermission)
  }
}

