import Utilities.Constants.Tiles
import Utilities.Constants._


object FractilesUtils {
	case class InputPerTask(K: Int, C: Int, S: Int)

	type TileSeq = Seq[Tiles]

	def genFractiles(n: Int, elems: TileSeq = possibleTile): Seq[TileSeq] = {
		def addNextTile(current: Seq[TileSeq]): Seq[TileSeq] = {
			for {
				seq <- current
				j   <- elems
			} yield seq :+ j
		}
		def loop(current: Seq[TileSeq], count: Int): Seq[TileSeq] = {
			if (count >= n) current
			else loop(addNextTile(current), count + 1)
		}
		val seqFract = loop(Seq(Seq()), 0)

		seqFract
	}

	def expandComplexity(origSeq: TileSeq, artWorkToExpand: TileSeq) : TileSeq = {
		val artSize = origSeq.size
		val goldArt = Seq.fill(artSize)(Tiles.G)

		def substTile(tile: Tiles): TileSeq = {
			if (tile == Tiles.G) goldArt
			else origSeq
		}

		artWorkToExpand.flatMap(substTile)
	}



}

