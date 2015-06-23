package org.rvenutolo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class MyFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Collection<String> requestURIs = new ArrayList<>();

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String requestURI = httpRequest.getRequestURI();
        synchronized (requestURIs) {
            requestURIs.add(requestURI);
            logger.info("Request URIs: ");
            int index = 1;
            for (final String uri : requestURIs) {
                logger.info("{}: {}", index, uri);
                index++;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
