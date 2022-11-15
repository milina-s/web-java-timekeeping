package com.example.entities;

public class Request {

    private Long id;
    private Activity activity;
    private User user;
    private RequestStatus status;
    private RequestType type;

    public Request() {
    }

    public Request(RequestBuilder builder) {
        this.id = builder.id;;
        this.activity = builder.activity;
        this.user = builder.user;
        this.status = builder.status;
        this.type = builder.type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public static class RequestBuilder {
        private Long id;
        private Activity activity;
        private User user;
        private RequestStatus status;
        private RequestType type;

        public RequestBuilder() {
        }

        public RequestBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public RequestBuilder setActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public RequestBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public RequestBuilder setStatus(RequestStatus status) {
            this.status = status;
            return this;
        }

        public RequestBuilder setType(RequestType type) {
            this.type = type;
            return this;
        }

        public Request build () {
            return new Request(this);
        }
    }
}
