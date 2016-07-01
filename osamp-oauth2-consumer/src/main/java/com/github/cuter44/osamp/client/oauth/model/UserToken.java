package com.github.cuter44.osamp.client.oauth.model;

import java.util.Set;
import java.util.HashSet;

import org.apache.oltu.oauth2.common.token.*;

import com.github.cuter44.osamp.client.local.model.*;



public class UserToken extends BasicOAuthToken
{
  // FIELDS
    public Long id;

    public LocalCredential localCred;
    public String remotePrincipal;

    public Long issuedAt;
    public Set<String> scopes;

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

    public String getRemotePrincipal()
    {
        return(this.remotePrincipal);
    }

    public void setRemotePrincipal(String remotePrincipal)
    {
        this.remotePrincipal = remotePrincipal;

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

  // CONSTRUCTORS

    public UserToken()
    {
        this.scopes = new HashSet<String>();

        return;
    }

    public UserToken(LocalCredential localCred, String remotePrincipal)
    {
        this();

        this.setLocalCred(localCred);
        this.setRemotePrincipal(remotePrincipal);

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
        hash = prime * hash + ((remotePrincipal == null) ? 0 : remotePrincipal.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o==null || !this.getClass().equals(o.getClass()))
            return false;

        UserToken s = (UserToken) o;

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
                (this.remotePrincipal == s.remotePrincipal) ||
                (this.remotePrincipal != null && this.remotePrincipal.equals(s.remotePrincipal))
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


