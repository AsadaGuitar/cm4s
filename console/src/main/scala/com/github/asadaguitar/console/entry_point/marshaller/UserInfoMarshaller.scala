package com.github.asadaguitar.console.entry_point.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.github.asadaguitar.console.entry_point.model.user.{CreateNewUserRequest, CreateNewUserRequestForm}
import com.github.asadaguitar.console.payload.db.field.user.{UserEmailAddress, UserId, UserName, UserPassword}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait UserInfoMarshaller extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userIdMarshaller: RootJsonFormat[UserId] = jsonFormat1(UserId)
  implicit val userNameMarshaller: RootJsonFormat[UserName] = jsonFormat1(
    UserName
  )
  implicit val userEmailAddressMarshaller: RootJsonFormat[UserEmailAddress] =
    jsonFormat1(UserEmailAddress)

  implicit val userPasswordMarshaller: RootJsonFormat[UserPassword] = jsonFormat1(UserPassword)

  implicit val createNewUserRequestMarshaller: RootJsonFormat[CreateNewUserRequest] = jsonFormat3(CreateNewUserRequest.apply)

}
