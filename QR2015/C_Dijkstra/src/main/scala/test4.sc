case class Node(id: Int, distFromLeaf: Int, longestPath: Int)

def treeDiameter(n: Int, tree: Array[Array[Int]]): Int = {

  def preProc(tree: Array[Array[Int]]): Map[Int, Set[Int]] = {
    val tr = tree.toList.map(_.toList)

    val vInv = tr.map(arr => List(arr(1), arr.head))
    val v = tr ++ vInv
    v.groupBy(_.head).map {
      case (key, nodes) =>
        (key, nodes.flatten.toSet diff Set(key))
    }
  }

  def treeDiam(tree: Array[Array[Int]]): Int = {

    def calcLine(nodeId: Int,e: (Node, Int)): (Node, Int) = {
      val (node, maxCandidate) = e
      val distFromLeaf = node.distFromLeaf + 1
      val maxPath = distFromLeaf

      (Node(nodeId, distFromLeaf, math.max(maxPath, maxCandidate)), math.max(maxPath, maxCandidate))
    }

    def calcFork(nodeId: Int,calculated: Set[(Node, Int)]): (Node, Int) = {
      val c = calculated.toList
      val directDescNodes = c.map(_._1)
      val maxCandidate = c.map(_._2).max

      val distFromLeaf = directDescNodes.map(_.distFromLeaf).max + 1

      val maxPath = directDescNodes.grouped(2)
        .map(p => p.map(_.distFromLeaf).sum)
        .max + 2

      (Node(nodeId, distFromLeaf, math.max(maxPath, maxCandidate)), math.max(maxPath, maxCandidate))
    }

    def calculateNode(nodeId: Int, calculated: Set[(Node, Int)]): (Node, Int) = {

      val curNode =
        if(calculated.size == 1)  calcLine(nodeId, calculated.head)
        else calcFork(nodeId, calculated)

      println(curNode._1)
      println(curNode._2)
      //      println(s"Calculationg node: ${curNode.id} and desc size is: ${directDescNodes.size}")
      curNode
    }

    def traverseTree(comingFrom: Int, node: (Int, Set[Int]), vgr: Map[Int, Set[Int]]): (Node, Int) = {
      val (nodeId, touchPoints) = node
      val directDesc = touchPoints diff Set(comingFrom)

      if (directDesc.isEmpty) (Node(nodeId, 0, 0), 0)
      else {
        val calculated =
          directDesc.map(
            e =>
              traverseTree(nodeId, (e, vgr(e)), vgr)
          )
        calculateNode(nodeId, calculated)
      }
    }

    val vgr = preProc(tree)
    //    println(s"Start from node: ${vgr.head._1}")
    val (node, maxPath) = traverseTree(-1, vgr.head, vgr)
    //    println(node)
    //    println(maxPath)
    //    lstNodes.foreach(println)
    maxPath
  }



  if (tree.isEmpty) 0
  else if(tree.head.isEmpty) 0
  else treeDiam(tree)
}


val root = 2

val vert = Array(
  Array(2, 5)
  , Array(5, 1)
  , Array(1, 9)
  , Array(1, 0)
  //      , Array(0, 10)
  , Array(5, 7)
  , Array(7, 6)
  , Array(6, 3)
  , Array(3, 8)
  , Array(8, 4)
)


treeDiameter(1, vert)