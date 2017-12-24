import org.scalatest.FunSuite
import PancakesManager._
import Utilities.Constants._

class PancakesManagerTest extends FunSuite {
	test(s"RotatePancakes should work properly"){
		val pancakesFacedUp   = Seq.fill(5)('+')
		val pancakesFacedDown = Seq.fill(3)('-')

		assert(rotatePancakes(pancakesFacedUp)   == Seq.fill(5)('-'))
		assert(rotatePancakes(pancakesFacedDown) == Seq.fill(3)('+'))
	}

	test("rotateFirstSame should work properly"){
		val pancakes = Seq.fill(5)('+') ++ Seq.fill(3)('-') ++ Seq.fill(3)('+')
		val rotated  = Seq.fill(5)('-') ++ Seq.fill(3)('-') ++ Seq.fill(3)('+')

		assert(rotateFirstSame(pancakes) == rotated)
		assert(rotateFirstSame(rotateFirstSame(pancakes)) == Seq.fill(11)('+'))
	}

	test("faceThemUp should work properly"){
		val pancakes = Seq('-', '-', '+', '-')

		assert(faceThemUp(pancakes) == 3)
	}
}
