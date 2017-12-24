package Utilities

import Utilities.CustomLogger.TimeUnits.nano
import Utilities.Utils.now

object CustomLogger {
	def log[A](blockName: String, unit: TimeUnits = nano)(block: => A): A = {
		val startTime = startExecution(blockName)

		val result = block // call-by-name

		finishExecution(blockName, startTime, unit)

		result
	}

	private  def startExecution(blockName: String): Long = {
		println(s"####### Start executing block '$blockName' ...")
		System.nanoTime()
	}

	private def finishExecution(blockName: String, startTime: Long, unit: TimeUnits = nano): Unit = {
		val finishTime = System.nanoTime()

		println(s"####### Finish executing block '$blockName'!")
		val duration = Duration(startTime, finishTime)

		val statMsg = s"Elapsed time for block '$blockName':"

		println(duration.getDurationMessage(statMsg, unit))
	}



	trait TimeUnits
	object TimeUnits{
		case object nano  extends TimeUnits
		case object micro extends TimeUnits
		case object milli extends TimeUnits
		case object hms   extends TimeUnits
	}

	case class Duration(nsStart: Long, nsEnd: Long){

		import TimeUnits._

		private val nsDuration = math.abs(nsEnd - nsStart)

		private def unitUpSubSecs(dur: Long): Long = dur / 1000
		private def unitUpSubDays(dur: Long): Long = dur / 60

		// Calculate sub second durations
		def getNano : Long = nsDuration
		def getMicro: Long = unitUpSubSecs(getNano)
		def getMilli: Long = unitUpSubSecs(getMicro)
		def getSecs : Long = unitUpSubSecs(getMilli)

		// Calculate sub days durations
		def getMins : Long = unitUpSubDays(getSecs)
		def getHours: Long = unitUpSubDays(getMins)
		def getDays : Long = unitUpSubDays(getHours)

		def getDurationMessage(msg: String, unit: TimeUnits = nano): String = {
			unit match {
				case TimeUnits.nano  => s"$now: $msg $getNano nano seconds"
				case TimeUnits.micro => s"$now: $msg $getMicro micro seconds"
				case TimeUnits.milli => s"$now: $msg $getMilli milli seconds"
				case TimeUnits.hms   => s"$now: $msg $getHours hours $getMins minutes $getSecs seconds"
			}
		}

	}
}
