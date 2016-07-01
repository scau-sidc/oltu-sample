package com.github.cuter44.osamp.client.sys.oltu;

import org.apache.oltu.oauth2.common.domain.client.*;
import org.apache.oltu.oauth2.ext.dynamicreg.client.response.*;

import org.apache.oltu.oauth2.client.response.*;
import com.github.cuter44.osamp.client.local.model.*;
import com.github.cuter44.osamp.client.oauth.model.*;

/** Utils providing cast service between models.
 */
public class OAuthResponseTranscriber
{
    public static BasicClientInfo toBasicClientInfo(BasicClientInfo client, OAuthClientRegistrationResponse resp)
    {
        client = (client!=null) ? client : new BasicClientInfo();

        client.setClientId      (resp.getClientId()     );
        client.setClientSecret  (resp.getClientSecret() );

        return(client);
    }

    public static BasicClientInfo toBasicClientInfo(OAuthClientRegistrationResponse resp)
    {
        return(
            toBasicClientInfo(null, resp)
        );
    }

    public static UserToken toUserToken(UserToken t, LocalCredential localCred, OAuthAccessTokenResponse resp)
    {
        t = (t!=null) ? t : new UserToken();

        t.setLocalCred      (localCred              );

        t.setAccessToken    (resp.getAccessToken()  );
        t.setRefreshToken   (resp.getRefreshToken() );
        t.setExpiresIn      (resp.getExpiresIn()    );

        return(t);
    }

    public static UserToken toUserToken(LocalCredential localCred, OAuthAccessTokenResponse resp)
    {
        return(
            toUserToken(null, localCred, resp)
        );
    }
}

