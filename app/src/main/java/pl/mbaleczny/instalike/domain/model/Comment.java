package pl.mbaleczny.instalike.domain.model;


public class Comment {

    private long id;
    private long imageId;
    private long userId;

    private String comment;
    private User user;
    private Object active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Object getActive() {
        return active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active != null;
    }

    public void setActive(Object active) {
        this.active = active;
    }
}
