package com.github.asadaguitar.console.entry_point.controller

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directives, Route}
import cats.data.Validated
import cats.effect.unsafe.implicits.global
import com.github.asadaguitar.console.entry_point.auth.AuthProfile
import com.github.asadaguitar.console.entry_point.model.user.{CreateNewUserRequest, CreateNewUserRequestForm, CreateNewUserResponse, CreateNewUserResponseInner}
import com.github.asadaguitar.console.payload.db.field.client.ClientId
import com.github.asadaguitar.console.payload.db.field.user.UserId
import com.github.asadaguitar.console.use_case.user.{CreateNewUserUseCase, CreateNewUserUseCaseCommand, CreateNewUserUseCaseReply}
import com.github.asadaguitar.console.util.security.{SafetyCipher, SafetyHasher}
import com.typesafe.config.Config

import scala.util.{Failure, Success}

final class UserInfoController(protected val config: Config)
    extends ControllerBase(config) {

  implicit val safetyCipher: SafetyCipher = SafetyCipher.impl
  implicit val safetyHasher: SafetyHasher = SafetyHasher.impl

  private val createNewUserUseCase = new CreateNewUserUseCase

  override val route: Route =
    path("user") {
      post {
        val authProfile = AuthProfile(
          clientId = ClientId("KOBA"), userId = UserId("Asada")
        )
        createNewUser(authProfile)
      }
    }

  private def createNewUser(authProfile: AuthProfile): Route = {
    entity(Directives.as[CreateNewUserRequestForm]) { form =>
      CreateNewUserRequest.validate(form) match {
        case Validated.Valid(request) =>
          onComplete {
            createNewUserUseCase
              .run(
                CreateNewUserUseCaseCommand(
                  clientId = authProfile.clientId,
                  userId = authProfile.userId,
                  createNewUserRequest = request
                )
              )
              .unsafeToFuture()
          } {
            case Success(CreateNewUserUseCaseReply(newUserId, createdAt)) =>
              val responseJson = CreateNewUserResponse(
                client_id = authProfile.clientId.value, user_id = newUserId.value, new_user_info = CreateNewUserResponseInner(
                  user_id = newUserId.value, created_at = createdAt
                )
              )
              complete(responseJson)
            case Failure(exception) =>
              println(exception)
              complete(
                StatusCodes.InternalServerError,
                HttpEntity(
                  ContentTypes.`text/html(UTF-8)`,
                  "Internal server error has occurred"
                )
              )
          }

        case Validated.Invalid(e) =>
          println(e.toList)
          complete(
            StatusCodes.BadRequest,
            HttpEntity(
              ContentTypes.`text/html(UTF-8)`,
              "Invalid request parameter"
            )
          )
      }
    }
  }
}
