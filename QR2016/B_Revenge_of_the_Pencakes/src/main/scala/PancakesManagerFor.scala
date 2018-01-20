import Utilities.Constants._

object PancakesManagerFor {
	type Pancakes = Seq[Char]

	def faceThemUpImp(pancakes: Pancakes): Int = {
		var count = 0
		for(i <- 0 until pancakes.size - 1){
			if (pancakes(i) != pancakes(i + 1))
				count = count + 1
		}

		if (pancakes.last == faceDown)
			count = count + 1

		count
	}
}

