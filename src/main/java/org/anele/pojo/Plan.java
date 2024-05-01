package org.anele.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {
    private String name;
    private Integer space;
    private Integer collaborators;
    private Integer privateRepos;
    public String getName() {
        return name;
    }
    public Integer getSpace() {
        return space;
    }
    public Integer getCollaborators() {
        return collaborators;
    }
    public Integer getPrivateRepos() {
        return privateRepos;
    }
}
