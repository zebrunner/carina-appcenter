package com.zebrunner.carina.appcenter.client;

/**
 * API response returned by API call.
 */
public class ApiResponse<T> {
    private final T data;

    /**
     * <p>Constructor for ApiResponse.</p>
     *
     * @param data The object deserialized from response bod
     */
    public ApiResponse(T data) {
        this.data = data;
    }

    /**
     * <p>Get the <code>data</code>.</p>
     *
     * @return the data
     */
    public T getData() {
        return data;
    }
}
