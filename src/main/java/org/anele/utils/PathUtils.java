package org.anele.utils;

public enum PathUtils {
    GET_SPECIFIC_USER("/users/{username}");
    private final String path;
    PathUtils(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
}
