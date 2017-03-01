package inject;

/**
 * Created by anqiansong on 2016/5/27.
 * 主题:
 */
public class UnHandleJsonException extends Exception {
    public String errorMessage;

    public UnHandleJsonException(String errorMessage){
        this.errorMessage=errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
