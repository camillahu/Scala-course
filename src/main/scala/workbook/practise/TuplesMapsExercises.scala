package workbook.practise

object TuplesMapsExercises {

  val aNetwork: Map[String, Set[String]] = Map(
    "Camilla" -> Set("Paal"),
    "Paal" -> Set("Camilla")
  )

  def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] = {
    val newPair:(String, Set[String]) = newPerson -> Set()
    network + newPair
  }

  def removePerson(network: Map[String, Set[String]], personToRemove: String): Map[String, Set[String]] = {
    if(network.contains(personToRemove)) network - personToRemove
    else throw new IllegalArgumentException(s"Person '$personToRemove' not found in requested network")
  }

  def addFriendRelationship(network: Map[String, Set[String]], person:String, friend: String): Map[String, Set[String]] = {
    val tuples = network.view.filterKeys(key => key == person || key == friend).toList
    val newTuples:Map[String, Set[String]] =
      tuples.map(p => p.copy(p._1, p._2 + {if(p._1 == person)friend else person})).toMap

    network ++ newTuples
  }


  def main(args: Array[String]): Unit = {

  }
}
