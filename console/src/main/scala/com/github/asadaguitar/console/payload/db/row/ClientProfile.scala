package com.github.asadaguitar.console.payload.db.row

import com.github.asadaguitar.console.payload.db.field.client.{
  ClientId,
  ClientName
}
import com.github.asadaguitar.console.payload.db.field.date.ApplicationDatetime

case class ClientProfile(
    clientId: ClientId,
    clientName: ClientName,
    createdAt: ApplicationDatetime,
    closedAt: Option[ApplicationDatetime]
)
