package com.hunorkovacs.koauthsync.service.provider.persistence

import com.hunorkovacs.koauth.service.provider.persistence.{Nonce, RequestToken, AccessToken, Consumer}

import scala.collection.mutable.ListBuffer

class ExampleMemoryPersistence extends InMemoryPersistence {

  override val consumers = ListBuffer[Consumer](
    Consumer("OmFjJKNqU4v791CWj6QKaBaiEep0WBxJ", "wr1KLYYH6o5yKFfiyN9ysKkPXcIAim2S", "admin")
  )

  override val requestTokens = ListBuffer[RequestToken](
    RequestToken("OmFjJKNqU4v791CWj6QKaBaiEep0WBxJ", "nHmH9Qv6vPhZuvLVfofIXoKqpKA6BcSq",
      "S6o9gbm6l6yyR3kcry9kzj40C6mhErmu", "oob", None, None),
    RequestToken("OmFjJKNqU4v791CWj6QKaBaiEep0WBxJ", "DGnMlgdnCxc5ur3ZYX5t1BSjUOJUyqfZ",
      "y6v2ZtztCLH9Yewoeb4NoIXRmWlb74xV", "oob", Some("admin"), Some("W8FMcCtnDZ1Gw1m4"))
  )

  override val accessTokens = ListBuffer[AccessToken](
    AccessToken("OmFjJKNqU4v791CWj6QKaBaiEep0WBxJ", "NDW4H8pFTthDV7kmSkdyYDmiBspabYEW",
      "e3lqNSPq1hU6v7FFnq6p6die6pFIYJU0", "admin")
  )
}

class InMemoryPersistence extends Persistence {

  val consumers = ListBuffer.empty[Consumer]
  val requestTokens = ListBuffer.empty[RequestToken]
  val accessTokens = ListBuffer.empty[AccessToken]
  val nonces = ListBuffer.empty[Nonce]

  override def nonceExists(nonce: String, consumerKey: String, token: String): Boolean = {
    nonces.exists(p => nonce == p.nonce && consumerKey == p.consumerKey && token == p.token)
  }

  override def whoAuthorizedRequestToken(consumerKey: String, requestToken: String, verifier: String): Option[String] = {
    requestTokens.find(p => consumerKey == p.consumerKey
      && requestToken == p.requestToken
      && p.verifier.contains(verifier)) match {
      case None => None
      case Some(foundRequestToken) => foundRequestToken.verifierUsername
    }
  }

  override def getCallback(consumerKey: String, requestToken: String) = {
    requestTokens.find(p => consumerKey == p.consumerKey
      && requestToken == p.requestToken).map(_.callback)
  }

  override def getAccessTokenSecret(consumerKey: String, accessToken: String): Option[String] = {
    accessTokens.find(t => consumerKey == t.consumerKey && accessToken == t.accessToken)
      .map(t => t.accessTokenSecret)
  }

  override def persistAccessToken(consumerKey: String, accessToken: String, accessTokenSecret: String, username: String): Unit = {
    accessTokens += AccessToken(consumerKey, accessToken, accessTokenSecret, username)
  }

  override def persistRequestToken(consumerKey: String, requestToken: String, requestTokenSecret: String, callback: String): Unit = {
    requestTokens += RequestToken(consumerKey, requestToken, requestTokenSecret, callback, None, None)
  }

  override def getConsumerSecret(consumerKey: String): Option[String] = {
    consumers.find(c => consumerKey == c.consumerKey)
      .map(c => c.consumerSecret)
  }

  override def getUsername(consumerKey: String, accessToken: String): Option[String] = {
    accessTokens.find(t => consumerKey == t.consumerKey && accessToken == t.accessToken)
      .map(_.username)
  }

  override def getRequestTokenSecret(consumerKey: String, requestToken: String): Option[String] = {
    requestTokens.find(t => consumerKey == t.consumerKey && requestToken == t.requestToken)
      .map(t => t.requestTokenSecret)
  }

  override def persistNonce(nonce: String, consumerKey: String, token: String): Unit = {
    nonces += Nonce(nonce, consumerKey, token)
  }

  override def deleteRequestToken(consumerKey: String, requestToken: String): Unit = {
    val someToken = requestTokens.find(t => consumerKey == t.consumerKey && requestToken == t.requestToken).get
    requestTokens -= someToken
  }
}
