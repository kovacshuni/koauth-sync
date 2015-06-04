# KOAuth Sync - OAuth 1.0a Provider & Consumer Library in Scala

**Same as [koauth](https://github.com/kovacshuni/koauth) but not in asynchronous mode.
All the methods are sync calls. All outputs are direct types, not Futures.**

This library aids calculations according to the [OAuth 1.0a](http://oauth.net/core/1.0a/)
specifications for both HTTP server and client.

* Provider library: Verifying and responding to HTTP requests according to specifications.
* Consumer library: Complementing HTTP requests to be sent with OAuth parameters.

See the rest of the README in [the original project](https://github.com/kovacshuni/koauth).

```
<dependency>
    <groupId>com.hunorkovacs</groupId>
    <artifactId>koauth-sync_2.11</artifactId>
    <version>1.1.0</version>
</dependency>
```

or

```
resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "com.hunorkovacs" %% "koauth-sync" % "1.1.0"
```

Versions are matched to koauth's versions.

Hunor Kovács
kovacshuni@yahoo.com  
[hunorkovacs.com](http://www.hunorkovacs.com)  
Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0) .
