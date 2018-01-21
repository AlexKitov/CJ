val example = Seq(1,2,3,2,3,6,7,1,1,7,7,7)

example.groupBy(v => v)

import InfiniteHouseOfPancakesCorrect.NOcc

val a = NOcc(7, 5)
a.split

val b = NOcc(6, 5)
b.split

val c = Seq(b, a)

val sorted = c.sortWith(_.value > _.value)
val lines = Seq("1", "3", "4", "1 2 1 2")
val problems = lines.grouped(2)

val a2 = problems.map(
	iter =>
	(
			iter.head.toInt
			, iter.tail.head.split(" ").map(_.toInt).toSeq
	)
)

val c2 = a2.toSeq.toMap

c2.keys.foreach(println)

c2(1)

val c3 = c2.map{case (k,v) => (k, v.size)}

