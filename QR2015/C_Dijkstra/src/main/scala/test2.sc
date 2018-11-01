case class Node(id: Int, distFromLeaf: Int, longestPath: Int)

def treeDiameter(n: Int, tree: Array[Array[Int]]): Int = {

  def treeDiam(tree: Array[Array[Int]]): Int = {
    val tr = tree.toList.map(_.toList)

    val vInv = tr.map(arr => List(arr(1), arr.head))
    val v = tr ++ vInv
    val vgr = v.groupBy(_.head).map { case (key, nodes) => (key, nodes.flatten.toSet diff Set(key)) }


    def calculateNode(nodeId: Int, calculated: Set[(Node, List[Node])]): (Node, List[Node]) = {
      val directDescNodes = calculated.map(_._1)
      val inDeptNodes = calculated.flatMap(_._2).toList

      val distFromLeaf = directDescNodes.map(_.distFromLeaf).max + 1

      val maxPath =
        if (directDescNodes.size == 1) distFromLeaf
        else directDescNodes.grouped(2)
          .map(p => p.map(_.distFromLeaf).sum)
          .max + 2

      val curNode = Node(nodeId, distFromLeaf, maxPath)

      println(s"Calculationg node: ${curNode.id} and desc size is: ${directDescNodes.size}")
      (curNode, inDeptNodes :+ curNode)
    }

    def traverseTree(comingFrom: Int, node: (Int, Set[Int])): (Node, List[Node]) = {
      val (nodeId, touchPoints) = node
      val directDesc = touchPoints diff Set(comingFrom)

      if (directDesc.isEmpty) (Node(nodeId, 0, 0), List())
      else {
        val calculated =
          directDesc.map(
            e =>
              traverseTree(nodeId, (e, vgr(e)))
          )
        calculateNode(nodeId, calculated)
      }
    }

    println(s"Start from node: ${vgr.head._1}")
    val (node, lstNodes) = traverseTree(-1, vgr.head)
    lstNodes.foreach(println)
    lstNodes.map(n => n.longestPath).max
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