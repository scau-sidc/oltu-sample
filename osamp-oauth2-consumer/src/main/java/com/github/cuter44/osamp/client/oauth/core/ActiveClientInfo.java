package com.github.cuter44.osamp.client.oauth.core;

import org.apache.oltu.oauth2.common.domain.client.*;

import com.github.cuter44.osamp.client.Constants;

public class ActiveClientInfo
{
    public BasicClientInfo client;

    private static class Singleton
    {
        public static ActiveClientInfo instance = new ActiveClientInfo().build();
    }

    public static ActiveClientInfo getInstance()
    {
        return(Singleton.instance);
    }

    protected ActiveClientInfo build()
    {
        BasicClientInfo c = new BasicClientInfo();

        c.setName       (Constants.DYMREG_CLIENT_NAME       );
        c.setDescription(Constants.DYMREG_CLIENT_DESCRIPTION);
        c.setIconUri    (Constants.DYMREG_CLIENT_ICON       );
        c.setClientUri  (Constants.DYMREG_CLIENT_URL        );
        c.setRedirectUri(Constants.DYMREG_REDIRECT_URL      );

        this.client = c;

        return(this);
    }
}
