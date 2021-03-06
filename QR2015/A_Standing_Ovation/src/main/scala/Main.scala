import Utilities.Utils._
import Utilities.CustomLogger._
import StandingOvation._
import java.nio.file.{Path, Paths}

object Main {
	def getOutputPath(fileName: String): Path  = Paths.get(s"./out/$fileName")
	def getInputPath(fileName: String): String = fsPath(fileName).toString

	val line0 = "Case #1:"

	def main(args: Array[String]): Unit =
		log("MainProgram", TimeUnits.hms){
			val problems = getTaskInputs(args(0))
			val problemSolution = problems.map(solveTask)

			produceOutput(problemSolution, args(1))
		}

	def getTaskInputs(filename: String): Seq[Seq[Int]] = {
		// read the input file
		val lines      = getLines(getInputPath(filename))
		val splitLines = lines.tail.map(_.split(" "))

		val problems = splitLines.map{case Array(_, seq) => seq.map(_.asDigit)}

		problems.foreach(seq => println(s"Problem is: '$seq'"))

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
