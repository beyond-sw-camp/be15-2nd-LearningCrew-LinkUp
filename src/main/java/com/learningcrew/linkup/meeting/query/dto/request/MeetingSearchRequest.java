package com.learningcrew.linkup.meeting.query.dto.request;

import com.learningcrew.linkup.linker.command.domain.constants.LinkerGender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class MeetingSearchRequest {
    private Integer page = 1; // 값이 없을 때의 default
    private Integer size = 10;
    // 지역별 ?
    @NotNull
    private LinkerGender gender = LinkerGender.BOTH;
    @NotNull
    @NotEmpty
    private List<String> ageGroups = Arrays.asList("10, 20, 30, 40, 50, 60, 70+".split(", "));
    @NotNull
    @NotEmpty
    private List<String> levels = Arrays.asList("LV1, LV2, LV3".split(", "));
    private List<Integer> sportIds;
    private List<Integer> statusIds;
    private LocalDate minDate; // 검색 조건
    private LocalDate maxDate; // 검색 조건

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }
}
