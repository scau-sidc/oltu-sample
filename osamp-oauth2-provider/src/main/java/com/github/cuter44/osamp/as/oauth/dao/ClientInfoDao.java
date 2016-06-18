package com.github.cuter44.osamp.as.oauth.dao;

import java.util.*;

import org.apache.oltu.oauth2.common.domain.client.*;

public class ClientInfoDao
{
  // FIELDS
    public Map<String, ClientInfo> clients;

  // CONSTRUCT
    public ClientInfoDao()
    {
        super();

        this.clients = new HashMap<String, ClientInfo>();

        return;
    }

  // DEFAULT INSTANCE
    private static class DefaultInstance
    {
        public static ClientInfoDao instance = new ClientInfoDao();
    }

    public static ClientInfoDao getInstance()
    {
        return(DefaultInstance.instance);
    }

  // CLIENT INFO
    public synchronized ClientInfo create(ClientInfo client)
    {
        if (this.clients.get(client.getClientId())!=null)
            throw(new IllegalArgumentException("Client id duplicated."));

        this.clients.put(client.getClientId(), client);

        return(client);
    }

    public ClientInfo get(String clientId)
    {
        return(
            this.clients.get(clientId)
        );
    }

    public synchronized ClientInfo remove(String clientId)
    {
        return(
            this.clients.remove(clientId)
        );
    }
    public synchronized ClientInfo remove(ClientInfo client)
    {
        return(
            this.remove(client.getClientId())
        );
    }

}
