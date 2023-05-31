package com.github.asadaguitar.console.payload.db.field.user

import com.github.asadaguitar.console.util.security.types.HashedValue

case class HashedUserPassword(value: HashedValue) extends AnyVal
