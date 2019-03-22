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
        "isactive",
        "name",
        "contactno",
        "email",
        "pin",
        "profession",
        "dob",
        "imageurl",
        "isOwner",
        "bloodGroup",
        "description",
        "updatedby",
        "updatedon",

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
    @JsonProperty("isowner")
    private boolean isowner = false;
    @JsonProperty("bloodgroup")
    private String bloodgroup = "";
    @JsonProperty("description")
    private String description = "";
    @JsonProperty("isactive")
    private boolean isactive = false;
    @JsonProperty("updatedby")
    private String updatedby = "";
    @JsonProperty("updatedon")
    private String updatedon = "";
    @JsonProperty("dob")
    private String dob = "";

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
        isowner = in.readByte() != 0;
        bloodgroup = in.readString();
        description = in.readString();
        isactive = in.readByte() != 0;
        updatedby = in.readString();
        updatedon = in.readString();
        dob = in.readString();
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
        parcel.writeByte((byte) (isowner ? 1 : 0));
        parcel.writeString(bloodgroup);
        parcel.writeString(description);
        parcel.writeByte((byte) (isactive ? 1 : 0));
        parcel.writeString(updatedby);
        parcel.writeString(updatedon);
        parcel.writeString(dob);
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

    public boolean isIsowner() {
        return isowner;
    }

    public void setIsowner(boolean isowner) {
        this.isowner = isowner;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getUpdatedon() {
        return updatedon;
    }

    public void setUpdatedon(String updatedon) {
        this.updatedon = updatedon;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public static Creator<MemberInfoRow> getCREATOR() {
        return CREATOR;
    }
}
