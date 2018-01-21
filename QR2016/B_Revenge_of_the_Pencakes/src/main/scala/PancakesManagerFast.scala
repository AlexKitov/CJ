import Utilities.Constants._

object PancakesManagerFast {

	def faceThemUpFast(pancakes: Seq[Char]): Int = {

		def loop(pancakes: Seq[Char], count: Int): Int = {
			pancakes match {
				case head :: Nil =>
					if (head == faceDown) count + 1
					else count
				case head :: tail =>
					if (head != tail.head) loop(tail, count + 1)
					else loop(tail, count)
			}
		}

		loop(pancakes, 0)
	}
}
