package com.github.eliflores.tools.exception;

public final class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static LittleToolsException createException(String message, Throwable cause) {
        return new LittleToolsException(message, cause);
    }
}
