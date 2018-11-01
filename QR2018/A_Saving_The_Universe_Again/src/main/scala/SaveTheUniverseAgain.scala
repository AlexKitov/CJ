
import Utilities.Utils.{getInputPath, getLines, getOutputPath, writeLines}

object SaveTheUniverseAgain {

	case class ProblemExample(l: Int, rep: Int, str: String)

	def solveTask(example: ProblemExample): String ={
		def loop(strRep: Seq[Char]): Unit = {
			println(s"")
		}

		loop((example.str * example.rep).toCharArray.toSeq)

		"Yes"
	}





	//##########################################################
	//########## Deal with input for the problem ###############
	//##########################################################

	def getTaskInputs(filename: String): Seq[ProblemExample] = {
		// read the input file
		val lines      = getLines(getInputPath(filename))
		val numCases   = lines.head.toInt
		val cases      = lines.tail

		val problems = cases.map(
					iterator => {
						val lx = iterator.head.split(" ").map(_.toInt)
						ProblemExample(
							lx(0)
							,lx(1)
							, iterator.tail.head
						)
					}
				).toSeq

		problems.foreach(pe => println(s"Problem is: l = ${pe.l}, x = ${pe.rep}, str = '${pe.str}'"))

		problems
	}


	//##########################################################
	//########## Deal with output for the problem ##############
	//##########################################################

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
