val a = "88"

val s = a.map(_.asDigit)

def loop(s: Seq[Int], sums: Seq[Int], sum: Int)
: Seq[Int] ={
	if (s.isEmpty) sums
	else loop(s.tail, sums :+ (sum + s.head), sum + s.head)
}

val b = loop(s, Seq(), 0)

val c = b.zipWithIndex

val d = c.map{case (pers, sLev) => (pers, sLev + 1)}

val e = d.map{case (pers, sLev) => pers - sLev}
math.abs(math.min(e.min, 0))

val r = scala.util.Random
val a1 = for (i <- 1 to 1000) yield r.nextInt(9)
a1.mkString("")