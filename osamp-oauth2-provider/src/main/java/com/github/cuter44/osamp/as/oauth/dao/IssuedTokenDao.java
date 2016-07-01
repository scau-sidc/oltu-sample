package com.github.cuter44.osamp.as.oauth.dao;

import java.util.*;

import org.apache.oltu.oauth2.common.domain.client.*;

import com.github.cuter44.osamp.as.oauth.model.*;
import com.github.cuter44.osamp.as.local.model.*;

public class IssuedTokenDao
{
  // SUBCLASS:OPPONENT
    protected static class Participators
    {
      // FIELDS
        public String clientId;
        public String principal;

      // CONSTRUCTORS
        public Participators(String clientId, String principal)
        {
            super();

            this.clientId = clientId;
            this.principal = principal;

            return;
        }

        public Participators(ClientInfo client, LocalCredential localCred)
        {
            super();

            this.clientId = client.getClientId();
            this.principal = localCred.getPrincipal();

            return;
        }

        public Participators(IssuedToken token)
        {
            super();

            this.clientId = token.getClientInfo().getClientId();
            this.principal = token.getLocalCred().getPrincipal();

            return;
        }

      // EQUALITY
        @Override
        public int hashCode()
        {
            final int prime = 31;
            int hash = 17;

            hash = prime * hash + ((clientId == null) ? 0 : clientId.hashCode());
            hash = prime * hash + ((principal == null) ? 0 : principal.hashCode());

            return hash;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;

            if (o==null || !this.getClass().equals(o.getClass()))
                return false;

            Participators s = (Participators) o;

            return(
                (
                    true
                )
                &&
                (
                    (this.clientId != null && this.clientId.equals(s.clientId))
                )
                &&
                (
                    (this.principal != null && this.principal.equals(s.principal))
                )
            );
        }

      // TO STRING
        @Override
        public String toString()
        {
            return(
                String.format(
                    "<%s,%s>",
                    this.clientId,
                    this.principal
                )
            );
        }
    }

  // FIELDS
    public Map<String, IssuedToken> codes;
    public Map<Participators, IssuedToken> tokens;

  // CONSTRUCT
    public IssuedTokenDao()
    {
        super();

        this.codes = new HashMap<String, IssuedToken>();
        this.tokens = new HashMap<Participators, IssuedToken>();

        return;
    }

  // DEFAULT INSTANCE
    private static class DefaultInstance
    {
        public static IssuedTokenDao instance = new IssuedTokenDao();
    }

    public static IssuedTokenDao getInstance()
    {
        return(DefaultInstance.instance);
    }

  // CLIENT INFO
    public IssuedToken createCode(IssuedToken code)
    {
        if (this.codes.containsKey(code.getCode()))
            throw(new IllegalArgumentException("Code duplicated."));

        this.codes.put(code.getCode(), code);

        return(code);
    }

    public IssuedToken createToken(IssuedToken token)
    {
        Participators op = new Participators(token);

        if (this.tokens.containsKey(op))
            throw(new IllegalArgumentException("Token existed already."));

        this.tokens.put(op, token);

        return(token);
    }

    public IssuedToken get(String code)
    {
        return(
            this.codes.get(code)
        );
    }

    public IssuedToken get(String clientId, String principal)
    {
        return(
            this.tokens.get(new Participators(clientId, principal))
        );
    }

    public void removeCode(String code)
    {
        this.codes.remove(code);

        return;
    }

    //public IssuedToken createToken(IssuedToken token)
    //{
        //Participators op = new Participators(token);

        //if (this.tokens.containsKey(op))
            //throw(new IllegalArgumentException("Token existed already."));

        //this.tokens.put(op, token);

        //return(token);
    //}
}
