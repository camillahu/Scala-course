package workbook.practise

object TuplesMapsExercises {

  val aNetwork: Map[String, Set[String]] = Map(
    "Camilla" -> Set("Camilla", "Leo"),
    "Paal" -> Set("Camilla", "Stian"),
    "Stian" -> Set("Paal"),
    "Leo" -> Set("Camilla", "Cath", "Thekla"),
    "Cath" -> Set("Leo", "Camilla"),
    "Thekla" -> Set("Leo"),
    "Soli" -> Set(),
    "Guro" -> Set()
  )

  def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] = {
    val newPair: (String, Set[String]) = newPerson -> Set()
    network + newPair
  }

  def removePerson(network: Map[String, Set[String]], personToRemove: String): Map[String, Set[String]] = {
    if (network.contains(personToRemove)) network - personToRemove
    else throw new IllegalArgumentException(s"Person '$personToRemove' not found in requested network")
  }

  def addFriendRelationship(network: Map[String, Set[String]], person: String, friend: String): Map[String, Set[String]] = {
    val tuples = network.view.filterKeys(key => key == person || key == friend).toMap
    val newTuples: Map[String, Set[String]] =
      tuples.map(p =>
        (p._1, p._2 + {if (p._1 == person) friend else person}))
    network ++ newTuples
  }

  def removeFriendRelationship(network: Map[String, Set[String]], person: String, friend: String): Map[String, Set[String]] = {
    val tuples = network.view.filterKeys(key => key == person || key == friend).toMap
    val newTuples: Map[String, Set[String]] =
      tuples.map(p =>
        (p._1, p._2 - {if (p._1 == person) friend else person
      }))

    network ++ newTuples
  }

  def numOfFriends(network: Map[String, Set[String]], person: String): Int = {
    network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy((k,v) => v.size)._1
  }

  def noFriends(network: Map[String, Set[String]]): Int = {
    network.count(p => p._2.isEmpty)
  }

  def isConnected(network: Map[String, Set[String]]): Boolean = ???

  def main(args: Array[String]): Unit = {

//    //add person test
//    val addPersonToNetwork: Map[String, Set[String]] = addPerson(aNetwork, "Line")
//    println(addPersonToNetwork)
//
//    //remove person test
//    val removePersonFromNetwork: Map[String, Set[String]] = removePerson(aNetwork, "Leo")
//    println(removePersonFromNetwork)
//
//    //add friends test
//    val addFriends: Map[String, Set[String]] = addFriendRelationship(addPersonToNetwork, "Camilla", "Line")
//    println(addFriends)
//
//    //remove friends test
//    val removeFriends: Map[String, Set[String]] = removeFriendRelationship(aNetwork, "Camilla", "Paal")
//    println(removeFriends)

    //num of friends test
    val camillaNumOfFriends: Int = numOfFriends(aNetwork, "Camilla")
    println(s"Camilla has $camillaNumOfFriends friends.")

    //most friends test
    val whoMostFriends: String = mostFriends(aNetwork)
    println(s"$whoMostFriends has most friends")

    //how many have no friends
    val hasNoFriends: Int = noFriends(aNetwork)
    println(s"$hasNoFriends people have no friends")

    //are they connected through friends?
    val hasConnection = ???
  }
}
