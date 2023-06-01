package com.github.asadaguitar.console.entry_point.model.user

case class CreateNewUserRequestForm(
    user_name: String,
    user_email_address: String,
    user_password: String
)

