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

		val res = origSeq.map(_.expand()) //expandComplexity(origSeq(5), origSeq(5))

		println(s"###############################")
		println(s"Fractile 5 is : ${res(5)}")
		println(s"Fractile 5 is : ${res(5).expand()}")

	}

	test("getArtworks should work propperly"){
		val fractilesSeq = getArtworks(InputPerTask(2, 3, 2))

		println(s"+++++++++++++++++++++++++++++++")
		fractilesSeq.foreach(println)

		val fractilesSeq2 = getArtworks(InputPerTask(3, 2, 3))

		println(s"===============================")
		fractilesSeq2.foreach(println)

		val fractilesSeq3 = getArtworks(InputPerTask(2, 1, 1))
		println(s"===============================")
		fractilesSeq3.foreach(println)

		val fractilesSeq4 = getArtworks(InputPerTask(3, 3, 3))
		println(s"===============================")
		fractilesSeq4.foreach(println)

		val fractilesSeq5 = getArtworks(InputPerTask(4, 3, 4))
		println(s"===============================")
		fractilesSeq5.foreach(println)

		val fractilesSeq6 = getArtworks(InputPerTask(10, 3, 4))
		println(s"===============================")
		fractilesSeq6.foreach(println)
	}


}
