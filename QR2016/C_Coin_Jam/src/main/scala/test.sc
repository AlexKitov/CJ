
import Stream._

val a = Seq.fill(5)('+') ++ Seq.fill(3)('-') ++ Seq.fill(3)('+')
a.forall(_ == '+')

a.span(_ == '+')


val b = 0.4356
val c = 0.56


b.toInt
b.ceil.toInt
c.ceil.toInt

math.round(b)
math.round(c)

lazy val a2: Stream[Int] = 2 +: from(3, 2)

val b2 = a2.filter(125 % _ == 0).take(1) foreach println
