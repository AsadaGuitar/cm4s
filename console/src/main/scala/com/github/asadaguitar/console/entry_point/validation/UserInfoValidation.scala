package com.github.asadaguitar.console.entry_point.validation

import cats.data.Validated
import com.github.asadaguitar.console.payload.db.field.user.{UserEmailAddress, UserName, UserPassword}
import com.github.asadaguitar.console.ConsoleAppError.ValidationError

object UserInfoValidation {

  def validateUserName(value: String): Validated[ValidationError, UserName] = {
    Validated.cond(
      value.nonEmpty,
      UserName(value),
      ValidationError(s"$value is not matches user_name pattern.")
    )
  }

  def validateUserEmailAddress(value: String): Validated[ValidationError, UserEmailAddress] ={
    val pattern = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$\n".r
    Validated.cond(
      pattern.matches(value),
      UserEmailAddress(value),
      ValidationError(s"$value is not matches user_email_address pattern.")
    )
  }

  def validateUserPassword(value: String): Validated[ValidationError, UserPassword] = {
    val pattern = "[!-~]{8,255}".r
    Validated.cond(
      pattern.matches(value),
      UserPassword(value),
      ValidationError(s"$value is not matches user_password pattern.")
    )
  }
}
