package pl.mbaleczny.instalike.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LikesCount implements Parcelable {

    public static final Parcelable.Creator<LikesCount> CREATOR = new Parcelable.Creator<LikesCount>() {
        @Override
        public LikesCount createFromParcel(Parcel source) {
            return new LikesCount(source);
        }

        @Override
        public LikesCount[] newArray(int size) {
            return new LikesCount[size];
        }
    };
    private long imageId;
    private int likes;

    public LikesCount() {
    }

    protected LikesCount(Parcel in) {
        this.imageId = in.readLong();
        this.likes = in.readInt();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.imageId);
        dest.writeInt(this.likes);
    }
}