package com.github.asadaguitar.console.entry_point.model.user

case class CreateNewUserRequestForm(
    userName: String,
    userEmailAddress: String,
    userPassword: String
)

