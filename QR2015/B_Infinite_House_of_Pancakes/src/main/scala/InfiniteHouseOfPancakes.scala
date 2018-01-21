import Utilities.Constants._

object InfiniteHouseOfPancakes {
	case class NOcc(value: Int, occurrences: Int){
		def split: Seq[NOcc] = {
			val div2 = value / 2
			val diff = value - div2

			if (div2 == diff) Seq(NOcc(div2, occurrences * 2))
			else Seq(NOcc(diff, occurrences), NOcc(div2, occurrences))
		}
	}
	def solveTask(example: PancakesDist): Int ={

//		println("########### Solution to next task follows ##############")

		def getOccurrences(example: PancakesDist): Seq[NOcc] = {
			val grouped = example.groupBy(v => v)

			grouped.map{
				case(value, lst) =>
					NOcc(value, lst.size)
			}.toSeq
		}

		def consolidate(pc: Seq[NOcc]): Seq[NOcc] = {
			val grouped = pc.groupBy(v => v.value)
			grouped.map{case (v, occ) => NOcc(v, occ.map(_.occurrences).sum)}.toSeq
		}

		def loop(occurrences: Seq[NOcc], specialMinutes: Int, solution: Int, step: Int): Int = {
			if(occurrences.head.value <= 2) math.min(specialMinutes + 2, solution)
			else{

				val split  = consolidate(occurrences.head.split ++ occurrences.tail)

				val sorted = split.sortWith(_.value > _.value)

				val maxPanc = sorted.head.value

				val specMins = specialMinutes + occurrences.head.occurrences

				val sol = math.min(solution, maxPanc + specMins)

//				println(s"=============================================")
//				println(s"Step #$step: $sorted")
//				println(s"Step #$step: maxPc=$maxPanc, specMins=$specMins, thisRound=${maxPanc + specMins}, sol=$sol")

				loop(sorted, specMins, sol, step + 1)
			}
		}


		val occExample = getOccurrences(example)
		val sorted = occExample.sortWith(_.value > _.value)

//		println(s"Before loop: $sorted")

		loop(sorted, 0, example.max, 1)
	}
}

