package com.github.cuter44.osamp.as.oauth.model;

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

  // CONSTRUCTORS

    public IssuedToken()
    {
        // NOOP
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
            &&
            (
                (this.id == s.id) ||
                (this.id != null && this.id.equals(s.id))
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
