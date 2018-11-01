case class Node(id: Int, distFromLeaf: Int, longestPath: Int)

def treeDiameter(n: Int, tree: Array[Array[Int]]): Int = {

  def preProc(tree: Array[Array[Int]]): Map[Int, List[Int]] = {
    val tr = tree.toList.map(_.toList)

    val vInv = tr.map(arr => List(arr(1), arr.head))
    val v = tr ++ vInv
    v.groupBy(_.head).map {
      case (key, nodes) =>
        (key, (nodes.flatten.toSet diff Set(key)).toList)
    }
  }

  def treeDiam(tree: Array[Array[Int]]): Int = {

    def calculateNode(nodeId: Int, calculated: List[(Node, Int)]): (Node, Int) = {
      val directDescNodes = calculated.map(_._1)
      val maxCandidate = calculated.map(_._2).max

      val distFromLeaf = directDescNodes.map(_.distFromLeaf).max + 1

      val maxPath =
        if (directDescNodes.size == 1) distFromLeaf
        else directDescNodes.grouped(2)
          .map(p => p.map(_.distFromLeaf).sum)
          .max + 2


        (
        Node(nodeId, distFromLeaf, math.max(maxPath, maxCandidate))
        , math.max(maxPath, maxCandidate)
      )
    }

    def traverseTree(comingFrom: Int, node: (Int, List[Int]), vgr: Map[Int, List[Int]]): (Node, Int) = {
      val (nodeId, touchPoints) = node
      val directDesc = touchPoints diff List(comingFrom)

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
    val (node, maxPath) = traverseTree(-1, vgr.head, vgr)
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