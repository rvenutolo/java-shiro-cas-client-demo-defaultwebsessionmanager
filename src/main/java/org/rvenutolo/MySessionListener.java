package org.rvenutolo;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySessionListener implements SessionListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStart(final Session session) {
        logger.info("session start: {}", session);
    }

    @Override
    public void onStop(final Session session) {
        logger.info("session stop: {}", session);
    }

    @Override
    public void onExpiration(final Session session) {
        logger.info("session expiration: {}", session);
    }

}
