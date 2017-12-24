import org.scalatest.FunSuite
import JamCoin._
import Utilities.Constants._

class JamCoinTest extends FunSuite {
	val jamcoin      = Seq(1,0,0,0,1,1)
	val falseJamcoin = Seq(1,1,0,1,1,1)

	test(s"convertToDec should work properly"){
		assert(convertToDec(falseJamcoin, 3) == 337)
	}


	test(s"getDecForAllBases should work properly"){
		val jamcoin = Seq(1,0,1)
		val decimals = getDecForAllBases(jamcoin)

		assert(decimals.size == allBases.size)

		assert(decimals(0)  == 5)
		assert(decimals(1)  == 10)
		assert(decimals(allBases.size - 1) == 101)
	}

	test(s"getDecDivisors should work properly"){
		val jamcoin = Seq(1,0,1)

		val decDiv = getDecForAllBases(jamcoin).map(getDecDivisor)

		decDiv.foreach(decDev => println(s"Number is: ${decDev.get.dec} div are: ${decDev.get.div}"))

	}

	test(s"isJamcoin should work properly"){
		assert(!isJamcoin(falseJamcoin, getDecForAllBases(falseJamcoin).map(getDecDivisor)))

		assert(isJamcoin(jamcoin, getDecForAllBases(jamcoin).map(getDecDivisor)))
	}

	test(s"confirmJamcoin should work properly"){
		val jcConf = validateJamcoin(jamcoin)

		jcConf.foreach(jcres=> println(s"JC ${jcres.jamcoin} has divs: ${jcres.divPerBase.mkString(", ")}"))

		assert(validateJamcoin(falseJamcoin).isEmpty)

		assert(jcConf.contains((JamcoinRes(jamcoin, Vector(5, 13, 3, 31, 43, 3, 73, 7, 3)))))
	}
}
