package umc.meme.shop.domain.artist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.AvailableTime;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTimeRequestDto {
    private Date date; //날짜
    private DayOfWeek dayOfWeek; //요일
    private Times times;

    static public AvailableTimeRequestDto from(AvailableTime availableTime){
        return AvailableTimeRequestDto.builder()
                .date(availableTime.getDate())
                .dayOfWeek(availableTime.getDayOfWeek())
                .times(availableTime.getTimes())
                .build();
    }
}
