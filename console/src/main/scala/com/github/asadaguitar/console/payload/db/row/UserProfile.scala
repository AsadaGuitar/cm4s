package com.github.asadaguitar.console.payload.db.row

import com.github.asadaguitar.console.payload.db.field.date.ApplicationDatetime
import com.github.asadaguitar.console.payload.db.field.user.{
  EncryptedUserEmailAddress,
  EncryptedUserName,
  HashedUserPassword,
  UserId
}

case class UserProfile(
    userId: UserId,
    encryptedUsername: EncryptedUserName,
    encryptedUserEmailAddress: EncryptedUserEmailAddress,
    hashedUserPassword: HashedUserPassword,
    createdAt: ApplicationDatetime,
    closedAt: Option[ApplicationDatetime]
)
