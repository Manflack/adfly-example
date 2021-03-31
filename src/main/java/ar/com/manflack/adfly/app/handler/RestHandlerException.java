package ar.com.manflack.adfly.app.handler;

import ar.com.manflack.adfly.app.dto.ErrorDTO;
import ar.com.manflack.adfly.domain.exception.LinkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandlerException
{
    @ExceptionHandler(LinkException.class)
    public ResponseEntity handleException(LinkException ex)
    {
        ErrorDTO errorDTO = new ErrorDTO(ex.getCode(), ex.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (errorDTO.getErrorCode())
        {
            case LinkException.URL_NOT_VALID:
            case LinkException.LINK_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;

            case LinkException.ID_ALREADY_EXISTS:
                status = HttpStatus.CONFLICT;
                break;

            case LinkException.PASSWORD_INVALID:
                status = HttpStatus.UNAUTHORIZED;
                break;
        }

        ResponseEntity response = new ResponseEntity(errorDTO, status);

        return response;
    }
}
