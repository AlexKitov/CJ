import org.scalatest.FunSuite
import FractilesUtils._
import Utilities.Constants._

class FractilesUtilsTest extends FunSuite {

	test("genOriginalSeqs should work propperly"){
		val origSeq = genFractiles(3)
		origSeq.foreach(println)
	}

	test("expandComplexity should work propperly"){
		val origSeq = genFractiles(3)

		val res = expandComplexity(origSeq(5), origSeq(5))

		println(res)
	}


}
