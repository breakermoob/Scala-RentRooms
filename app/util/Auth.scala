package auth

import pdi.jwt.{Jwt, JwtAlgorithm, JwtHeader, JwtClaim, JwtOptions}

import scala.util.{Try, Success, Failure}
import scala.util.parsing.json.JSON
import scalaj.http.{Http, HttpOptions}
import java.util.Base64
import java.security.cert.CertificateFactory
import java.io.ByteArrayInputStream

class Auth {
  def validate(token: String): Try[Boolean] = {
    val textDecoded = new String(Base64.getDecoder.decode(token.split('.')(0)))
    val values = JSON.parseFull(textDecoded)
    values match {
      case Some(v:Map[String,String]) => {
        val response = Http("https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com")
        .option(HttpOptions.readTimeout(10000)).asString
        val keys = JSON.parseFull(response.body)
        keys match {
          case Some(k:Map[String,String]) => {
            val pkey = k(v("kid"))
            .replaceAll("-----BEGIN CERTIFICATE-----", "")
            .replaceAll("-----END CERTIFICATE-----", "")
            .replaceAll("\\s", "")
            val certder = Base64.getMimeDecoder.decode(pkey)
            val certstream = new ByteArrayInputStream(certder)
            val cert = CertificateFactory.getInstance("X.509").generateCertificate(certstream)
            val key = cert.getPublicKey()

            try {
              val result = Jwt.validate(token, key, Seq(JwtAlgorithm.RS256))
              return Success(true)
            } catch {
                case e: Exception => return Failure(e)
            }
          }
          case None => return Failure(new java.lang.Exception("Public key can't be retrieved."))
        }
      }
      case None => return Failure(new java.lang.Exception("JWT header wrong."))
    }
  }
}