object StandingOvation {
	def solveTask(seq: Seq[Int]): Int ={
		def standingPeopleForShineLevel(s: Seq[Int], sums: Seq[Int], sum: Int)
		: Seq[Int] ={
			if (s.isEmpty) sums
			else standingPeopleForShineLevel(s.tail, sums :+ (sum + s.head), sum + s.head)
		}

		val standingPerShineLevel = standingPeopleForShineLevel(seq, Seq(), 0)

		val standingWithShineLevel = standingPerShineLevel.zipWithIndex

		val shineLevelShift = standingWithShineLevel.map{case (people, sLev) => (people, sLev + 1)}

		val deficit = shineLevelShift.map{case (pers, sLev) => pers - sLev}
		math.abs(math.min(deficit.min, 0))
	}
}

