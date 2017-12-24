import Utilities.Utils._
import Utilities.CustomLogger._
import JamCoin._
import java.nio.file.Paths

object Main {
	def getOutputPath(fileName: String) = Paths.get(s"./out/$fileName")
	def getInputPath(fileName: String)  = fsPath(fileName).toString

	val line0 = "Case #1:"

	def main(args: Array[String]): Unit =
		log("MainProgram", TimeUnits.hms){
			// n - length of jamcoin
			// j - number of jamcoins to discover
			val (n, j) = getTaskInputs(args(0))
			val jcSeq = genJJamCoinsOfNLenght(n, j)

			produceOutput(jcSeq, args(1))
		}

	def getTaskInputs(filename: String): (Int, Int) = {
		// read the input file
		val lines       = getLines(getInputPath(filename))
		val Array(n, j) = lines.tail.head.split(" ").map(_.toInt)

		println(s"N is: $n and J is: $j")

		(n, j)
	}

	def produceOutput(jamcoinsRes: Seq[JamcoinRes], fileName: String): Unit = {

		val linesRes = line0 +: formatJamcoinRes(jamcoinsRes)

		linesRes.map(println)

		// Generate output file name
		val fileNameOut = getOutputPath(fileName)
		println(fileNameOut)
		writeLines(linesRes, fileNameOut.toString)
	}
}
