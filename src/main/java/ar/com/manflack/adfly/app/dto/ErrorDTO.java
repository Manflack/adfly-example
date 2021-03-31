package ar.com.manflack.adfly.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO
{
    private String errorCode;
    private String errorMessage;
}
