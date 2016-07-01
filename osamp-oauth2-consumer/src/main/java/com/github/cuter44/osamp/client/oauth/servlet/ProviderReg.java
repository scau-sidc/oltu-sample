package com.github.cuter44.osamp.client.oauth.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.oltu.oauth2.common.exception.*;
import org.apache.oltu.oauth2.common.domain.client.*;
import org.apache.oltu.oauth2.client.*;
import org.apache.oltu.oauth2.client.request.*;
//import org.apache.oltu.oauth2.client.response.*;
import org.apache.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;
import org.apache.oltu.oauth2.ext.dynamicreg.client.*;
import org.apache.oltu.oauth2.ext.dynamicreg.client.request.*;
import org.apache.oltu.oauth2.ext.dynamicreg.client.response.*;

import static com.github.cuter44.osamp.client.Constants.*;
import static com.github.cuter44.osamp.client.sys.servlet.Jsonizer.*;
import static com.github.cuter44.osamp.client.sys.oltu.OAuthResponseTranscriber.*;
import com.github.cuter44.osamp.client.oauth.core.*;
import com.github.cuter44.osamp.client.oauth.model.*;


/** OAuth2 Dynamic Registration Trigger.
 *
 * 使用此触发器向 OAuth provider 注册自身.
 *
 * <pre style="font-size:12px">

    <strong>请求</strong>
    POST /oauth2/provider/reg

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
@WebServlet("/oauth2/provider/reg")
public class ProviderReg extends HttpServlet
{
    @Override
    public void init()
    {
        return;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        try
        {
            BasicClientInfo c = ActiveClientInfo.getInstance().client;

            OAuthClientRequest oreq = OAuthClientRegistrationRequest
                .location(DYMREG_PROVIDER_URL, OAuthRegistration.Type.PUSH)
                .setName(c.getName())
                .setDescription(c.getDescription())
                .setIcon(c.getIconUri())
                .setUrl(c.getClientUri())
                .setRedirectURL(c.getRedirectUri())
                .buildJSONMessage();

            OAuthRegistrationClient httpClient = new OAuthRegistrationClient(new URLConnectionClient());
            OAuthClientRegistrationResponse oresp = httpClient.clientInfo(oreq);

            toBasicClientInfo(c, oresp);

            write(
                resp,
                jsonizeObject(null, c, null)
            );
        }
        catch (Exception ex)
        {
            req.setAttribute(ATTR_KEY_EXCEPTION, ex);
            req.getRequestDispatcher(URI_ERROR_HANDLER).forward(req, resp);
        }
    }

}