package com.github.cuter44.osamp.as.oauth.core;

import java.util.*;

import org.apache.oltu.oauth2.common.domain.client.*;
import com.github.cuter44.nyafx.crypto.*;

import static com.github.cuter44.osamp.as.Constants.CLIENT_SECRET_LENGTH;
import static com.github.cuter44.osamp.as.Constants.CLIENT_EXPIRES_IN;
import com.github.cuter44.osamp.as.oauth.dao.*;

public class ClientInfoControl
{
  // FIELDS
    protected ClientInfoDao dao;
    protected CryptoBase    crypto;

  // CONSTRUCT
    public ClientInfoControl()
    {
        super();

        this.dao    = ClientInfoDao.getInstance();
        this.crypto = CryptoBase.getInstance();

        return;
    }

  // DEFAULT INSTANCE
    private static class DefaultInstance
    {
        public static ClientInfoControl instance = new ClientInfoControl();
    }

    public static ClientInfoControl getInstance()
    {
        return(DefaultInstance.instance);
    }

  // CLIENT INFO
    /** @return client
     */
    public BasicClientInfo create(BasicClientInfo client)
    {
        this.generateClientId(client);
        this.generateClientSecret(client);
        client.setIssuedAt(System.currentTimeMillis());
        client.setExpiresIn(CLIENT_EXPIRES_IN);

        this.dao.create(client);

        return(client);
    }

    /** @return client
     */
    public BasicClientInfo generateClientId(BasicClientInfo client)
    {
        try
        {
            String cu = client.getClientUri();
            if (cu == null)
                throw(new IllegalArgumentException("client_uri required but omitted."));

            String ru = client.getRedirectUri();
            if (ru == null)
                throw(new IllegalArgumentException("redirect_uri required but omitted."));

            byte[] b = (cu + ru).getBytes("utf-8");

            String clientId = this.crypto.bytesToHex(
                this.crypto.MD5Digest(b)
            );

            client.setClientId(clientId);

            return(client);
        }
        catch (Exception ex)
        {
            if (ex instanceof RuntimeException)
                throw((RuntimeException)ex);
            // else
            throw(new RuntimeException(ex));
        }
    }

    /** @return client
     */
    public BasicClientInfo generateClientSecret(BasicClientInfo client)
    {
        String clientSecret = this.crypto.bytesToHex(
            this.crypto.randomBytes(CLIENT_SECRET_LENGTH)
        );

        client.setClientSecret(clientSecret);

        return(client);
    }
}
