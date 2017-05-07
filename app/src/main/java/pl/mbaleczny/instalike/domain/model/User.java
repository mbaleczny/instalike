package pl.mbaleczny.instalike.domain.model;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String role;
    private String active;
    private String phoneNumber;
    private String printForm;

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.username = in.readString();
        this.role = in.readString();
        this.active = in.readString();
        this.phoneNumber = in.readParcelable(Object.class.getClassLoader());
        this.printForm = username;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrintForm() {
        if (printForm == null) {
            return username;
        }
        return printForm;
    }

    public void setPrintForm(String printForm) {
        this.printForm = printForm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActive() {
        return active;
    }

    public boolean isActive() {
        return active != null;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.username);
        dest.writeString(this.role);
        dest.writeString(this.active);
        dest.writeString(this.phoneNumber);
    }

    @Override
    public String toString() {
        return getPrintForm();
    }
}