import org.scalatest.FunSuite
import FindNumber._

class HelpersTest extends FunSuite{
	test("Get digits of a number in a set"){
		assert(getDigits(123456) == Set(1,2,3,4,5,6), "Create Set[Int] containing the digits of an Int")
		assert(getDigits(122333) == Set(1,2,3), "Are duplicates are removed?")
		assert(getDigits(442211) == Set(1,2,4), "Test for reverse order")
	}

}
