import org.scalatest.FunSuite
import FindNumber._
import Utilities.Constants._

class FindNumberTest extends FunSuite {
	test(s"Test for ${insomniaNumber} with N = 0"){
		val N = 0
		assert(findNumber(N) == insomniaNumber)
	}
}
