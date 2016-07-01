package com.github.cuter44.osamp.as.sys.oltu;

import java.util.Set;

import org.apache.oltu.oauth2.common.domain.client.*;
import org.apache.oltu.oauth2.ext.dynamicreg.server.request.*;
import org.apache.oltu.oauth2.as.request.*;
import org.apache.oltu.oauth2.client.request.*;
import org.apache.oltu.oauth2.common.exception.*;

/** Utils providing cast service between models.
 */
public class OAuthRequestTranscriber
{
    public static BasicClientInfo toBasicClientInfo(OAuthServerRegistrationRequest req, BasicClientInfo client)
    {
        client = (client!=null) ? client : new BasicClientInfo();

        client.setDescription(req.getClientDescription() );
        client.setIconUri    (req.getClientIcon()        );
        client.setName       (req.getClientName()        );
        client.setClientUri  (req.getClientUrl()         );
        client.setRedirectUri(req.getRedirectURI()       );

        return(client);
    }

    public static BasicClientInfo toBasicClientInfo(OAuthServerRegistrationRequest req)
    {
        return(
            toBasicClientInfo(req, null)
        );
    }

    public static OAuthClientRequest toClientRequest(OAuthAuthzRequest req, String location)
        throws OAuthSystemException
    {
        Set<String> scopes = req.getScopes();
        StringBuilder sb = new StringBuilder();
        boolean delimiter = false;

        for (String s:scopes)
        {
            if (delimiter)
                delimiter = true;
            else
                sb.append(',');

            sb.append(s);
        }

        OAuthClientRequest oreq = OAuthClientRequest
            .authorizationLocation(location)
                .setClientId(req.getClientId())
                .setRedirectURI(req.getRedirectURI())
                .setResponseType(req.getResponseType())
                .setScope(sb.toString())
                .setState(req.getState())
                .buildQueryMessage();

        return(oreq);
    }
}

