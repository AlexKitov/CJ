import java.nio.file.{Path, Paths}

import FractilesUtils.InputPerTask
import Utilities.CustomLogger._
import Utilities.Utils._

object Main {
	def getOutputPath(fileName: String): Path  = Paths.get(s"./out/$fileName")
	def getInputPath(fileName: String): String = fsPath(fileName).toString


	def main(args: Array[String]): Unit =
		log("MainProgram", TimeUnits.hms){
			val (numExamples, examples) = getTaskInputs(args(0))

//			val res = genJJamCoinsOfNLenght(n, j)
//
//			produceOutput(jcSeq, args(1))
		}

	def getTaskInputs(filename: String): (Int, Seq[InputPerTask]) = {
		// read the input file
		val lines       = getLines(getInputPath(filename))
		val numExamples = lines.head.toInt
		val examples    = lines.tail.map(_.split(" ").toSeq.map(_.toInt))
		val taskInpits = examples.map(seq => InputPerTask(seq(0), seq(1), seq(2)))

		println(s"Number of examples are: '$numExamples'")
		println(s"First example is: '${taskInpits.head}'")
		println(s"Last  example is: '${taskInpits.last}'")

		(numExamples, taskInpits)
	}

//	def produceOutput(jamcoinsRes: Seq[JamcoinRes], fileName: String): Unit = {
//
//		val linesRes = line0 +: formatJamcoinRes(jamcoinsRes)
//
//		linesRes.foreach(println)
//
//		// Generate output file name
//		val fileNameOut = getOutputPath(fileName)
//		println(fileNameOut)
//		writeLines(linesRes, fileNameOut.toString)
//	}
}
