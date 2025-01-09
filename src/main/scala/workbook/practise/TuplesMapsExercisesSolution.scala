package workbook.practise

import scala.annotation.tailrec

object TuplesMapsExercisesSolution {

    def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] = {
      network + (newPerson -> Set())
    }

    def removePerson(network: Map[String, Set[String]], personToRemove: String): Map[String, Set[String]] = {
      (network - personToRemove).map(pair => (pair._1, pair._2 - personToRemove))
    }

    def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      if(!network.contains(a)) throw new IllegalArgumentException(s"The person $a is not part of the network")
      else if(!network.contains(b)) throw new IllegalArgumentException(s"The person $b is not part of the network")
      else {
        val friendsOfA = network(a)
        val friendsOfB = network(b)

        network + (a -> (friendsOfA + b)) + (b -> (friendsOfB + a))
      }
    }

    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      if(!network.contains(a) || !network.contains(b)) network
      else {
        val friendsOfA = network(a)
        val friendsOfB = network(b)

        network + (a -> (friendsOfA - b)) + (b -> (friendsOfB - a))
      }
    }

    def nFriends(network: Map[String, Set[String]], person: String): Int = {
      if(!network.contains(person)) -1
      else network(person).size
    }

    def mostFriends(network: Map[String, Set[String]]): String = {
      if(network.isEmpty) throw new RuntimeException("Network is empty")
      else {
        val best = network.foldLeft(("", -1))((currentBest, newAssociation) => {
          val currentMostPopularPerson = currentBest._1
          val mostFriendsSoFar = currentBest._2

          val newPerson = newAssociation._1
          val newPersonFriends = newAssociation._2.size

          if(mostFriendsSoFar < newPersonFriends) (newPerson, newPersonFriends)
          else currentBest
        })
        best._1
//        network.maxBy((k, v) => v.size)._1
      }
    }

    def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
      network.count(p => p._2.isEmpty)
    }

    def isConnected(network: Map[String, Set[String]], person1: String, person2: String): Boolean = {
      @tailrec
      def connection(current: String, visited: Set[String]): Boolean = {
        if (current == person2) true
        else if (visited.contains(current)) false
        else network(current).exists(friend => connection(friend, visited + current))
      }
      connection(person1, Set())
    }

    def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
      @tailrec
      def search(discoveredPeople: Set[String], consideredPeople: Set[String]): Boolean = {
        if (discoveredPeople.isEmpty) false
        else {
          val person = discoveredPeople.head
          val personsFriends = network(person)

          if(personsFriends.contains(b)) true
          else search(discoveredPeople - person ++ personsFriends -- consideredPeople, consideredPeople + person)
        }
      }
      if(!network.contains(a) || !network.contains(b)) false
      else search(network(a), Set(a))
    }

    def main(args: Array[String]): Unit = {

      //add person
      val empty: Map[String, Set[String]] = Map()
      val network = addPerson(addPerson(empty, "Bob"), "Mary")
      println(network)

      //add friend relation
      println(friend(network, "Bob", "Mary"))

      //unfriend
      println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))

      //number of friends
      val people = addPerson(addPerson(addPerson(empty, "Bob"), "Mary"), "Jim")
      val simpleNet = friend(friend(people, "Bob", "Mary"), "Jim", "Mary")
      println(simpleNet)
      println(nFriends(simpleNet, "Mary"))
      println(nFriends(simpleNet, "Bob"))
      println(nFriends(simpleNet, "Daniel"))

      //most friends
      println(mostFriends(simpleNet))

      //no friends
      println(nPeopleWithNoFriends(addPerson(simpleNet, "Daniel")))

      //social connection
      println(socialConnection(addPerson(simpleNet, "Daniel"), "Bob", "Daniel"))
    }
  }

