package workbook.part2oop

object OOExercise1 {

  class Novel(val title:String, val releaseYear: Int, val author:Writer) {

    def authorAge:Int = releaseYear - author.yearOfBirth

    def isWrittenBy(author:Writer): Boolean = this.author == author

    def newEdition(releaseYear: Int): Novel =
      Novel(title= this.title, releaseYear = releaseYear, author = this.author)
  }

  class Writer(val firstName:String, val lastName:String, val yearOfBirth:Int) {

    def fullName:String = s"$firstName $lastName"
  }

  val sjm = Writer("Sarah J.", "Maas", 1986)
  val sjmImpostor = Writer("Sarah J.", "Maas", 1983)
  val acotar = Novel("A Court of Thorns and Roses", 2015, sjm)
  val acotarCollectorsEdition = acotar.newEdition(2019)

  def main(args: Array[String]): Unit = {
    println(s"Title: ${acotar.title} \nRelease year: ${acotar.releaseYear} " +
      s"\nAuthor: ${if (acotar.isWrittenBy(sjmImpostor)) acotar.author.fullName else "invalid author credentials"} " +
      s"\nAuthors age when published: ${acotar.authorAge}" +
      s"\n${acotarCollectorsEdition.title} -Collectors edition release year: ${acotarCollectorsEdition.releaseYear}")
  }
}
