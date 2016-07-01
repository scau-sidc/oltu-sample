package com.github.cuter44.osamp.client.sys.servlet;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.io.IOException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.*;
import javax.servlet.http.*;

import com.alibaba.fastjson.*;
import com.github.cuter44.nyafx.fastjson.*;

/** Helper class for serialize to json, with role-based properties filter.
 */
public class Jsonizer
{
    public static final Map<String, JSONObject> HINTS = new HashMap<String, JSONObject>();
    public static final JSONBuilder JSON_BUILDER = new JSONBuilder();

    static {
        // TODO init hints
    }

    public static void write(HttpServletResponse resp, int status, JSON json)
        throws IOException
    {
        if (json == null)
        {
            resp.setStatus(404);
            return;
        }

        resp.setStatus(status);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(json);

        return;
    }

    public static void write(HttpServletResponse resp, JSON json)
        throws IOException
    {
        write(resp, 200, json);

        return;
    }

    public static JSONObject jsonizeObject(JSONObject base, Object o, String hintName)
        throws IllegalAccessException, InvocationTargetException
    {
        JSONObject hint = hintName!=null ? HINTS.get(hintName) : null;

        return(
            JSON_BUILDER.jsonizeObject(base, o, hint)
        );
    }

    public static JSONObject jsonizeObject(Object o, String hintName)
        throws IllegalAccessException, InvocationTargetException
    {
        return(
            jsonizeObject(null, o, hintName)
        );
    }

    public static JSONArray jsonizeCollection(JSONArray base, Collection o, String hintName)
        throws IllegalAccessException, InvocationTargetException
    {
        JSONObject hint = hintName!=null ? HINTS.get(hintName) : null;

        return(
            JSON_BUILDER.jsonizeCollection(base, o, hint)
        );
    }

    public static JSONArray jsonizeCollection(Collection o, String hintName)
        throws IllegalAccessException, InvocationTargetException
    {
        return(
            jsonizeCollection(null, o, hintName)
        );
    }
}
