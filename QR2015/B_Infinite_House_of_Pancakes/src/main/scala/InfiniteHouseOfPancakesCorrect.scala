import Utilities.Constants._

object InfiniteHouseOfPancakesCorrect {

	def solveTaskCorrect(example: PancakesDist): Int ={
		val max_pancakes = example.max

		def loop(x: Int, solution: Int): Int ={
			if (x >= max_pancakes) solution
			else {
				val moves = example.map(pc => (pc - 1) / x).sum
				val sol = math.min(solution, moves + x)
//				println(s"Step $x -> solution = $sol")
				loop(x + 1, sol)
			}
		}

		loop(1, max_pancakes)
	}
}

