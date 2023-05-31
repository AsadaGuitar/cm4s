package com.github.asadaguitar.console.entry_point.auth

import akka.http.scaladsl.server.Directive1
import com.emarsys.jwt.akka.http.{JwtAuthentication, JwtConfig}
import com.github.asadaguitar.console.entry_point.marshaller.UserInfoMarshaller
import com.github.asadaguitar.console.payload.db.field.user.UserId
import com.typesafe.config.Config

trait AuthDirective extends UserInfoMarshaller {

  protected val config: Config

  private val jwtAuth: JwtAuthentication = new JwtAuthentication {
    override lazy val jwtConfig: JwtConfig = new JwtConfig(config)
  }

  protected def generateJwtToken(authProfile: AuthProfile): JwtToken = JwtToken(
    jwtAuth.generateToken(authProfile)
  )

  protected def authenticate: Directive1[AuthProfile] =
    jwtAuth.jwtAuthenticate(jwtAuth.as[AuthProfile])

}
