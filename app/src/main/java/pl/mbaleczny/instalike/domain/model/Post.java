package pl.mbaleczny.instalike.domain.model;

import java.util.Date;
import java.util.List;

import pl.mbaleczny.instalike.util.Bus;

public class Post {

    public static final Bus<Post> LIKE_LIST_BUS = new Bus<>();

    private long id;

    private long userId;
    private long eventId;

    private boolean userLiked;

    private String imageUrl;
    private String comment;
    private Date createdAt;
    private Date updatedAt;

    private Object active;

    private User user;
    private List<LikesCount> likesCount;
    private List<CommentsCount> commentsCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public boolean getActive() {
        return active != null;
    }

    public void setActive(Object active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isUserLiked() {
        return userLiked;
    }

    public void setUserLiked(boolean userLiked) {
        this.userLiked = userLiked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LikesCount> getLikes() {
        return likesCount;
    }

    public void setLikes(List<LikesCount> likes) {
        this.likesCount = likes;
    }

    public List<CommentsCount> getComments() {
        return commentsCount;
    }

    public void setComments(List<CommentsCount> comments) {
        this.commentsCount = comments;
    }

    public int getCommentsCount() {
        return commentsCount != null ? commentsCount.size() : 0;
    }

    public int getLikesCount() {
        return likesCount != null ? likesCount.size() : 0;
    }
}