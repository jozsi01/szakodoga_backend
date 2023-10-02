package ErrorHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class ErrorCodeException extends Exception{
    private ErrorCode errorCode;
    public ErrorCodeException(String errorCode) throws Exception{
        this.errorCode = new ObjectMapper().readValue(errorCode, ErrorCode.class);
    }
}
