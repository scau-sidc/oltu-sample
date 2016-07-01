package com.github.cuter44.osamp.as.oauth.core;

import java.util.*;

import org.apache.oltu.oauth2.common.domain.client.*;
import org.apache.oltu.oauth2.common.exception.*;
import org.apache.oltu.oauth2.as.issuer.*;
import com.github.cuter44.nyafx.crypto.*;

import static com.github.cuter44.osamp.as.Constants.*;
import com.github.cuter44.osamp.as.oauth.dao.*;
import com.github.cuter44.osamp.as.oauth.model.*;
import com.github.cuter44.osamp.as.local.dao.*;
import com.github.cuter44.osamp.as.local.model.*;

public class AccessTokenControl
{
  // FIELDS
    protected IssuedTokenDao        dao;
    protected LocalCredentialDao    localCredDao;
    protected ClientInfoDao         clientDao;
    protected CryptoBase            crypto;
    protected OAuthIssuerImpl       issuer;

  // CONSTRUCT
    public AccessTokenControl()
    {
        super();

        this.dao            = IssuedTokenDao.getInstance();
        this.localCredDao   = LocalCredentialDao.getInstance();
        this.clientDao      = ClientInfoDao.getInstance();
        this.crypto         = CryptoBase.getInstance();
        this.issuer         = new OAuthIssuerImpl(new MD5Generator());

        return;
    }

  // DEFAULT INSTANCE
    private static class DefaultInstance
    {
        public static AccessTokenControl instance = new AccessTokenControl();
    }

    public static AccessTokenControl getInstance()
    {
        return(DefaultInstance.instance);
    }

  // ACCESS TOKEN
    /** @return client
     */

    public IssuedToken genToken(String principal, String clientId, Set<String> scopes)
        throws OAuthSystemException
    {
        LocalCredential cred = this.localCredDao.get(principal);
        if (cred == null)
            throw(new IllegalArgumentException("Invalid local credential."));

        ClientInfo client = this.clientDao.get(clientId);
        if (client == null)
            throw(new IllegalArgumentException("Invalid clientId."));

        IssuedToken token = new IssuedToken(cred, client);

        token.setAccessToken    (this.issuer.accessToken()          );
        token.setCode           (this.issuer.authorizationCode()    );
        token.setRefreshToken   (this.issuer.refreshToken()         );
        token.setScopes         (scopes                             );
        token.setIssuedAt       (System.currentTimeMillis()/1000L   );
        token.setExpiresIn      (TOKEN_EXPIRES_IN                   );

        this.dao.createToken(token);
        this.dao.createCode(token);

        return(token);
    }

    public IssuedToken authorizationCode(String code)
    {
        IssuedToken token = this.dao.get(code);

        if (token == null)
            throw(new IllegalArgumentException("Invalid code:"+code));

        token.setIssuedAt   (System.currentTimeMillis()/1000L   );
        token.setExpiresIn  (TOKEN_EXPIRES_IN                   );
        token.setValidity   (true                               );

        this.dao.removeCode(code);

        return(token);
    }

}
