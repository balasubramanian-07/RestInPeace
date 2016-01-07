package com.ekart.restClient.entities;

public final class RestResponse<T> {

    private final int statusCode;
    private final Headers headers;
    private final T response;

    RestResponse(int statusCode, Headers headers, T response) {

        this.statusCode = statusCode;
        this.headers = headers;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Headers getHeaders() {
        return headers;
    }

    public T getResponseBody() {
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestResponse that = (RestResponse) o;

        if (statusCode != that.statusCode) return false;
        if (!headers.equals(that.headers)) return false;
        if (!response.equals(that.response)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", response=" + response +
                '}';
    }

    @Override
    public int hashCode() {
        int result = statusCode;
        result = 31 * result + headers.hashCode();
        result = 31 * result + response.hashCode();
        return result;
    }

    public static class Builder<T> {

        private int statusCode;
        private Headers headers;
        private T response;

        public Builder<T> withStatusCode(int statusCode) {

            this.statusCode = statusCode;
            return this;
        }

        public Builder<T> withHeaders(Headers headers) {

            this.headers = headers;
            return this;
        }

        public Builder<T> withResponseBody(T response) {

            this.response = response;
            return this;
        }

        public RestResponse<T> build() {

            return new RestResponse<>(statusCode, headers, response);
        }
    }
}
