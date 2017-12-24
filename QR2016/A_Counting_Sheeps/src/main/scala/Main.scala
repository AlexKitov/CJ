import Utilities.Constants._
import Utilities.Utils._
import Utilities.CustomLogger._
import FindNumber._
import java.nio.file.Paths

object Main {
	val smallExample = fsPath(smallExampleFileName)
	val largeExample = fsPath(largeExampleFileName)


	def main(args: Array[String]): Unit = log("MainSmallExample", TimeUnits.milli){
		// read the input file
		val lines = getLines(smallExample.toString)
		val numExamples = lines.head
		val examples    = lines.tail

		val exampleNums  = examples.map(_.toInt)

		val sleepNums = exampleNums.map(findNumber)

		val sleepStrings = translateNums(sleepNums)

		val zipped = exampleNums zip sleepNums

		zipped.foreach{
			case (example, sleep) => println(s"$example => $sleep")
		}

		val linesRes =
			for (i <- 1 to numExamples.toInt)
				yield formatCases(i, sleepStrings(i-1))

		linesRes.map(println)

		val fileNameOut = Paths.get(s"./out/$smallExampleFileNameOut")
		println(fileNameOut)
		writeLines(linesRes, fileNameOut.toString)

	}
}
