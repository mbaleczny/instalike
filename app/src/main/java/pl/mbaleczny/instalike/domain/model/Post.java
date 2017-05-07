package pl.mbaleczny.instalike.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import pl.mbaleczny.instalike.util.Bus;

public class Post implements Parcelable {

    public static final Bus<Post> LIKE_LIST_BUS = new Bus<>();
    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
    private long id;
    private long userId;
    private long eventId;
    private String imageUrl;
    private String comment;
    private Date createdAt;
    private Date updatedAt;

    @SerializedName("userLiked")
    private boolean userLiked;

    private User user;
    private List<LikesCount> likesCount;
    private List<CommentsCount> commentsCount;
    private String active;

    public Post() {
    }

    protected Post(Parcel in) {
        this.id = in.readLong();
        this.userId = in.readLong();
        this.eventId = in.readLong();
        this.userLiked = in.readByte() != 0;
        this.imageUrl = in.readString();
        this.comment = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
        this.active = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.likesCount = in.createTypedArrayList(LikesCount.CREATOR);
        this.commentsCount = in.createTypedArrayList(CommentsCount.CREATOR);
    }

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

    public void setActive(String active) {
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
        if (commentsCount != null && commentsCount.size() == 1) {
            return commentsCount.get(0).getComments();
        }
        return 0;
    }

    public int getLikesCount() {
        if (likesCount != null && likesCount.size() == 1) {
            return likesCount.get(0).getLikes();
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.userId);
        dest.writeLong(this.eventId);
        dest.writeByte(this.userLiked ? (byte) 1 : (byte) 0);
        dest.writeString(this.imageUrl);
        dest.writeString(this.comment);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
        dest.writeString(this.active);
        dest.writeParcelable(this.user, flags);
        dest.writeTypedList(this.likesCount);
        dest.writeTypedList(this.commentsCount);
    }
}