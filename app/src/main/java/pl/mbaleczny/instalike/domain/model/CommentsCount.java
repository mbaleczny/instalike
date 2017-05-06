package pl.mbaleczny.instalike.domain.model;

public class CommentsCount {

    private long imageId;
    private int comments;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}