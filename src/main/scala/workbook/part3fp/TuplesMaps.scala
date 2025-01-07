package workbook.part3fp

object TuplesMaps {

  //tuples = finite ordered "lists"
  //group of values considered one value

  val aTuple = (2, "rock the jvm") // Tuple2[Int, String] == (Int, String)
  val firstField = aTuple._1 // gets the first field of the tuple- 2 in this case.
  val aCopiedTuple = aTuple.copy(_1 = 54) //makes a copy where field 1 is changed to54, the rest is constant.

  //tuples of two elements
  val aTuple_v2 = 2-> "rock the jvm" // IDENTICAL to (2, "rock the jvm")

  // maps: keys -> values
  val aMap = Map()

  val phonebook: Map[String, Int] = Map(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  ).withDefaultValue(-1) //returning this if value called isn't present instead of crashing the application

  //core APIs
  val phonebookHasDaniel = phonebook.contains("Daniel") //checking if Danielk exists, but doesn't obtain the value
  val marysPhoneNumber = phonebook("Mary") //crash with an exception if the key is not present in the Map.
  val maryGetOOrElse = phonebook.getOrElse("Jane", "Person not found")

  //add a pair
  val newPair = "Mary" -> 678
  val newPhoneBook = phonebook + newPair // new map

  //remove a key
  val phoneBookWithoutDaniel = phonebook - "Daniel" // new map

  //turning tuples/maps into linear collections
  // list -> map

  val linearPhonebook = List(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123,
    "Jim" -> 222,
  ) //if the same name appears twice, the last value connected to it will be the only one in when converting to Map.

  val phonebook_v2 = linearPhonebook.toMap // will only work with lists of Tuple2.

  //map -> linear collection
  val linearPhonebook_v2 = phonebook.toList // toSeq, toVector, toArray, toSet

  //map, flatMap, filter -- can be used

  val aProcessedPhonebook = phonebook.map(pair => (pair._1.toUpperCase(), pair._2))
  //it's not a good idea to change keys in a map like this because it may remove another key with the same name as what
  //you changed the existing key to. map and flatMap can be useful if you're careful, however.

  //filtering keys
  val noJsView = phonebook.view.filterKeys(!_.startsWith("J")) //viewing the filtered Map, not changing it
  val noJsMap = phonebook.view.filterKeys(!_.startsWith("J")).toMap //making a new Map with the selected view.

  //other collections can create Maps.
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  val nameGroupings = names.groupBy(name => name.charAt(0))
  //makes a map with char at index 0 and groups names together depending on their first letter.
  //HashMap(J -> List(James, Jim), A -> List(Angela), M -> List(Mary), B -> List(Bob), D -> List(Daniel))



  def main(args: Array[String]): Unit = {
//    println(phonebook)
//    println(phonebookHasDaniel)
//    println(marysPhoneNumber)
//    println(maryGetOOrElse)

  println(maryGetOOrElse)
  }
}
