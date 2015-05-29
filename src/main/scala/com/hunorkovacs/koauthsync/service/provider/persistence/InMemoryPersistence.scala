package com.hunorkovacs.koauthsync.service.provider.persistence

import com.hunorkovacs.koauth.service.provider.persistence

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

class ExampleMemoryPersistence(ec: ExecutionContext) extends InMemoryPersistence(ec) {

  override protected val asyncPers = new persistence.ExampleMemoryPersistence(ec)
}

class InMemoryPersistence(ec: ExecutionContext) extends Persistence {

  protected val asyncPers = new persistence.InMemoryPersistence(ec)

  override def nonceExists(nonce: String, consumerKey: String, token: String): Boolean =
    Await.result(asyncPers.nonceExists(nonce, consumerKey, token), 2 seconds)

  override def whoAuthorizedRequestToken(consumerKey: String, requestToken: String, verifier: String): Option[String] =
    Await.result(asyncPers.whoAuthorizedRequestToken(consumerKey, requestToken, verifier), 2 seconds)

  override def getCallback(consumerKey: String, requestToken: String) =
    Await.result(asyncPers.getCallback(consumerKey, requestToken), 2 seconds)

  override def getAccessTokenSecret(consumerKey: String, accessToken: String): Option[String] =
    Await.result(asyncPers.getAccessTokenSecret(consumerKey, accessToken), 2 seconds)

  override def persistAccessToken(consumerKey: String, accessToken: String, accessTokenSecret: String, username: String): Unit =
    Await.result(asyncPers.persistAccessToken(consumerKey, accessToken, accessTokenSecret, username), 2 seconds)

  override def persistRequestToken(consumerKey: String, requestToken: String, requestTokenSecret: String, callback: String): Unit =
    Await.result(asyncPers.persistRequestToken(consumerKey, requestToken, requestTokenSecret, callback), 2 seconds)

  override def getConsumerSecret(consumerKey: String): Option[String] =
    Await.result(asyncPers.getConsumerSecret(consumerKey), 2 seconds)

  override def getUsername(consumerKey: String, accessToken: String): Option[String] =
    Await.result(asyncPers.getUsername(consumerKey, accessToken), 2 seconds)

  override def getRequestTokenSecret(consumerKey: String, requestToken: String): Option[String] =
    Await.result(asyncPers.getRequestTokenSecret(consumerKey, requestToken), 2 seconds)

  override def persistNonce(nonce: String, consumerKey: String, token: String): Unit =
    Await.result(asyncPers.persistNonce(nonce, consumerKey, token), 2 seconds)

  override def deleteRequestToken(consumerKey: String, requestToken: String): Unit =
    Await.result(asyncPers.deleteRequestToken(consumerKey, requestToken), 2 seconds)
}
