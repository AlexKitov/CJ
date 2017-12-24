package Utilities

import java.io.{File, PrintWriter}
import java.nio.file.{Path, Paths}
import java.text.SimpleDateFormat
import java.util.Calendar

import scala.io.Source

object Utils {
	/**
	  * Get the relative path to a resource file depending on Test / main
	  * @param resource
	  * @return
	  */
	def fsPath(resource: String): Path =
		Paths.get(getClass.getClassLoader.getResource(resource).toURI)

	def now: String = {
		val dt = Calendar.getInstance.getTime
		val formatter = new SimpleDateFormat("yy/MM/dd hh:mm:ss")

		formatter.format(dt)
	}

	def getLines(fileName: String): Seq[String] = {
		Source.fromFile(fileName).getLines.toSeq
	}

	def writeLines(data: Seq[String], fileName: String): Unit ={
		val pw = new PrintWriter(new File(fileName))
		data.map(pw.println)
		pw.close()
	}
}
