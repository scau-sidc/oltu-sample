package com.github.cuter44.osamp.client.oauth.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.*;
import org.apache.oltu.oauth2.common.domain.client.*;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.client.*;
import org.apache.oltu.oauth2.client.request.*;
import org.apache.oltu.oauth2.client.response.*;

import static com.github.cuter44.osamp.client.Constants.*;
import static com.github.cuter44.osamp.client.sys.servlet.Jsonizer.*;
import static com.github.cuter44.osamp.client.sys.oltu.OAuthResponseTranscriber.*;
import com.github.cuter44.osamp.client.oauth.core.*;
import com.github.cuter44.osamp.client.oauth.dao.*;
import com.github.cuter44.osamp.client.oauth.model.*;
import com.github.cuter44.osamp.client.local.model.*;


/** OAuth2 Code Callback Endpoint.
 *
 * <pre style="font-size:12px">

    <strong>请求</strong>
    POST /oauth2/callback

    <strong>参数</strong>
    <i>(none)</i>

    <strong>请求头</strong>
    <i>(none)</i>

    <strong>请求体</strong>
    <i>(none)</i>

    <strong>响应头</strong>

    <strong>响应体</strong>

    <strong>样例</strong>
    暂无
 * </pre>
 *
 */
@WebServlet("/oauth2/callback")
public class OAuth2Callback extends HttpServlet
{
    protected UserTokenDao dao;

    @Override
    public void init()
    {
        this.dao = UserTokenDao.getInstance();

        return;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        try
        {
            String code = req.getParameter(OAuth.OAUTH_CODE);
            if (code == null)
                throw(new IllegalArgumentException("Missing parameter 'code'"));

            BasicClientInfo c = ActiveClientInfo.getInstance().client;

            OAuthClientRequest oreq = OAuthClientRequest
                .tokenLocation(URL_PROVIDER_TOKEN)
                .setCode(code)
                .setClientId(c.getClientId())
                .setClientSecret(c.getClientSecret())
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setRedirectURI(DYMREG_REDIRECT_URL)
                .buildBodyMessage();

            OAuthClient httpClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse oresp = httpClient.accessToken(oreq);

            while (true)
            {
                String state = req.getParameter(OAuth.OAUTH_STATE);

                // SWITCH
                // CASE STATE_BIND
                if (STATE_BIND.equals(state))
                {
                    this.bind(req, resp, oresp);
                    break;
                }

                // CASE STATE_LOGIN
                if (STATE_LOGIN.equals(state))
                {
                    this.login(req, resp, oresp);
                    break;
                }

                throw(new UnsupportedOperationException("Action not supported:state="+state));
            }
        }
        catch (Exception ex)
        {
            req.setAttribute(ATTR_KEY_EXCEPTION, ex);
            req.getRequestDispatcher(URI_ERROR_HANDLER).forward(req, resp);
        }
    }

    public void bind(HttpServletRequest req, HttpServletResponse resp, OAuthJSONAccessTokenResponse oresp)
        throws Exception
    {
        LocalCredential localCred = (LocalCredential) req.getSession().getAttribute(SESS_LOCAL_PRINCIPAL);
        if (localCred == null)
            throw(new IllegalStateException("Require local credential login."));

        UserToken t = toUserToken(localCred, oresp);

        this.dao.create(t);

        resp.sendRedirect(URL_REDIRECT_BIND);

        return;
    }

    public void login(HttpServletRequest req, HttpServletResponse resp, OAuthJSONAccessTokenResponse oresp)
        throws Exception
    {
        UserToken t = this.dao.forAccessToken(oresp.getAccessToken());

        if (t == null)
            throw(new IllegalArgumentException("Invalid access_token:"+t.getAccessToken()));

        req.getSession().setAttribute(SESS_LOCAL_PRINCIPAL, t.getLocalCred());

        resp.sendRedirect(URL_REDIRECT_LOGIN);

        return;
    }
}