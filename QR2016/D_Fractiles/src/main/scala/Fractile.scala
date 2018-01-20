import FractilesUtils.TileSeq
import Utilities.Constants.Tiles

case class Fractile(origSeq: TileSeq, currSeq: TileSeq, C: Int) {

	def expand(): Fractile = {
		def substWithGoldTiles():TileSeq = {
			val artSize = origSeq.size
			Seq.fill(artSize)(Tiles.G)
		}

		def substTile(tile: Tiles): TileSeq = {
			if (tile == Tiles.G) substWithGoldTiles()
			else origSeq
		}

		val expandedSeq = currSeq.flatMap(substTile)

		Fractile(origSeq, expandedSeq, C + 1)
	}
}




//trait Tile{
//	def expand()
//}
//case class Lead(origSeq: Seq[Tile]) extends Tile{
//	override def expand(): Unit = origSeq
//}
//case class Gold(origSeq: Seq[Tile]) extends Tile{
//	override def expand(): Unit = Seq.fill(origSeq.size)(Gold(origSeq))
//}
//
//
//
//trait Fractile{
//	def isEmpty = false
//	def
//}