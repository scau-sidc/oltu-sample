package com.github.cuter44.osamp.as.oauth.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//import org.apache.oltu.oauth2.as.issuer.*;
//import org.apache.oltu.oauth2.as.request.*;
//import org.apache.oltu.oauth2.as.response.*;
import org.apache.oltu.oauth2.common.message.*;
import org.apache.oltu.oauth2.common.exception.*;
import org.apache.oltu.oauth2.ext.dynamicreg.server.request.*;
import org.apache.oltu.oauth2.ext.dynamicreg.server.response.*;
import org.apache.oltu.oauth2.common.domain.client.*;

import static com.github.cuter44.osamp.as.sys.servlet.Jsonizer.*;
import static com.github.cuter44.osamp.as.Constants.*;
import com.github.cuter44.osamp.as.oauth.dao.*;
import com.github.cuter44.osamp.as.oauth.core.*;
import static com.github.cuter44.osamp.as.sys.oltu.OAuthResponseTranscriber.*;
import static com.github.cuter44.osamp.as.sys.oltu.OAuthRequestTranscriber.*;

/** 创建 Consumer/Clients.
 *
 * <pre style="font-size:12px">

    <strong>请求</strong>
    POST /oauth2/client/reg

    <strong>参数</strong>
    <i>(none)</i>

    <strong>请求头</strong>
    Content-Type: application/json

    <strong>请求体</strong>
    <i>(unknown)</i>

    <strong>响应头</strong>
    Content-Type: application/json

    <strong>响应体</strong>
    <i>application/json; charset=utf-8</i>
    array of {@link ShopService.Aggregated com.kakakj.cafune.serv.model.ShopService.Aggregated}

    <strong>样例</strong>
    暂无
 * </pre>
 *
 */
@WebServlet("/oauth2/client/reg")
public class ClientReg extends HttpServlet
{
    //protected OAuthIssuerImpl   oauthIssuer;
    protected ClientInfoDao     clientDao;
    protected ClientInfoControl clientCtl;

    @Override
    public void init()
    {
        //this.oauthIssuer    = new OAuthIssuerImpl(new MD5Generator());
        this.clientDao      = ClientInfoDao.getInstance();
        this.clientCtl      = ClientInfoControl.getInstance();

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
                final JSONHttpServletRequestWrapper jsonWrapper = new JSONHttpServletRequestWrapper(req);
                OAuthServerRegistrationRequest registrationRequest = new OAuthServerRegistrationRequest(jsonWrapper);

                BasicClientInfo i = toBasicClientInfo(registrationRequest);

                i = this.clientCtl.create(i);

                OAuthResponse oresp = OAuthServerRegistrationResponse
                    .status(HttpServletResponse.SC_OK)
                    .setClientId(i.getClientId())
                    .setClientSecret(i.getClientSecret())
                    //.setIssuedAt(Long.toString(i.getIssuedAt()))
                    //.setExpiresIn(i.getExpiresIn())
                    .buildJSONMessage();

                toServletRespJson(resp, oresp);
            }
            catch (OAuthProblemException ex)
            {
                OAuthResponse oresp = OAuthServerRegistrationResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(ex)
                    .buildJSONMessage();

                toServletRespJson(resp, oresp);
            }
        }
        catch (Exception ex)
        {
            req.setAttribute(ATTR_KEY_EXCEPTION, ex);
            req.getRequestDispatcher(URI_ERROR_HANDLER).forward(req, resp);
        }
    }

}