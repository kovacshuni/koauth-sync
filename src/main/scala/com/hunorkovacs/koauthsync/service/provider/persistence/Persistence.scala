package com.hunorkovacs.koauthsync.service.provider.persistence

trait Persistence {

  /**
   * Saved nonces can be deleted after a predefined time passes. Preferably longer or equal
   * to the amount the timestamp verification is tuned to. Delete function is not defined
   * in this trait, as it's not crucial for this library to work. But you could and should
   * clean up nonces once in a while.
   *
   * @return true if the nonce already exists for the given Consumer Key and Token
   */
  def nonceExists(nonce: String,
                  consumerKey: String,
                  token: String): Boolean

  /**
   * Saves a nonce associated to a Request or Access Token.
   */
  def persistNonce(nonce: String,
                   consumerKey: String,
                   token: String): Unit

  /**
   * Save a Request Token with void verifier username and verifier key.
   */
  def persistRequestToken(consumerKey: String,
                          requestToken: String,
                          requestTokenSecret: String,
                          callback: String): Unit

  /**
   * @return Consumer Secret associated to given Consumer Key
   */
  def getConsumerSecret(consumerKey: String): Option[String]

  /**
   * @return The username that authorized this token, otherwise None.
   */
  def whoAuthorizedRequestToken(consumerKey: String,
                                requestToken: String,
                                verifier: String): Option[String]
  /**
   * @return The callback that was registered with this Request Token.
   */
  def getCallback(consumerKey: String, requestToken: String): Option[String]

  /**
   * Saves an Access Token with companion attributes.
   */
  def persistAccessToken(consumerKey: String,
                         accessToken: String,
                         accessTokenSecret: String,
                         username: String): Unit

  /**
   * Deletes a Request Token, usually for deactivating one after an exchange for Access Token.
   */
  def deleteRequestToken(consumerKey: String,
                         requestToken: String): Unit

  /**
   * @return the associated Request Token Secret if Request Token exists. Otherwise None.
   */
  def getRequestTokenSecret(consumerKey: String, requestToken: String): Option[String]

  /**
   * @return the Token Secret. If not found, a None.
   */
  def getAccessTokenSecret(consumerKey: String, accessToken: String): Option[String]

  /**
   * @return the username associated to the token.
   */
  def getUsername(consumerKey: String, accessToken: String): Option[String]
}
