package umc.meme.shop.domain.artist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTimeRequestDto {
    private Long userId;
    private List<AvailableTimeDto> availableTimeDtoList;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
        public static class AvailableTimeDto {
            private LocalDate date; //날짜
            private DayOfWeek dayOfWeek; //요일
            private Times times;

            public static AvailableTimeDto from(LocalDate date, DayOfWeek dayOfWeek, Times times){
                return AvailableTimeDto.builder()
                        .date(date)
                        .dayOfWeek(dayOfWeek)
                        .times(times)
                        .build();
            }

    }
}
