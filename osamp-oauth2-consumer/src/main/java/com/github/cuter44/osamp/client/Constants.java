package com.github.cuter44.osamp.client;

public class Constants
{
  // LOCAL PRINCIPAL
    public static final String SESS_LOCAL_PRINCIPAL = "_local_principal";

  // OAUTH
    public static final String DYMREG_CLIENT_NAME           = "OAuth Sample Client";
    public static final String DYMREG_CLIENT_DESCRIPTION    = "OAuth Sample Client";
    public static final String DYMREG_CLIENT_ICON   = "http://localhost:8080/osamp-consumer/asset/client_icon.png";
    public static final String DYMREG_CLIENT_URL    = "http://localhost:8080/osamp-consumer/index.jsp";
    public static final String DYMREG_REDIRECT_URL  = "http://localhost:8080/osamp-consumer/oauth2/callback";
    public static final String DYMREG_TYPE          = "push";

    public static final String DYMREG_PROVIDER_URL  = "http://localhost:8080/osamp-provider/oauth2/client/reg";

    public static final String URL_PROVIDER_AUTH    = "http://localhost:8080/osamp-provider/auth.jsp";
    public static final String URL_PROVIDER_TOKEN   = "http://localhost:8080/osamp-provider/oauth2/token";

    public static final String STATE_BIND           = "bind";
    public static final String URL_REDIRECT_BIND    = "http://localhost:8080/osamp-consumer/index.jsp";
    public static final String STATE_LOGIN          = "login";
    public static final String URL_REDIRECT_LOGIN   = "http://localhost:8080/osamp-consumer/index.jsp";


    // Client secret length, in bytes.
    //public static final int CLIENT_SECRET_LENGTH = 8;
    // Client expiration, in ms.
    //public static final long CLIENT_EXPIRES_IN = 51840000000L;
    // Token expires in, in ms
    //public static final long TOKEN_EXPIRES_IN = 8640000000L;

  // ERROR HANDLING
    public static final String URI_ERROR_HANDLER = "/sys/exception";
    public static final String ATTR_KEY_EXCEPTION = "_sys_exception";

}
