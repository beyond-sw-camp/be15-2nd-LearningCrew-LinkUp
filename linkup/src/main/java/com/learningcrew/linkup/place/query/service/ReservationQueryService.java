package com.learningcrew.linkup.place.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.place.query.dto.request.ReserveListRequest;
import com.learningcrew.linkup.place.query.dto.response.ReserveDto;
import com.learningcrew.linkup.place.query.dto.response.ReserveListResponse;
import com.learningcrew.linkup.place.query.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationQueryService {

    private final ReservationMapper reservationMapper;

    @Transactional(readOnly = true)
    public ReserveListResponse getReserveList(int ownerId, ReserveListRequest request) {
        request.setOwnerId(ownerId);
        List<ReserveDto> reserves = reservationMapper.selectAllReservation(request);
        long totalItems = reservationMapper.countReserves(request);
        int page = request.getPage();
        int size = request.getSize();
        int totalPage = (int) Math.ceil((double) totalItems / size);

        return ReserveListResponse.builder()
                .reservations(reserves)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage(totalPage)
                        .totalItems(totalItems)
                        .build())
                .build();
    }

}
