package com.github.cuter44.osamp.as.oauth.model;

import java.util.Set;
import java.util.HashSet;

import org.apache.oltu.oauth2.common.token.*;
import org.apache.oltu.oauth2.common.domain.client.*;
import com.github.cuter44.osamp.as.local.model.*;

public class IssuedToken extends BasicOAuthToken
{
  // FIELDS
	public Long id;

	public LocalCredential localCred;
    public ClientInfo clientInfo;

    public String code;
    public Set<String> scopes;

    public Boolean validity;
    public Long issuedAt;


  // ACCESSORS
    public Long getId()
    {
        return(this.id);
    }

    public void setId(Long id)
    {
        this.id = id;

        return;
    }

    public LocalCredential getLocalCred()
    {
        return(this.localCred);
    }

    public void setLocalCred(LocalCredential localCred)
    {
        this.localCred = localCred;

        return;
    }

    public ClientInfo getClientInfo()
    {
        return(this.clientInfo);
    }

    public void setClientInfo(ClientInfo clientInfo)
    {
        this.clientInfo = clientInfo;

        return;
    }

    public String getCode()
    {
        return(this.code);
    }

    public void setCode(String code)
    {
        this.code = code;

        return;
    }

    public Set<String> getScopes()
    {
        return(this.scopes);
    }

    public void setScopes(Set<String> scopes)
    {
        this.scopes = scopes;

        return;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return;
    }

    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;

        return;
    }

    public void setExpiresIn(Long expiresIn)
    {
        this.expiresIn = expiresIn;

        return;
    }

    public Long getIssuedAt()
    {
        return(this.issuedAt);
    }

    public void setIssuedAt(Long issuedAt)
    {
        this.issuedAt = issuedAt;

        return;
    }

    public Boolean getValidity()
    {
        return(this.validity);
    }

    public void setValidity(Boolean validity)
    {
        this.validity = validity;

        return;
    }


  // CONSTRUCTORS

    public IssuedToken()
    {
        this.validity = false;
        this.scopes = new HashSet<String>();

        return;
    }

    public IssuedToken(LocalCredential localCred, ClientInfo clientInfo)
    {
        this();

        this.setLocalCred(localCred);
        this.setClientInfo(clientInfo);

        return;
    }

  // EQUALITY
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int hash = 17;

        hash = prime * hash + ((id == null) ? 0 : id.hashCode());
        hash = prime * hash + ((accessToken == null) ? 0 : accessToken.hashCode());
        hash = prime * hash + ((localCred == null) ? 0 : localCred.hashCode());
        hash = prime * hash + ((clientInfo == null) ? 0 : clientInfo.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o==null || !this.getClass().equals(o.getClass()))
            return false;

        IssuedToken s = (IssuedToken) o;

        return(
            (
                true
            )
            //&&
            //(
                //(this.id == s.id) ||
                //(this.id != null && this.id.equals(s.id))
            //)
            &&
            (
                (this.accessToken == s.accessToken) ||
                (this.accessToken != null && this.accessToken.equals(s.accessToken))
            )
            &&
            (
                (this.localCred == s.localCred) ||
                (this.localCred != null && this.localCred.equals(s.localCred))
            )
            &&
            (
                (this.clientInfo == s.clientInfo) ||
                (this.clientInfo != null && this.clientInfo.equals(s.clientInfo))
            )
        );
    }

  // TO STRING
    @Override
    public String toString()
    {
        return(
            String.format(
                "%s#%d",
                this.getClass().getSimpleName(),
                this.getId()
            )
        );
    }
}
