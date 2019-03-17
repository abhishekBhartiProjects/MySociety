package io.github.abhishekbhartiprojects.mysociety.feature.model;


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
public class SheetDataMemberInfo {
    @JsonProperty("rows")
    private List<MemberInfoRow> rows = null;

    @JsonProperty("rows")
    public List<MemberInfoRow> getRows() {
        return rows;
    }

    @JsonProperty("rows")
    public void setRows(List<MemberInfoRow> rows) {
        this.rows = rows;
    }
}
