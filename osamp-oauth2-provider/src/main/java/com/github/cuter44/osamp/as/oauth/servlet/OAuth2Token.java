package com.github.cuter44.osamp.as.oauth.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static org.apache.oltu.oauth2.common.OAuth.*;
//import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.as.request.*;
import org.apache.oltu.oauth2.as.response.*;
import org.apache.oltu.oauth2.common.message.*;
import org.apache.oltu.oauth2.common.exception.*;

import static com.github.cuter44.osamp.as.Constants.*;
import static com.github.cuter44.osamp.as.sys.oltu.OAuthResponseTranscriber.*;
import com.github.cuter44.osamp.as.oauth.core.*;
import com.github.cuter44.osamp.as.oauth.dao.*;
import com.github.cuter44.osamp.as.oauth.model.*;


/** OAuth2 Access Token Endpoint.
 *
 * 签发 Access Token.
 * POST 为 server flow
 *
 * <pre style="font-size:12px">

    <strong>请求</strong>
    GET/POST /oauth2/token

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
@WebServlet("/oauth2/token")
public class OAuth2Token extends HttpServlet
{
    protected IssuedTokenDao     dao;
    protected AccessTokenControl ctl;

    @Override
    public void init()
    {
        this.dao = IssuedTokenDao.getInstance();
        this.ctl = AccessTokenControl.getInstance();

        return;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        try
        {
            try
            {
                OAuthTokenRequest oreq = new OAuthTokenRequest(req);

                String oauthGrantType = oreq.getGrantType();

                switch (GrantType.valueOf(oauthGrantType.toUpperCase()))
                {
                    case AUTHORIZATION_CODE:
                    {
                        String code = oreq.getCode();

                        IssuedToken token = this.dao.get(code);

                        OAuthResponse oresp = OAuthASResponse
                            .tokenResponse(HttpServletResponse.SC_OK)
                            .setAccessToken(token.getAccessToken())
                            .setRefreshToken(token.getRefreshToken())
                            //.setIssuedAt(token.getIssuedAt())
                            .setExpiresIn(Long.toString(token.getExpiresIn()))
                            .buildJSONMessage();

                        toServletResp(resp, oresp);

                        break;
                    }

                    default:
                        throw(new UnsupportedOperationException("Grant type not yet supported:"+oauthGrantType));
                }

            }
            catch (OAuthProblemException ex)
            {
                final OAuthResponse oresp = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_FOUND)
                        .error(ex)
                        .buildJSONMessage();

                ex.printStackTrace();

                toServletResp(resp, oresp);
            }
        }
        catch (Exception ex)
        {
            req.setAttribute(ATTR_KEY_EXCEPTION, ex);
            req.getRequestDispatcher(URI_ERROR_HANDLER).forward(req, resp);
        }
    }

}