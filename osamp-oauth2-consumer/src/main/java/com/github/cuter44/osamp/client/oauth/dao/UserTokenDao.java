package com.github.cuter44.osamp.client.oauth.dao;

import java.util.*;

import org.apache.oltu.oauth2.common.domain.client.*;

import com.github.cuter44.osamp.client.oauth.model.*;

public class UserTokenDao
{
  // FIELDS
    public Map<String, UserToken> tokensp;
    public Map<String, UserToken> tokensa;

  // CONSTRUCT
    public UserTokenDao()
    {
        super();

        this.tokensp = new HashMap<String, UserToken>();
        this.tokensa = new HashMap<String, UserToken>();

        return;
    }

  // DEFAULT INSTANCE
    private static class DefaultInstance
    {
        public static UserTokenDao instance = new UserTokenDao();
    }

    public static UserTokenDao getInstance()
    {
        return(DefaultInstance.instance);
    }

  // CLIENT INFO
    public UserToken create(UserToken token)
    {
        this.tokensp.put(token.getLocalCred().getPrincipal(), token);
        this.tokensa.put(token.getAccessToken(), token);

        return(token);
    }


    public UserToken forPrincipal(String principal)
    {
        return(
            this.tokensp.get(principal)
        );
    }

    public UserToken forAccessToken(String accessToken)
    {
        return(
            this.tokensa.get(accessToken)
        );
    }

}
