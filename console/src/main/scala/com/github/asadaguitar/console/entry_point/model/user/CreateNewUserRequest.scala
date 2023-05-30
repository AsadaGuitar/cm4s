package com.github.asadaguitar.console.entry_point.model.user

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives.{complete, provide}
import cats.data.Validated
import cats.implicits.catsSyntaxTuple3Semigroupal
import com.github.asadaguitar.console.entry_point.validation.UserInfoValidation
import com.github.asadaguitar.console.payload.db.field.user._
import com.typesafe.scalalogging.slf4j.LazyLogging

case class CreateNewUserRequest(
    userName: UserName,
    userEmailAddress: UserEmailAddress,
    userPassword: UserPassword
)

object CreateNewUserRequest extends LazyLogging {

  def validate(
      form: CreateNewUserRequestForm
  ): Directive1[CreateNewUserRequest] = {
    (
      UserInfoValidation.validateUserName(form.userName).toValidatedNel,
      UserInfoValidation
        .validateUserEmailAddress(form.userEmailAddress)
        .toValidatedNel,
      UserInfoValidation.validateUserPassword(form.userPassword).toValidatedNel
    )
      .mapN(CreateNewUserRequest.apply) match {
      case Validated.Valid(req) => provide(req)
      case Validated.Invalid(e) =>
        logger.warn(s"validation error. ${e.toList.mkString}")
        complete("")
    }
  }
}
