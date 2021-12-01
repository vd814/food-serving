package com.swing.foodserving.handler;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS|POST|PUT|PATCH|DELETE)$");
    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/api/*", null);

    @Override
    public boolean matches(HttpServletRequest request) {
    	System.out.println("request.getMethod(): "+request.getMethod());
        if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }
        return !unprotectedMatcher.matches(request);
    }
}