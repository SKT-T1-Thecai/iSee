package com.cv.component;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@Component
//@WebFilter("/*")
public class LoginFilter implements Filter
{
    public LoginFilter()
    {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String []  allowDomain= {"http://10.136.183.208:8080","http://10.251.254.104:80","http://10.191.120.156:8080","http://10.136.186.170:8080","http://localhost:8080"};
        Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(allowDomain));
        String originHeader=((HttpServletRequest) servletRequest).getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");
            httpResponse.addHeader("Access-Control-Allow-Origin", originHeader);
            httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        }
        String url = httpRequest.getRequestURI();
        System.out.println(url);
        if (true)
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            Object o = httpRequest.getSession().getAttribute("uid");
            if (null != o)
            {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                httpResponse.sendRedirect("http://localhost:80/home");
            }
        }
    }
}
