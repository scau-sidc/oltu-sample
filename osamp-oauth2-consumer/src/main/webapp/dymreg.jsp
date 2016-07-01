<%@ page contentType="text/html;charset=utf-8" 
  import="
    com.github.cuter44.osamp.client.Constants,
    org.apache.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration.Request
  "
%>
<html>
<body>
  <h1>OAuth client 注册参数</h1>
  <p>
    用于支持 dynamic registration 的请求体.
  </p>
  <p>
    <textarea id="req-body" readonly style="user-select:true; width:640px; height:180px;"><%
      out.println(
        "{"+"\n"+
          "  \""+Request.CLIENT_NAME        +"\":"+"\""+Constants.DYMREG_CLIENT_NAME        +"\""+","+"\n"+
          "  \""+Request.CLIENT_DESCRIPTION +"\":"+"\""+Constants.DYMREG_CLIENT_DESCRIPTION +"\""+","+"\n"+
          "  \""+Request.CLIENT_ICON        +"\":"+"\""+Constants.DYMREG_CLIENT_ICON        +"\""+","+"\n"+
          "  \""+Request.CLIENT_URL         +"\":"+"\""+Constants.DYMREG_CLIENT_URL         +"\""+","+"\n"+
          "  \""+Request.REDIRECT_URL       +"\":"+"\""+Constants.DYMREG_REDIRECT_URL       +"\""+","+"\n"+
          "  \""+Request.TYPE               +"\":"+"\""+Constants.DYMREG_TYPE               +"\""    +"\n"+
        "}"
      );

    %></textarea>
  </p>
  <p>
    向 provider 的注册接口发送请求以注册 client. 可以自行完成此工作, 或单击以下按钮自动完成:
    <br />
    正确执行后应返回包含 client_id 与 client_secret 的数据.
  </p>
  <div>
    <iframe src="do-dymreg.jsp" width="100%" height="480px"></iframe>
  </div>
</body>
</html>
