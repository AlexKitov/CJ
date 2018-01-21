import Utilities.Utils._
import Utilities.CustomLogger._
import InfiniteHouseOfPancakes._
import java.nio.file.{Path, Paths}

import Utilities.Constants.ProblemExample

object Main {
	def getOutputPath(fileName: String): Path  = Paths.get(s"./out/$fileName")
	def getInputPath(fileName: String): String = fsPath(fileName).toString

	def main(args: Array[String]): Unit =
		log("MainProgram", TimeUnits.hms){
			val problems = getTaskInputs(args(0))

			val problemSolution = log("Functional Solution"){problems.map(p => solveTask(p.pancakes))}

			produceOutput(problemSolution, args(1))
		}


	def getTaskInputs(filename: String): Seq[ProblemExample] = {
		// read the input file
		val lines      = getLines(getInputPath(filename))
		val splitLines = lines.tail

		val problems = splitLines.grouped(2)
				.map(iterator =>
					ProblemExample(
						iterator.head.toInt
						, iterator.tail.head.split(" ").map(_.toInt)
					)
				).toSeq

		problems.foreach(pe => println(s"Problem is: '${pe.numNonEmpty}, ${pe.pancakes.mkString(", ")}'"))

		problems
	}


	def produceOutput(solutions: Seq[Int], fileName: String): Unit = {

		val lines =
			for(i <- solutions.indices)
				yield s"Case #${i+1}: ${solutions(i)}"

		lines.foreach(println)

		// Generate output file name
		val fileNameOut = getOutputPath(fileName)
		println(fileNameOut)
		writeLines(lines, fileNameOut.toString)
	}
}
