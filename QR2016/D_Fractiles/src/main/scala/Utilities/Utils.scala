package Utilities

import java.io.{File, PrintWriter}
import java.nio.file.{Path, Paths}
import java.text.SimpleDateFormat
import java.util.Calendar

import scala.io.Source

object Utils {
	/**
	  * Get the relative path to a resource file depending on Test / main
	  * @param resource name of a file in resource folder
	  * @return Path to that file
	  */
	def fsPath(resource: String): Path =
		Paths.get(getClass.getClassLoader.getResource(resource).toURI)

	/**
	  * Gets the current date time in a specific format
	  * @return Date Time in a specific format
	  */
	def now: String = {
		val dt = Calendar.getInstance.getTime
		val formatter = new SimpleDateFormat("yy/MM/dd hh:mm:ss")

		formatter.format(dt)
	}

	/**
	  * Reads the lines from a file into a Seq of Strings
	  * @param fileName the name of the file
	  * @return Seq of lines
	  */
	def getLines(fileName: String): Seq[String] = {
		Source.fromFile(fileName).getLines.toSeq
	}

	/**
	  * Write a Seq of lines into a file
	  * @param data Lines to be written into a file
	  * @param fileName the name of the file
	  */
	def writeLines(data: Seq[String], fileName: String): Unit ={
		val pw = new PrintWriter(new File(fileName))
		data.foreach(pw.println)
		pw.close()
	}
}
