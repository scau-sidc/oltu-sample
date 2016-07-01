<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<html>
<body>
  <p>
    使用任意用户名和密码登录.
    <br />
    未记录的用户将会自动创建.
  </p>

  <p>
  <form id="form-user-login">
    <label>Principal<input name="principal" value="mocked-user-consumer"></label>
    <br />
    <label>Credential<input name="credential" type="password" value="P@ssword"></label>
    <br />
    <button type="button" onclick="javascript:ajaxLogin();">Login</button>
  </form>
  </p>

  <p>
    <span id="field-hint"></span>
    <br />
    前往 <a href="usercard.jsp">usercard.jsp →</a>
  </p>

  <script>
    function ajaxLogin(ev)
    {
      var formData = new FormData(document.getElementById("form-user-login"));
      var xhr = new XMLHttpRequest();
      var xhrBody = "nonce=1";

      for (var entry of formData.entries())
        xhrBody = xhrBody+"&"+entry[0]+"="+entry[1];

      xhr.open("POST", "user/login");      
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.addEventListener("load", function(ev){
        var xhr = ev.target;
        var body = JSON.parse(xhr.responseText);
        document.getElementById("field-hint").innerText = "已登录为 "+body.principal;
      })
      xhr.send(xhrBody);
      
      ev && ev.preventDefault();
    }
  
  </script>
</body>
</html>
