package com.hunorkovacs.koauthsync.service.provider.persistence

import scala.concurrent.{ExecutionContext, Future, Await}

class DoubleWrapPersistence(syncPers: Persistence, ec: ExecutionContext)
  extends com.hunorkovacs.koauth.service.provider.persistence.Persistence {

  implicit private val implicitEc = ec

  override def nonceExists(nonce: String, consumerKey: String, token: String) =
    Future(syncPers.nonceExists(nonce, consumerKey, token))

  override def whoAuthorizedRequestToken(consumerKey: String, requestToken: String, verifier: String) =
    Future(syncPers.whoAuthorizedRequestToken(consumerKey, requestToken, verifier))

  override def getCallback(consumerKey: String, requestToken: String) =
    Future(syncPers.getCallback(consumerKey, requestToken))

  override def getAccessTokenSecret(consumerKey: String, accessToken: String) =
    Future(syncPers.getAccessTokenSecret(consumerKey, accessToken))

  override def persistAccessToken(consumerKey: String, accessToken: String, accessTokenSecret: String, username: String) =
    Future(syncPers.persistAccessToken(consumerKey, accessToken, accessTokenSecret, username))

  override def persistRequestToken(consumerKey: String, requestToken: String, requestTokenSecret: String, callback: String) =
    Future(syncPers.persistRequestToken(consumerKey, requestToken, requestTokenSecret, callback))

  override def getConsumerSecret(consumerKey: String) =
    Future(syncPers.getConsumerSecret(consumerKey))

  override def getUsername(consumerKey: String, accessToken: String) =
    Future(syncPers.getUsername(consumerKey, accessToken))

  override def getRequestTokenSecret(consumerKey: String, requestToken: String) =
    Future(syncPers.getRequestTokenSecret(consumerKey, requestToken))

  override def persistNonce(nonce: String, consumerKey: String, token: String) =
    Future(syncPers.persistNonce(nonce, consumerKey, token))

  override def deleteRequestToken(consumerKey: String, requestToken: String) =
    Future(syncPers.deleteRequestToken(consumerKey, requestToken))
}
