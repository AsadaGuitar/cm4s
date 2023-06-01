package com.github.asadaguitar.console.use_case.user

import cats.effect.IO
import cats.syntax.option._
import com.github.asadaguitar.console.dao.UserProfileDao
import com.github.asadaguitar.console.entry_point.model.user.CreateNewUserRequest
import com.github.asadaguitar.console.payload.db.field.client.ClientId
import com.github.asadaguitar.console.payload.db.field.date.ApplicationDatetime
import com.github.asadaguitar.console.payload.db.field.user.{EncryptedUserEmailAddress, EncryptedUserName, HashedUserPassword, UserId, UserIdGen}
import com.github.asadaguitar.console.payload.db.row.UserProfile
import com.github.asadaguitar.console.use_case.UseCaseBase
import com.github.asadaguitar.console.util.security.{SafetyCipher, SafetyHasher}

case class CreateNewUserUseCaseCommand(
    clientId: ClientId,
    userId: UserId,
    createNewUserRequest: CreateNewUserRequest
)

case class CreateNewUserUseCaseReply(
    newUserId: UserId,
    createdAt: ApplicationDatetime
)

final class CreateNewUserUseCase(implicit
    safetyCipher: SafetyCipher,
    safetyHasher: SafetyHasher
) extends UseCaseBase {

  override type Command = CreateNewUserUseCaseCommand
  override type Result = CreateNewUserUseCaseReply

  override def run(command: Command): IO[Result] = {
    val CreateNewUserUseCaseCommand(
      clientId,
      _,
      CreateNewUserRequest(userName, userEmailAddress, userPassword)
    ) = command
    for {
      newUserId <- UserIdGen.generate(clientId)
      encryptedUserName <- safetyCipher
        .encrypt(userName.value)
        .map(EncryptedUserName)
      encryptedUserEmailAddress <- safetyCipher
        .encrypt(userEmailAddress.value)
        .map(EncryptedUserEmailAddress)
      hashedPassword <- safetyHasher
        .hash(userPassword.value)
        .map(HashedUserPassword)
      createdAt <- ApplicationDatetime.now
      userProfile = UserProfile(
        userId = newUserId,
        encryptedUsername = encryptedUserName,
        encryptedUserEmailAddress = encryptedUserEmailAddress,
        hashedUserPassword = hashedPassword,
        createdAt = createdAt,
        closedAt = none[ApplicationDatetime]
      )
      _ <- UserProfileDao.create(userProfile)
    } yield CreateNewUserUseCaseReply(newUserId, createdAt)
  }
}
