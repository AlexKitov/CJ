import Utilities.Constants._
import Utilities.Utils._
import Utilities.CustomLogger._
import TimeUnits._
import PancakesManager._
import PancakesManagerFor._
import java.nio.file.{Path, Paths}

object Main {
	val smallExample = fsPath(smallExampleFileName)
	val largeExample = fsPath(largeExampleFileName)


	def main(args: Array[String]): Unit = log("MainSmallExample", milli){
		// read the input file
		val examplePancakes  = getInput(largeExample)

		//Solve functional
		val numOfRotations = log("Functional", milli){examplePancakes.map(faceThemUp)}

		//Solve imperative
		val numOfRotationsImperative = log("Imperative", milli){examplePancakes.map(faceThemUpImp)}

		//Solve functional Fast tail recursive
		val numOfRotationsFast = log("Functional Fast", micro){examplePancakes.map(faceThemUp)}

		// Print output
		printOutput(numOfRotationsImperative)

		printOutput(numOfRotations)

		printOutput(numOfRotationsFast)

	}

	def getInput(file: Path): Seq[Seq[Char]] = {
		val lines = getLines(file.toString)
		val examples    = lines.tail

		examples.map(str => str.toCharArray.toSeq)
	}

	def printOutput(numRotations: Seq[Int]): Unit = {
		val linesRes =
			for (i <- numRotations.indices)
				yield formatCases(i + 1, numRotations(i))

		linesRes.foreach(println)

		val fileNameOut = Paths.get(s"./out/$largeExampleFileNameOut")
		println(fileNameOut)
		writeLines(linesRes, fileNameOut.toString)
	}
}
