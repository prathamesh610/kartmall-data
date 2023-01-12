package com.prathamesh.kartmalldata.handler;

import com.prathamesh.kartmalldata.dto.MetaDTO;
import com.prathamesh.kartmalldata.dto.ResponseDTO;
import com.prathamesh.kartmalldata.exception.KartMallException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;


@ControllerAdvice
public class InternalExceptionHandler {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class KartMallWrappedException extends Exception{
        private String errorCode;
        private String errorMessage;
        public KartMallWrappedException(KartMallException kartMallException){
            this.errorCode = kartMallException.getErrorCode();
            this.errorMessage = kartMallException.getErrorMessage();
        }
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = KartMallWrappedException.class)
    public ResponseEntity<Object> handleKartMallWrappedException(KartMallWrappedException kartMallWrappedException){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(null);
        responseDTO.setMeta(new MetaDTO(
                kartMallWrappedException.errorCode, kartMallWrappedException.errorMessage, UUID.randomUUID()));
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
