package Utilities

object Constants {
//	val allBases: Seq[Int] = 2 to 10
//	val endStream = 1000000

//	val goldTile = "G"
//	val leadTile = "L"

	val possibleTile = Seq(Tiles.G, Tiles.L)

	trait Tiles
	object Tiles {
		case object L extends Tiles
		case object G extends Tiles
	}
}
