package com.github.cuter44.osamp.as.local.dao;

import java.util.*;

import com.github.cuter44.osamp.as.local.model.*;

public class LocalCredentialDao
{
  // FIELDS
    public Map<String, LocalCredential> credentials;

  // CONSTRUCT
    public LocalCredentialDao()
    {
        super();

        this.credentials = new HashMap<String, LocalCredential>();

        return;
    }

  // DEFAULT INSTANCE
    private static class DefaultInstance
    {
        public static LocalCredentialDao instance = new LocalCredentialDao();
    }

    public static LocalCredentialDao getInstance()
    {
        return(DefaultInstance.instance);
    }

  // CLIENT INFO
    public synchronized LocalCredential create(LocalCredential cred)
    {
        if (this.credentials.get(cred.getPrincipal())!=null)
            throw(new IllegalArgumentException("Credential principal duplicated."));

        this.credentials.put(cred.getPrincipal(), cred);

        return(cred);
    }

    public LocalCredential get(String principal)
    {
        LocalCredential cred = this.credentials.get(principal);
        if (cred == null)
            cred = this.create(new LocalCredential(principal));

        return(cred);
    }

    public synchronized LocalCredential remove(String principal)
    {
        return(
            this.credentials.remove(principal)
        );
    }
    public synchronized LocalCredential remove(LocalCredential cred)
    {
        return(
            this.remove(cred.getPrincipal())
        );
    }

}
