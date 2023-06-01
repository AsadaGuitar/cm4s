package com.github.asadaguitar.console.entry_point.marshaller

import spray.json._

import java.time.LocalDateTime
import scala.util.Try

trait DateMarshaller {

  implicit object DateFormat extends JsonFormat[LocalDateTime] {
    def write(date: LocalDateTime): JsString = JsString(datetimeToString(date))

    def read(json: JsValue): LocalDateTime = json match {
      case JsString(rawDate) =>
        parseDatetimeString(rawDate)
          .fold(deserializationError(s"Expected ISO Date format, got $rawDate"))(identity)
      case error => deserializationError(s"Expected JsString, got $error")
    }
  }

  private def datetimeToString(localDateTime: LocalDateTime) = localDateTime.toString

  private def parseDatetimeString(date: String): Option[LocalDateTime] =
    Try(LocalDateTime.parse(date)).toOption
}
