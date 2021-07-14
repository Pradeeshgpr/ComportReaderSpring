package com.artwiz.comport.reader.util;

public class HttpRequestUtils {

    public static String getStaticPageLocation(String name) {
        return "/static/err/" + name;
    }

    public static String getDynamicPageLocation(String name) {
        return "/view/" + name + ".jsp";
    }

    public static String get404ErrorPage() {
        return getStaticPageLocation("err404");
    }

}
