package org.anele.utils;

public enum PathUtils {
    GET_SPECIFIC_USER("/users/{username}"),
    GET_A_REPOSITORIES("/repos/{owner}/{repo}"),
    PATCH_UPDATE_A_REPOSITORY("/repos/{owner}/{repo}"),
    REPOSITORY_CONTRIBUTORS("/repos/{owner}/{repo}/contributors");
    private final String path;

    PathUtils(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
