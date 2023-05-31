package com.github.asadaguitar.console.entry_point.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.github.asadaguitar.console.entry_point.auth.AuthProfile
import com.github.asadaguitar.console.entry_point.model.user.CreateNewUserRequestForm
import com.github.asadaguitar.console.payload.db.field.client.ClientId
import com.github.asadaguitar.console.payload.db.field.user.UserId
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait UserInfoMarshaller extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userIdMarshaller: RootJsonFormat[UserId] = jsonFormat1(UserId.apply)

  implicit val clientIdMarshaller: RootJsonFormat[ClientId] = jsonFormat1(ClientId.apply)

  implicit val authProfileMarshaller: RootJsonFormat[AuthProfile] = jsonFormat2(AuthProfile)

  implicit val createNewUserRequestFormMarshaller
      : RootJsonFormat[CreateNewUserRequestForm] = jsonFormat3(
    CreateNewUserRequestForm
  )

}
