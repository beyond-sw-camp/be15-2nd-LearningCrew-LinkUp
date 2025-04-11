package com.learningcrew.linkup.place.query.mapper;

import com.learningcrew.linkup.place.query.dto.request.ReserveListRequest;
import com.learningcrew.linkup.place.query.dto.response.ReserveDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    // 예약내역 조회 ( 수락만 )
    List<ReserveDto> selectAllReservation(ReserveListRequest reservationRequest);

    long countReserves(@Param("reservationRequest") ReserveListRequest reservationRequest);
}
