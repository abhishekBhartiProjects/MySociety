package io.github.abhishekbhartiprojects.mysociety.feature.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "memberid",
        "wing",
        "flatno",
        "issuperadmin",
        "isadmin",
        "name",
        "contactno",
        "email",
        "pin",
        "profession",
        "imageurl",
        "description"
})

public class MemberInfoRow implements Parcelable {
    @JsonProperty("memberid")
    private String memberid = "";
    @JsonProperty("wing")
    private String wing = "";
    @JsonProperty("flatno")
    private String flatno = "";
    @JsonProperty("issuperadmin")
    private boolean issuperadmin = false;
    @JsonProperty("isadmin")
    private boolean isadmin = false;
    @JsonProperty("name")
    private String name = "";
    @JsonProperty("contactno")
    private String contactno = "";
    @JsonProperty("email")
    private String email = "";
    @JsonProperty("pin")
    private String pin = "";
    @JsonProperty("profession")
    private String profession = "";
    @JsonProperty("imageurl")
    private String imageurl = "";
    @JsonProperty("description")
    private String description = "";

    protected MemberInfoRow(Parcel in) {
        memberid = in.readString();
        wing = in.readString();
        flatno = in.readString();
        issuperadmin = in.readByte() != 0;
        isadmin = in.readByte() != 0;
        name = in.readString();
        contactno = in.readString();
        email = in.readString();
        pin = in.readString();
        profession = in.readString();
        imageurl = in.readString();
        description = in.readString();
    }

    public static final Creator<MemberInfoRow> CREATOR = new Creator<MemberInfoRow>() {
        @Override
        public MemberInfoRow createFromParcel(Parcel in) {
            return new MemberInfoRow(in);
        }

        @Override
        public MemberInfoRow[] newArray(int size) {
            return new MemberInfoRow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(memberid);
        parcel.writeString(wing);
        parcel.writeString(flatno);
        parcel.writeByte((byte) (issuperadmin ? 1 : 0));
        parcel.writeByte((byte) (isadmin ? 1 : 0));
        parcel.writeString(name);
        parcel.writeString(contactno);
        parcel.writeString(email);
        parcel.writeString(pin);
        parcel.writeString(profession);
        parcel.writeString(imageurl);
        parcel.writeString(description);
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getWing() {
        return wing;
    }

    public void setWing(String wing) {
        this.wing = wing;
    }

    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }

    public boolean isIssuperadmin() {
        return issuperadmin;
    }

    public void setIssuperadmin(boolean issuperadmin) {
        this.issuperadmin = issuperadmin;
    }

    public boolean isisadmin() {
        return isadmin;
    }

    public void setisadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Creator<MemberInfoRow> getCREATOR() {
        return CREATOR;
    }
}
