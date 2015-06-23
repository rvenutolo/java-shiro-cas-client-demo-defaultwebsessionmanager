Java webapp protected by the Shiro CAS client (http://shiro.apache.org)
==

Maven demo using the CAS client from the Shiro project (v1.2.3) to protect a web application.

Use **mvn clean compile jetty:run** to start the webapp on **http://localhost:8080**. The url 'protected/index.jsp' is protected and should trigger a CAS authentication.

Most of the configuration is defined in the **src/main/resources/shiro.ini** file.

A specific logout application url is available at: http://localhost:8080/logout.

Run your CAS server on https://localhost:8888/cas.

***

Fork info
---

I have created this fork to demonstrate a problem I have.

### Problem Description
I am adding in CAS SSO support to a number web applications that make use of Shiro. These applications all set the session manager to an instance of [DefaultWebSessionManager](https://shiro.apache.org/static/1.2.3/apidocs/org/apache/shiro/web/session/mgt/DefaultWebSessionManager.html). This is done to make use of a [SessionListener](https://shiro.apache.org/static/1.2.3/apidocs/org/apache/shiro/session/SessionListener.html) (see [setSessionListeners](https://shiro.apache.org/static/1.2.3/apidocs/org/apache/shiro/session/mgt/AbstractNativeSessionManager.html#setSessionListeners%28java.util.Collection%29)) to log session terminations and expirations. If one does not set the session manager, the security manager uses an instance of [ServletContainerSessionManager](http://shiro.apache.org/static/1.2.3/apidocs/org/apache/shiro/web/session/mgt/ServletContainerSessionManager.html).

When using ServletContainerSessionManager (i.e. not specifying a session manager), things works as expected. However, when using a DefaultWebSessionManager, I encounter a problem after authenticating where I get what I can best describe as endless redirects.

I am inexperienced with Shiro and CAS, so I'm not sure what is going on. My best guess is that it has something to do with the ServletContainerSessionManager working with HTTP sessions, while the DefaultWebSessionManager works with Shiro native sessions, but I am in no way confident that is a sufficient, or even correct, explanation.

### Problem Demonstration
##### Edit **src/main/resources/shiro.ini** according to the following steps:
* Comment out the block of text that involves the session manager and session listener. Run the application and everything should function as expected.
* Un-comment out the previously mentioned block of text. Run the application and after clicking on the protected tag and authenticating, things should go haywire.
* Un-comment out the block of text where a cookie is defined and the session manager's session id cookie is set to that cookie. Run the application and logging in should work as desired, however logout will not.
* Un-comment out the block of text where the cookie's secure and httpOnly flags are set. Run the application and logging in and out should work, however clicking on the protected tab will redirect to /, and clicking on the non-protected tab will not display the logged-in principal.
