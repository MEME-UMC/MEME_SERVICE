package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistTimeDto {
    private DayOfWeek availableDayOfWeek;
    private Times availableTime;
}
