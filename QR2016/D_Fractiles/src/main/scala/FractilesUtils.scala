import Utilities.Constants.Tiles
import Utilities.Constants._


object FractilesUtils {
	case class InputPerTask(K: Int, C: Int, S: Int)

	type TileSeq = Seq[Tiles]

	def genFractiles(K: Int, elems: TileSeq = possibleTile): Seq[Fractile] = {
		def addNextTile(current: Seq[TileSeq]): Seq[TileSeq] = {
			for {
				seq <- current
				j   <- elems
			} yield seq :+ j
		}
		def loop(current: Seq[TileSeq], count: Int): Seq[TileSeq] = {
			if (count >= K) current
			else loop(addNextTile(current), count + 1)
		}
		val seqFract = loop(Seq(Seq()), 0)

		seqFract.map(tileSeq => Fractile(tileSeq, tileSeq, 1))
	}

	def expandSeqFractiles(seqFractiles: Seq[Fractile], nTimes: Int): Seq[Fractile] = {
			if(nTimes == 0) seqFractiles
			else expandSeqFractiles(seqFractiles.map(_.expand()), nTimes - 1)
	}

	def getArtworks(taskParams: InputPerTask): Seq[Fractile] = {
		val possibleFractiles = genFractiles(taskParams.K)
		expandSeqFractiles(possibleFractiles, taskParams.C - 1)
	}


}

