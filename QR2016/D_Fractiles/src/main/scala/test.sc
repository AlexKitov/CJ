
import Stream._
val a = Seq("G", "L")

a.permutations.foreach(println)

a.combinations(2).foreach(println)

def perm(n: Int = 3, elems: Seq[String]) = {

	def addOne(current: Seq[Seq[String]]) = {
		for {
			seq <- current
			j   <- elems
		} yield seq :+ j
	}

	def loop(current: Seq[Seq[String]], count: Int): Seq[Seq[String]] = {
		if (count >= n) current
		else loop(addOne(current), count + 1)
	}

	loop(Seq(Seq()), 0)
}

val a1 = perm(3, a)

a1.foreach(println)