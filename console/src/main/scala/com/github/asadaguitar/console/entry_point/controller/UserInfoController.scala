package com.github.asadaguitar.console.entry_point.controller

import akka.http.scaladsl.server.Directives.{entity, pathPrefix, post}
import akka.http.scaladsl.server.{Directives, Route}
import com.github.asadaguitar.console.payload.db.field.user.UserId
import com.typesafe.config.Config

final class UserInfoController(protected val config: Config)
    extends Controller(config) {

  override val route: Route = ???

  private def createNewUser =
    pathPrefix("") {
      post {
        entity(Directives.as[UserId]) {

        }
      }
    }

}
