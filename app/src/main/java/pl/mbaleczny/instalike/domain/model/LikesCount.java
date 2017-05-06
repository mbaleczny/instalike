package pl.mbaleczny.instalike.domain.model;

public class LikesCount {

    private long imageId;
    private int likes;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}