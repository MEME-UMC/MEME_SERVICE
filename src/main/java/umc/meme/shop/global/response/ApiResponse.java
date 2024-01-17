package umc.meme.shop.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.SuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "statusCode", "message", "data"})
public class ApiResponse<T> {

    private final String isSuccess;
    private final int statusCode;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public static <T>ApiResponse<T> SuccessResponse(SuccessStatus status, T data){
        return new ApiResponse<>("SUCCESS", status.getCode(), status.getMessage(), data);
    }
    public static ApiResponse SuccessResponse(SuccessStatus status){
        return new ApiResponse<>("SUCCESS", status.getCode(), status.getMessage(), "");
    }

    public static ApiResponse FailureResponse(int statusCode, String message){
        return new ApiResponse<>("FAILURE", statusCode, message, "");
    }

    public static ApiResponse FailureResponse(ErrorStatus errorStatus){
        return new ApiResponse<>("FAILURE", errorStatus.getCode(), errorStatus.getMessage(), "");
    }
}
