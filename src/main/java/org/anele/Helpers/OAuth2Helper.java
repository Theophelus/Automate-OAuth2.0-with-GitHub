package org.anele.Helpers;

import org.anele.base.DriverFactory;

import java.text.MessageFormat;

public class OAuth2Helper {


    //method to build Auth Url with base url, client id and scope as parameters
    public static StringBuilder buildUrl(String baseUrl,
                                             String clientId,
                                         String scope){

        return new StringBuilder(baseUrl)
                .append("?")
                .append(MessageFormat.format("client_id={0}", clientId))
                .append("&")
                .append(MessageFormat.format("scope={0}", scope));
    }

}
