package com.github.asadaguitar.console.entry_point.controller

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directives, Route}
import cats.data.Validated
import cats.effect.unsafe.implicits.global
import com.github.asadaguitar.console.entry_point.model.user.{
  CreateNewUserRequest,
  CreateNewUserRequestForm
}
import scala.util.{Success, Failure}

import com.github.asadaguitar.console.payload.db.field.user.UserId
import com.github.asadaguitar.console.use_case.user.{
  CreateNewUserUseCase,
  CreateNewUserUseCaseCommand
}
import com.typesafe.config.Config
import com.typesafe.scalalogging.slf4j.LazyLogging


final class UserInfoController(protected val config: Config)
    extends ControllerBase(config)
    with LazyLogging {

  private val createNewUserUseCase = new CreateNewUserUseCase

  override val route: Route = ???

  private def createNewUser = {
    val pathDirectives = "/user/new"
    path(pathDirectives) {
      post {
        authenticate { authProfile =>
          entity(Directives.as[CreateNewUserRequestForm]) { form =>
            CreateNewUserRequest.validate(form) match {
              case Validated.Valid(request) =>
                val runningUseCase = createNewUserUseCase
                  .run(
                    CreateNewUserUseCaseCommand(
                      clientId = authProfile.clientId,
                      userId = authProfile.userId,
                      createNewUserRequest = request
                    )
                  )
                  .unsafeToFuture()

                onComplete(runningUseCase) {
                  case Success(userId: UserId) => ??? // page
                  case Failure(exception) => ??? // 500
                }

              case Validated.Invalid(e) =>
                logger.warn(s"$pathDirectives: ${e.toList.mkString}")
                // 400
                ???
            }
          }
        }
      }
    }
  }

}
