package com.github.eliflores.tools.exception;

public final class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static MyLittleToolsException createException(String message, Throwable cause) {
        return new MyLittleToolsException(message, cause);
    }
}
