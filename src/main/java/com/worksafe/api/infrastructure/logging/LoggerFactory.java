package com.worksafe.api.infrastructure.logging;

import com.worksafe.api.domain.logging.Logger;

public final class LoggerFactory {
    private LoggerFactory() {
        super();
    }

    public static Logger getLogger(Class<?> clazz) {
        return new LoggerImpl(clazz);
    }
}
