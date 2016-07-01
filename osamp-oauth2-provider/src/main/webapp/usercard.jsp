<%@ page contentType="text/html; charset=UTF-8"
  import="
    com.github.cuter44.osamp.as.Constants,
    com.github.cuter44.osamp.as.local.model.LocalCredential
  "
%>
<html>
<body>
  <p>
    <%
      LocalCredential localCred = (LocalCredential)session.getAttribute(Constants.SESS_LOCAL_PRINCIPAL);

      if (localCred != null)
        out.println("已登录为 <br /><img src=\"asset/user_avatar.png\" height=\"64px\">" + localCred.getPrincipal());
      else
        out.println("Provider 本地帐户未登录.");
    %>
  </p>
  <p>
    前往 <a href="login.jsp">login.jsp →</a>
  </p>
</body>
</html>
