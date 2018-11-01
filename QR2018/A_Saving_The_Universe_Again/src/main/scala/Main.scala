import Utilities.CustomLogger._
import SaveTheUniverseAgain._

object Main {

	def main(args: Array[String]): Unit =
		log("MainProgram", TimeUnits.hms) {
			val problems = getTaskInputs(args(0))

			val problemSolution =
				log("Functional Solution") {
					problems.map(solveTask)
				}
			produceOutput(problemSolution, args(1))
		}
}
