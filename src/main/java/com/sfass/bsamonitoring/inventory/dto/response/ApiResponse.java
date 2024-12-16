package com.sfass.bsamonitoring.inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private T data;

    // 성공 응답을 위한 정적 팩토리 메서드
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), data);
    }

    // 에러 응답 (상태 코드만)을 위한 정적 팩토리 메서드
    public static <T> ApiResponse<T> error(int status, T data) {
        return new ApiResponse<>(status, data);
    }
}