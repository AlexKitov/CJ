import Utilities.Constants._
import Utilities.Utils._
import Utilities.CustomLogger._
import PancakesManager._
import java.nio.file.Paths

object Main {
	val smallExample = fsPath(smallExampleFileName)
	val largeExample = fsPath(largeExampleFileName)


	def main(args: Array[String]): Unit = log("MainSmallExample", TimeUnits.milli){
		// read the input file
		val lines = getLines(largeExample.toString)
		val numExamples = lines.head
		val examples    = lines.tail

		val examplePancakes  = examples.map(str => str.toCharArray.toSeq)

		val numOfRotations = examplePancakes.map(faceThemUp)

		val zipped = examples zip numOfRotations

		zipped.foreach{
			case (example, sleep) => println(s"$example => $sleep")
		}

		val linesRes =
			for (i <- 1 to numExamples.toInt)
				yield formatCases(i, numOfRotations(i-1))

		linesRes.map(println)

		val fileNameOut = Paths.get(s"./out/$largeExampleFileNameOut")
		println(fileNameOut)
		writeLines(linesRes, fileNameOut.toString)
	}
}
