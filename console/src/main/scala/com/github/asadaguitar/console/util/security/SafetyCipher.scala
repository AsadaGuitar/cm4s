package com.github.asadaguitar.console.util.security

import cats.effect.IO
import com.github.asadaguitar.console.util.security.types.{EncryptedValue, LiteralValue}

trait SafetyCipher {

  def encrypt(value: LiteralValue): IO[EncryptedValue]

  def decrypt(value: EncryptedValue): IO[LiteralValue]
}
