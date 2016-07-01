<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""
  import="
    org.apache.oltu.oauth2.as.request.*,
    org.apache.oltu.oauth2.client.request.*,
    org.apache.oltu.oauth2.common.message.types.ResponseType,
    org.apache.oltu.oauth2.common.domain.client.*,
    org.apache.oltu.oauth2.common.exception.*,
    com.github.cuter44.osamp.as.oauth.model.*,
    com.github.cuter44.osamp.as.oauth.dao.*,
    com.github.cuter44.osamp.as.sys.oltu.*,
    com.github.cuter44.osamp.as.Constants
  "
%>
<%
  OAuthAuthzRequest oreq = new OAuthAuthzRequest(request);

  String oauthResponseType = oreq.getResponseType();
  String clientId = oreq.getClientId();
  ClientInfo client = null;
  String urlProceed = null;
  String urlDecline = null;

  ClientInfoDao clientDao = ClientInfoDao.getInstance();

  switch (ResponseType.valueOf(oauthResponseType.toUpperCase()))
  {
    case CODE:
    {
      client = clientDao.get(clientId);

      if (client == null)
        throw(OAuthProblemException.error("invalid_client_id"));

      urlProceed = OAuthRequestTranscriber.toClientRequest(oreq, Constants.URL_PROCEED_AUTH).getLocationUri();
      // urlDecline

      break;
    }

    default:
        throw(new UnsupportedOperationException("Response type not yet supported:"+oauthResponseType));
  }

%>

<html>
<body>
  <h1>授权访问</h1>

  <div style="float:right">
    <iframe src="usercard.jsp" width="320px" height="220px">
    </iframe>
  </div>

  <div id="main-body">
    <div>
      <p>
        以下应用程序申请访问您的账户:
      </p>
      <div>
        <img src="<%=client.getIconUri()%>"><%=client.getName()%>
      </div>
      <p>且要求获得以下权限:</p>
      <div>
        &lt;scopes&gt;
      </div>
    </div>
    <div style="display:inline-block; border:1px solid;"><a href="<%=urlProceed%>">Proceed</a></div>
    <div style="display:inline-block; border:1px solid;"><a href="<%="todo"%>">Decline</a></div>
  </div>

</body>
</html>
