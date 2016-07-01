package com.github.cuter44.osamp.as.sys.oltu;

import java.util.Map;
import java.io.IOException;
import javax.servlet.http.*;

import org.apache.oltu.oauth2.common.message.OAuthResponse;

public class OAuthResponseTranscriber
{
    public static HttpServletResponse toServletResp(HttpServletResponse resp, OAuthResponse oresp)
        throws IOException
    {
        resp.setStatus(oresp.getResponseStatus());

        Map<String, String> h = oresp.getHeaders();
        for (String k:h.keySet())
            resp.setHeader(k, h.get(k));

        resp.getWriter().write(
            oresp.getBody()
        );

        return(resp);
    }

    public static HttpServletResponse toServletRespJson(HttpServletResponse resp, OAuthResponse oresp)
        throws IOException
    {
        toServletResp(resp, oresp);

        resp.setContentType("application/json");

        return(resp);
    }
}
