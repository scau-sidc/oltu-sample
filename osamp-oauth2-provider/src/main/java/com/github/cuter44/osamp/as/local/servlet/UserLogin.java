package com.github.cuter44.osamp.as.oauth.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static com.github.cuter44.nyafx.servlet.ParamsX.*;

import static com.github.cuter44.osamp.as.Constants.*;
import static com.github.cuter44.osamp.as.sys.servlet.Jsonizer.*;
import com.github.cuter44.osamp.as.local.model.*;
import com.github.cuter44.osamp.as.local.dao.*;

/** 本地用户登录.
 *
 * 因为这是个 demo 所以用任何用户名都是可以的, 会自动创建用户.
 * 另外不检查密码
 *
 * <pre style="font-size:12px">

    <strong>请求</strong>
    POST /user/login

    <strong>参数</strong>
    <i>(none)</i>

    <strong>请求头</strong>
    Content-Type: application/x-www-form-urlencoded

    <strong>请求体</strong>
    principal   :string
    passward    :string

    <strong>响应头</strong>
    Content-Type: application/json

    <strong>响应体</strong>
    instance of {@link LocalCredential com.github.cuter44.osamp.as.local.model.LocalCredential}

    <strong>样例</strong>
    暂无
 * </pre>
 *
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet
{
    private static final String PRINCIPAL = "principal";

    protected LocalCredentialDao localCredDao;

    @Override
    public void init()
    {
        this.localCredDao = LocalCredentialDao.getInstance();

        return;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {

        try
        {
            String principal = needString(req, PRINCIPAL);

            LocalCredential cred = this.localCredDao.get(principal);

            req.getSession().setAttribute(SESS_LOCAL_PRINCIPAL, cred);

            write(
                resp,
                jsonizeObject(null, cred, null)
            );
        }
        catch(Exception ex)
        {
            req.setAttribute(ATTR_KEY_EXCEPTION, ex);
            req.getRequestDispatcher(URI_ERROR_HANDLER).forward(req, resp);
        }

    }


}