<%@ page contentType="text/html;charset=utf-8" 
  import="
    org.apache.oltu.oauth2.common.domain.client.ClientInfo,
    org.apache.oltu.oauth2.common.message.types.ResponseType,
    org.apache.oltu.oauth2.client.request.*,
    com.github.cuter44.osamp.client.Constants,
    com.github.cuter44.osamp.client.oauth.core.*
  "
%>
<%
  ClientInfo client = ActiveClientInfo.getInstance().client;

  OAuthClientRequest oreq = OAuthClientRequest
    .authorizationLocation(Constants.URL_PROVIDER_AUTH)
      .setClientId(client.getClientId())
      .setRedirectURI(client.getRedirectUri())
      .setResponseType(ResponseType.CODE.toString())
      .setState(Constants.STATE_LOGIN)
      .buildQueryMessage();
%>

<html>
<body>
  <p>
    Using
    <br />
    <code>
    URL_PROVIDER_AUTH=<%=Constants.URL_PROVIDER_AUTH%>
    <br />
    client_id=<%=client.getClientId()%>
    <br />
    redirect_uri=<%=client.getRedirectUri()%>
    <br />
    response_type=code
    <br />
    scopes=
    <br />
    state=<%=Constants.STATE_LOGIN%>
    </code>
  </p>
  <div style="display:inline-block; border:1px solid;"><a href="<%=oreq.getLocationUri()%>">Proceed Login</a></div>
</body>
</html>
