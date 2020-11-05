package com.example.shop.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAuthenticationException extends AuthenticationException {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final RestError error;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final transient Map<String, Object> contexts = new HashMap<>();

    @JsonIgnore
    private final transient Object[] messageParams;

    public UserAuthenticationException(RestError error) {
        this(error, "");
    }

    public UserAuthenticationException(RestError error, String exceptionMessage) {
        super(exceptionMessage);
        this.error = error;
        this.messageParams = new Object[]{};
    }

    public UserAuthenticationException(RestError error, String exceptionMessage, List<Object> messageParams) {
        super(exceptionMessage);
        this.error = error;
        this.messageParams = messageParams.toArray();
    }

    public RestError getError() {
        return error;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }

    public Map<String, Object> getContexts() {
        return contexts;
    }

    public UserAuthenticationException addContext(String key, Object value) {
        contexts.put(key, value);
        return this;
    }
}
