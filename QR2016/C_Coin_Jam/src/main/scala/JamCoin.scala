import Utilities.Constants._
import Utilities.CustomLogger._

import Stream._

object JamCoin {

	type Jamcoin = Seq[Int]

	case class DecDivPairs(dec: BigInt, div: Int)
	case class JamcoinRes(jamcoin: Jamcoin, divPerBase: Seq[Int])

	def genJamCoinCandidate(numDigits: Int): Seq[Int] = {
		val r = scala.util.Random

		val a = (0 until numDigits - 2)
				.foldLeft(Seq[Int]())(
					(acc, _) =>
						math.round(r.nextFloat % 2) +: acc
				)

		1 +: a :+ 1
	}

	def convertToDec(jamcoin: Jamcoin, base: BigInt): BigInt = {
		val revJamcoin = jamcoin.reverse

		val decimals =
			for(i <- revJamcoin.indices)
				yield revJamcoin(i) * base.pow(i)
		decimals.sum
	}

	def getDecForAllBases(jamcoin: Jamcoin): Seq[BigInt] = {
		allBases.map(convertToDec(jamcoin, _))
	}

	def getDecDivisor(n: BigInt): Option[DecDivPairs] = {
		val oddDivisors = (3 to endStream by 2).iterator.toStream
		val divToTest = 2 +: oddDivisors
		val div = divToTest.filter(n % _ == 0).take(1)

		if(div.isEmpty || n == div.head) None
		else Some(DecDivPairs(n, div.head))
	}

	def isJamcoin(jamcoin: Jamcoin, divForDecInEachBase: Seq[Option[DecDivPairs]]): Boolean = {
		if (jamcoin.head == 0) false
		else if (jamcoin.last == 0) false
		else !divForDecInEachBase.contains(None)
	}

	def validateJamcoin(jamcoin: Jamcoin): Option[JamcoinRes] = { //log("confirmJamcoin")
		val allDec = getDecForAllBases(jamcoin) //log("getDecForAllBases"){getDecForAllBases(jamcoin)}

		val decDiv = allDec.map(getDecDivisor)  //log("getDivisorsToTest"){allDec.map(getDecDivisors)}

		if (isJamcoin(jamcoin, decDiv)) Some(JamcoinRes(jamcoin, decDiv.map(_.get.div)))
		else None
	}

	def doesExist(jamcoinRes: JamcoinRes, set: Seq[JamcoinRes]): Boolean = {
		val jcs = set.map(_.jamcoin)
		val jcToTest = jamcoinRes.jamcoin
		jcs.contains(jcToTest)
	}



	def processJamcoinOpt(jamcoin: Option[JamcoinRes], jamcoinsStack: Seq[JamcoinRes]): Seq[JamcoinRes] = {

		def tryToAddJamcoin(): Seq[JamcoinRes] = {

			def addJamcoin(): Seq[JamcoinRes] = {
				println(s"Found a new one! '${jamcoinsStack.size + 1}' jamcoins found")
				jamcoin.get +: jamcoinsStack
			}

			if (!doesExist(jamcoin.get, jamcoinsStack)) addJamcoin()
			else jamcoinsStack
		}

		jamcoin match {
			case _: Some[JamcoinRes] => tryToAddJamcoin()
			case None => jamcoinsStack
		}
	}

	def generateJamcoin(numDigits: Int, jamcoinsStack: Seq[JamcoinRes]): Seq[JamcoinRes] = {
		val jc: Jamcoin = genJamCoinCandidate(numDigits)
		val jcOpt = validateJamcoin(jc)
		processJamcoinOpt(jcOpt, jamcoinsStack)
	}

	def genJJamCoinsOfNLenght(N: Int, J: Int): Seq[JamcoinRes] = {

		def loop(jamcoinRes: Seq[JamcoinRes]): Seq[JamcoinRes] = {
			if (jamcoinRes.size == J) jamcoinRes
			else loop(generateJamcoin(N, jamcoinRes))
		}

		loop(Seq())
	}

	def formatJamcoinRes(jcRes: Seq[JamcoinRes]): Seq[String] = {
		jcRes.map(jc => s"${jc.jamcoin.mkString("")} ${jc.divPerBase.mkString(" ")}")
	}

}

