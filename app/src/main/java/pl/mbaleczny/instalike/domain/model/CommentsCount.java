package pl.mbaleczny.instalike.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentsCount implements Parcelable {

    public static final Parcelable.Creator<CommentsCount> CREATOR = new Parcelable.Creator<CommentsCount>() {
        @Override
        public CommentsCount createFromParcel(Parcel source) {
            return new CommentsCount(source);
        }

        @Override
        public CommentsCount[] newArray(int size) {
            return new CommentsCount[size];
        }
    };
    private long imageId;
    private int comments;

    public CommentsCount() {
    }

    protected CommentsCount(Parcel in) {
        this.imageId = in.readLong();
        this.comments = in.readInt();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.imageId);
        dest.writeInt(this.comments);
    }
}