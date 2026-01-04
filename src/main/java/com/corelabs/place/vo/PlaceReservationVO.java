package com.corelabs.place.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("placeReservationVO")
public class PlaceReservationVO {
    private String reservationId;
    private int reservationNumber;
    private String reservationUser;
    private String reservationDate;
    private String placeId;
    private String placeName;
    private String placeAddress;
}
