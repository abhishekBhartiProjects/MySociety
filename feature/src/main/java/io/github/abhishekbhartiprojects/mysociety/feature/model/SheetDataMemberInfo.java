package io.github.abhishekbhartiprojects.mysociety.feature.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

import androidx.annotation.Keep;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "rows"
})

@Keep
public class SheetDataMemberInfo implements Parcelable {
    @JsonProperty("rows")
    private List<MemberInfoRow> rows = null;

    protected SheetDataMemberInfo(Parcel in) {
        rows = in.createTypedArrayList(MemberInfoRow.CREATOR);
    }

    public static final Creator<SheetDataMemberInfo> CREATOR = new Creator<SheetDataMemberInfo>() {
        @Override
        public SheetDataMemberInfo createFromParcel(Parcel in) {
            return new SheetDataMemberInfo(in);
        }

        @Override
        public SheetDataMemberInfo[] newArray(int size) {
            return new SheetDataMemberInfo[size];
        }
    };

    @JsonProperty("rows")
    public List<MemberInfoRow> getRows() {
        return rows;
    }

    @JsonProperty("rows")
    public void setRows(List<MemberInfoRow> rows) {
        this.rows = rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(rows);
    }
}
