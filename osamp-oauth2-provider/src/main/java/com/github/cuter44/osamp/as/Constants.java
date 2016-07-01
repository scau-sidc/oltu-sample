package com.github.cuter44.osamp.as;

public class Constants
{
  // LOCAL PRINCIPAL
    public static final String SESS_LOCAL_PRINCIPAL = "_local_principal";

  // OAUTH
    // Client secret length, in bytes.
    public static final int CLIENT_SECRET_LENGTH = 8;
    // Client expiration, in ms.
    public static final long CLIENT_EXPIRES_IN = 51840000000L;
    // Token expires in, in ms
    public static final long TOKEN_EXPIRES_IN = 8640000000L;

    public static final String URL_PROCEED_AUTH = "http://localhost:8080/osamp-provider/oauth2/code";

  // ERROR HANDLING
    public static final String URI_ERROR_HANDLER = "/sys/exception";
    public static final String ATTR_KEY_EXCEPTION = "_sys_exception";


}
