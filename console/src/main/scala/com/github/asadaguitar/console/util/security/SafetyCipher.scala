package com.github.asadaguitar.console.util.security

import cats.effect.IO
import com.github.asadaguitar.console.util.security.types.{
  EncryptedValue,
  LiteralValue
}

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util
import java.util.Base64
import javax.crypto.spec.SecretKeySpec

trait SafetyCipher {

  def encrypt(value: LiteralValue): IO[EncryptedValue]

  def decrypt(value: EncryptedValue): IO[LiteralValue]
}

object SafetyCipher {

  private val SALT = ""
  private val KEY = ""

  private val secretKey: SecretKeySpec = {
    var keyBytes: Array[Byte] = (SALT + KEY).getBytes("UTF-8")
    val sha: MessageDigest = MessageDigest.getInstance("SHA-1")
    keyBytes = sha.digest(keyBytes)
    keyBytes = util.Arrays.copyOf(keyBytes, 16)
    new SecretKeySpec(keyBytes, "AES")
  }

  private val encryptionCipher = {
    val cipher: javax.crypto.Cipher = javax.crypto.Cipher.getInstance("AES")
    cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey)
    cipher
  }

  private val decryptionCipher = {
    val cipher: javax.crypto.Cipher = javax.crypto.Cipher.getInstance("AES")
    cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey)
    cipher
  }

  def impl: SafetyCipher = new SafetyCipher {

    override def encrypt(value: LiteralValue): IO[EncryptedValue] = for {
      encrypted <- IO(encryptionCipher.doFinal(value.getBytes("UTF-8")))
      encode <- IO(
        new String(Base64.getEncoder.encode(encrypted), StandardCharsets.UTF_8)
      )
    } yield encode

    override def decrypt(value: EncryptedValue): IO[LiteralValue] = for {
      decoded <- IO(Base64.getDecoder.decode(value))
      decrypted <- IO(new String(decryptionCipher.doFinal(decoded)))
    } yield decrypted
  }

}
