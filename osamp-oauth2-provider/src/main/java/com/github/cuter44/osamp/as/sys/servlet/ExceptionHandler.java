package com.github.cuter44.osamp.as.sys.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Map;
import java.util.HashMap;

import static com.github.cuter44.osamp.as.Constants.*;

/** The Error Parser.
 *
 * <pre style="font-size:12px">

   <strong>请求</strong>
   /sys/exception
   <i>Accepts only forwarded requests.</i>

   <strong>响应头</strong>
   (status code)=<i>Vary according concrete error type</i>

   <strong>响应体</strong>
   <i>application/json</i>
   instance of:
       error    :string, not-null   , the error type;
       msg      :string             , the error message;
       msgl10n  :string             , the localized(zh-CN) error message, only available for end-user oriented errors;
   <i>Possible values can be found in development documentations (see appendix in api-list)</i>

   <strong>异常</strong>
   While thing goes well, return <code>{"error":"ok", "msg":"ok", "msgl10n":"(´☉ ∀ ☉)ﾉ ?"}</code>

 * </pre>
 */
@WebServlet("/sys/exception")
public class ExceptionHandler extends HttpServlet
{
    protected Map<Class, Integer> errorCodes;

    @Override
    public void init()
    {
        // REGISTER ERROR CODES
        this.errorCodes = new HashMap<Class, Integer>();

        return;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws IOException
    {
        Exception ex = (Exception)req.getAttribute("exception");

        int errorCode = 200;
        String error = "ok";
        String msg = "ok";
        String msgl10n = "(´☉ ∀ ☉)ﾉ ?";

        if (ex != null)
        {
            this.getServletContext().log("", ex);

            Class clazz = ex.getClass();
            Integer registeredErrorCode = this.errorCodes.get(clazz);

            errorCode = registeredErrorCode!=null ? registeredErrorCode : 500 ;
            error = clazz.getSimpleName();
            msg = ex.getMessage();
            msgl10n = ex.getLocalizedMessage();
        }

        resp.setStatus(errorCode);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().println(
            String.format(
                "{\"error\":\"%s\",\"msg\":\"%s\",\"msgl10n\":\"%s\"}",
                error,
                msg,
                msgl10n
            )
        );

        return;
    }
}
