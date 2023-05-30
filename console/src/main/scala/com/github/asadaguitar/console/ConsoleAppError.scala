package com.github.asadaguitar.console

import cats.implicits.none

sealed trait ConsoleAppError {
  val message: String
  val cause: Option[Throwable] = none[Throwable]
}

object ConsoleAppError {
  case class ValidationError(
      message: String,
      cause: Option[Throwable] = none[Throwable]
  )
}
