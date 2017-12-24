val a = Seq.fill(5)('+') ++ Seq.fill(3)('-') ++ Seq.fill(3)('+')
a.forall(_ == '+')

a.span(_ == '+')
