package com.corelabs.place.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("placeVO")
public class PlaceVO {
    private String placeId;
    private String placeName;
    private String placeAddress;
    private String createDate;
    private String updateDate;
}
