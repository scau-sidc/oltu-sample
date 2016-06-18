package com.github.cuter44.osamp.as.oauth.dao;

import org.apache.oltu.oauth2.common.domain.client.*;
import org.apache.oltu.oauth2.ext.dynamicreg.server.request.*;

/** Utils providing cast service between models.
 */
public class Cast
{
    public static BasicClientInfo toBasicClientInfo(OAuthServerRegistrationRequest req)
    {
        BasicClientInfo i = new BasicClientInfo();

        i.setDescription(req.getClientDescription() );
        i.setIconUri    (req.getClientIcon()        );
        i.setName       (req.getClientName()        );
        i.setClientUri  (req.getClientUrl()         );
        i.setRedirectUri(req.getRedirectURI()       );

        return(i);
    }
}

