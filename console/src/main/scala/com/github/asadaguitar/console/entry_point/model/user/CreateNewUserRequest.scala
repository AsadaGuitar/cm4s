package com.github.asadaguitar.console.entry_point.model.user

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives.{complete, provide}
import cats.data.{Validated, ValidatedNel}
import cats.implicits.catsSyntaxTuple3Semigroupal
import com.github.asadaguitar.console.ConsoleAppError.ValidationError
import com.github.asadaguitar.console.entry_point.validation.UserInfoValidation
import com.github.asadaguitar.console.payload.db.field.user._
import com.typesafe.scalalogging.slf4j.LazyLogging

case class CreateNewUserRequest(
    userName: UserName,
    userEmailAddress: UserEmailAddress,
    userPassword: UserPassword
)

object CreateNewUserRequest {

  def validate(
      form: CreateNewUserRequestForm
  ): ValidatedNel[ValidationError, CreateNewUserRequest] = {
    (
      UserInfoValidation.validateUserName(form.user_name).toValidatedNel,
      UserInfoValidation
        .validateUserEmailAddress(form.user_email_address)
        .toValidatedNel,
      UserInfoValidation.validateUserPassword(form.user_password).toValidatedNel
    )
      .mapN(CreateNewUserRequest.apply)
  }
}
