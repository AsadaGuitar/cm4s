package com.github.asadaguitar.console.entry_point.auth

import com.github.asadaguitar.console.payload.db.field.client.ClientId
import com.github.asadaguitar.console.payload.db.field.user.UserId

case class AuthProfile(
    clientId: ClientId,
    userId: UserId
)
