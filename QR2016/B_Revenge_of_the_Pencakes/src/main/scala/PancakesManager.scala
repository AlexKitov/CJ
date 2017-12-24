import Utilities.Constants._

object PancakesManager {
	type Pancakes = Seq[Char]

	def faceThemUp(pancakes: Pancakes): Int = {

		def faceUpRun(pancakes: Pancakes, count: Int): Int = {
			if (areAllFaceUp(pancakes)) count
			else faceUpRun(rotateFirstSame(pancakes), count + 1)
		}

		faceUpRun(pancakes, 0)
	}

	def rotatePancakes(pancakes: Pancakes): Pancakes = {
		val lead = pancakes.head
		if (lead == faceUp) Seq.fill(pancakes.size)(faceDown)
		else Seq.fill(pancakes.size)(faceUp)
	}

	def rotateFirstSame(pancakes: Pancakes): Pancakes = {

		val (toRotate, toStay) = pancakes.span(_ == pancakes.head)
		rotatePancakes(toRotate) ++ toStay
	}

	def areAllFaceUp(pancakes: Pancakes): Boolean = {
		pancakes.forall(_ == faceUp)
	}

	def formatCases(caseInd: Int, count: Int): String = {
		s"Case #$caseInd: $count"
	}
}

