//import Utilities.Constants.{Tiles, goldTile, possibleTile}
//import FractilesUtils._


trait Tile{
	def expand()
}

case class Lead(origSeq: Seq[Tile]) extends Tile{
	override def expand(): Unit = origSeq
}

case class Gold(origSeq: Seq[Tile]) extends Tile{
	override def expand(): Unit = Seq.fill(origSeq.size)(Gold(origSeq))
}
//
//val possibleTile = Seq(Lead, Tiles.L)
