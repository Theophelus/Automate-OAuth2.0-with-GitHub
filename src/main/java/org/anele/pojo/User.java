package org.anele.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String login;
    private Integer id;
    private String nodeId;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private Boolean siteAdmin;
    private String name;
    private Object company;
    private String blog;
    private String location;
    private String email;
    private Boolean hireable;
    private String bio;
    private Object twitterUsername;
    private Integer publicRepos;
    private Integer publicGists;
    private Integer followers;
    private Integer following;
    private String createdAt;
    private String updatedAt;
    private Integer privateGists;
    private Integer totalPrivateRepos;
    private Integer ownedPrivateRepos;
    private Integer diskUsage;
    private Integer collaborators;
    private Boolean twoFactorAuthentication;
    private Plan plan;
    public String getLogin() {
        return login;
    }
    public Integer getId() {
        return id;
    }
    public String getNodeId() {
        return nodeId;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public String getGravatarId() {
        return gravatarId;
    }
    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }
    public String getEventsUrl() {
        return eventsUrl;
    }
    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public String getName() {
        return name;
    }

    public Object getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }
    public String getEmail() {
        return email;
    }

    public Boolean getHireable() {
        return hireable;
    }

    public String getBio() {
        return bio;
    }

    public Object getTwitterUsername() {
        return twitterUsername;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public Integer getPublicGists() {
        return publicGists;
    }
    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public Integer getPrivateGists() {
        return privateGists;
    }
    public Integer getTotalPrivateRepos() {
        return totalPrivateRepos;
    }
    public Integer getOwnedPrivateRepos() {
        return ownedPrivateRepos;
    }
    public Integer getDiskUsage() {
        return diskUsage;
    }
    public Integer getCollaborators() {
        return collaborators;
    }
    public Boolean getTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }
    public Plan getPlan() {
        return plan;
    }
}
