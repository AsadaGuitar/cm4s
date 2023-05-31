package com.github.asadaguitar.console.payload.db.field.user

import com.github.asadaguitar.console.util.security.types.EncryptedValue

case class EncryptedUserEmailAddress(value: EncryptedValue) extends AnyVal
