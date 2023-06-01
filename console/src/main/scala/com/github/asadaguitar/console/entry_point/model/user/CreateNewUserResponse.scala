package com.github.asadaguitar.console.entry_point.model.user

import com.github.asadaguitar.console.payload.db.field.date.ApplicationDatetime

case class CreateNewUserResponse(client_id: String, user_id: String, new_user_info: CreateNewUserResponseInner)

case class CreateNewUserResponseInner(user_id: String, created_at: ApplicationDatetime)