val n = 8
val in  = Array("8 1", "5 8", "7 3", "8 6", "3 5")


type fusedPipes = List[Set[Int]]

def minimalCost(n: Int, pairs: Array[String]): Int = {
  // Parse input
  def parse(lst: List[String]): fusedPipes = {
    val a = lst.flatMap(e => e.split(" ")).map(_.toInt)
    a.grouped(2).map(e => e.toSet).toList
  }


  //************** Accumulate connected pipes in sets ******************

  def calculateNewAcc(head: Set[Int], acc: fusedPipes): fusedPipes = {
    val (existing, rest) = acc.partition(set => set.intersect(head).nonEmpty)

    if (existing.isEmpty) head :: rest
    else (existing.reduce(_++_) ++ head) :: rest

  }

  def processAcc(head: Set[Int], acc: fusedPipes): fusedPipes = {
    if (acc.isEmpty) List(head)
    else calculateNewAcc(head, acc)
  }

  def linkedPipeSets(lstPairs: fusedPipes, acc: fusedPipes): fusedPipes = {
    if (lstPairs.isEmpty) acc
    else {
      val newAcc = processAcc(lstPairs.head, acc)

      linkedPipeSets(lstPairs.tail, newAcc)
    }
  }

  //************** Add single pipes ******************

  def addSinglePipes(n: Int, acc: fusedPipes): fusedPipes = {
    val allFusedPipes = acc.foldLeft(Set[Int]())( (acc, elem) => acc ++ elem)
    val fullSet = (1 to n).toSet
    val diff = fullSet.diff(allFusedPipes).map(Set(_))

    acc ++ diff
  }

  val sets = linkedPipeSets(parse(pairs.toList), List())

  val withSingle = addSinglePipes(n, sets)

  val costs = withSingle
    .map(_.size)
    .map(a => math.ceil(math.sqrt(a)))

  costs.sum.toInt
}

minimalCost(n, in)
