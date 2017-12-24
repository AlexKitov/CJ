import Utilities.Constants.{allDigits, insomniaNumber, insomniaStr}

object FindNumber {
	type Digits = Set[Int]

	val maxCount = 50

	def findNumber(N: Int): Int = {

		def numberRun(digits: Digits, multiplier: Int, count: Int): Int = {
			val number = multiplier * N
			val newDigits = getDigits(number) ++ digits

			if(count > maxCount) insomniaNumber
			else if (isAsleep(newDigits)) number
			else if(newDigits == digits) numberRun(digits, multiplier + 1, count + 1)
			else numberRun(newDigits, multiplier + 1, 0)
		}

		if (N == 0) insomniaNumber
		else numberRun(Set(), 1, 0)
	}

	def getDigits(number: Int): Digits = {
		def carryDigits(n: Int, carry: Digits): Digits = n match {
			case n if n < 10 => carry + n
			case _ => carryDigits(n/10, carry + n%10)
		}
		carryDigits(number, Set())
	}

	def isAsleep(digits: Digits): Boolean = {
		digits == allDigits
	}

	def translateNums(sleepNums: Seq[Int]): Seq[String] = {
		def singleNumTranslate(num: Int): String = {
			if (num == -1) insomniaStr
			else num.toString
		}

		sleepNums.map(singleNumTranslate)
	}

	def formatCases(caseInd: Int, num: String): String = {
		s"Case #$caseInd: $num"
	}
}

