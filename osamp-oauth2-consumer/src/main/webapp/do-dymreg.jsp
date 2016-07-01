<%@ page contentType="text/html;charset=utf-8" 
  import="com.github.cuter44.osamp.client.Constants;"
%>

<html>
<body>
  <p>
    Using
    <br />
    <code>DYMREG_PROVIDER_URL=<%=Constants.DYMREG_PROVIDER_URL%></code>
  </p>
  <form method="POST" action="./oauth2/provider/reg">
    <button type="submit">Proceed Dynamic Registration</button>
  </form>
</body>
</html>
