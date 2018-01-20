import Utilities.Constants._

object PancakesManagerFast {

	def faceThemUpFast(pancakes: Seq[Char]): Int = {

		def faceUpRun(pancakes: Seq[Char], count: Int): Int = {
			if (pancakes.tail.isEmpty) {
				if (pancakes.head == faceDown) count + 1
				else count
			}
			else {
				if (pancakes.head != pancakes.tail.head) faceUpRun(pancakes.tail, count + 1)
				else faceUpRun(pancakes.tail, count)
			}
		}

		faceUpRun(pancakes, 0)
	}
}
