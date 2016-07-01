package com.github.cuter44.osamp.as.oauth.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.as.request.*;
import org.apache.oltu.oauth2.as.response.*;
import org.apache.oltu.oauth2.common.message.*;
import org.apache.oltu.oauth2.common.exception.*;

import static com.github.cuter44.osamp.as.Constants.*;
import com.github.cuter44.osamp.as.oauth.core.*;
import com.github.cuter44.osamp.as.oauth.dao.*;
import com.github.cuter44.osamp.as.oauth.model.*;
import com.github.cuter44.osamp.as.local.model.*;


/** OAuth2 Code Endpoint.
 *
 * 用户在同意授予资源访问权限时向此接口递交请求,
 *
 * <pre style="font-size:12px">

    <strong>请求</strong>
    GET /oauth2/code

    <strong>参数</strong>
    <i>(none)</i>

    <strong>请求头</strong>
    <i>(none)</i>

    <strong>请求体</strong>
    <i>(none)</i>

    <strong>响应头</strong>
    302 Found
    Location: $redirect_uri

    <strong>响应体</strong>
    <i>application/json; charset=utf-8</i>
    array of {@link ShopService.Aggregated com.kakakj.cafune.serv.model.ShopService.Aggregated}

    <strong>样例</strong>
    暂无
 * </pre>
 *
 */
@WebServlet("/oauth2/code")
public class OAuth2Code extends HttpServlet
{
    protected AccessTokenControl ctl;
    protected IssuedTokenDao dao;

    @Override
    public void init()
    {
        this.ctl = AccessTokenControl.getInstance();
        this.dao = IssuedTokenDao.getInstance();

        return;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        try
        {
            try
            {
                //dynamically recognize an OAuth profile based on request characteristic (params,
                // method, content type etc.), perform validation
                OAuthAuthzRequest oreq = new OAuthAuthzRequest(req);

                String oauthResponseType = oreq.getResponseType();

                switch (ResponseType.valueOf(oauthResponseType.toUpperCase()))
                {
                    case CODE:
                    {
                        LocalCredential localCred = (LocalCredential) req.getSession().getAttribute(SESS_LOCAL_PRINCIPAL);

                        if (localCred == null)
                            throw(new IllegalStateException("Required local login."));

                        IssuedToken token;

                        token = this.dao.get(oreq.getClientId(), localCred.getPrincipal());
                        if (token == null)
                        {
                            token = this.ctl.genToken(
                                localCred.getPrincipal(),
                                oreq.getClientId(),
                                oreq.getScopes()
                            );
                        }

                        OAuthResponse oresp = OAuthASResponse
                            .authorizationResponse(req, HttpServletResponse.SC_FOUND)
                                .location(oreq.getRedirectURI())
                                .setCode(token.getCode())
                                .setParam(OAuth.OAUTH_STATE, oreq.getState())
                                .buildQueryMessage();

                        resp.sendRedirect(oresp.getLocationUri());

                        break;
                    }

                    default:
                        throw(new UnsupportedOperationException("Response type not yet supported:"+oauthResponseType));
                }
            }
            catch (OAuthProblemException ex)
            {
                String location = req.getParameter(OAuth.OAUTH_REDIRECT_URI);
                if (location == null)
                    throw(ex);

                final OAuthResponse oresp = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_FOUND)
                        .error(ex)
                        .location(location)
                        .buildQueryMessage();

                resp.sendRedirect(oresp.getLocationUri());
            }
        }
        catch (Exception ex)
        {
            req.setAttribute(ATTR_KEY_EXCEPTION, ex);
            req.getRequestDispatcher(URI_ERROR_HANDLER).forward(req, resp);
        }
    }

}