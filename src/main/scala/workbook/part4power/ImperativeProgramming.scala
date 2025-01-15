package workbook.part4power

object ImperativeProgramming {

  val meaningOfLife: Int = 42

  var aVariable = 99
  aVariable = 100 //vars value can be reassigned, but cannot change type
  aVariable +=10

  //loops
  var i = 0
  while (i<10) {
    println(s"Counter at $i")
    i += 1
  }



}
