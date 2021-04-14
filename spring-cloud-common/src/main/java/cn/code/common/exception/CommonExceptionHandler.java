package cn.code.common.exception;

import cn.code.common.response.ResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Iterator;

@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    public CommonExceptionHandler() {
    }

    @ExceptionHandler({AppException.class})
    @ResponseBody
    public ResponseTemplate validateHandler(AppException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder().code(ex.getErrorCode()).msg(ex.getMessage()).build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseTemplate validateHandler(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder().code("400").msg(this.bindResultErrors(ex.getBindingResult())).build();
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public ResponseTemplate validateHandler(BindException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder().code("400").msg(this.bindResultErrors(ex.getBindingResult())).build();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseTemplate validateHandler(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        StringBuilder sb = new StringBuilder();
        Iterator var3 = ex.getConstraintViolations().iterator();

        while (var3.hasNext()) {
            ConstraintViolation v = (ConstraintViolation) var3.next();
            sb.append(v.getMessage()).append(";");
        }

        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return ResponseTemplate.builder().code("400").msg(sb.toString()).build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public ResponseTemplate validateHandler(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder().code("400").msg(ex.getMessage()).build();
    }

    private String bindResultErrors(BindingResult bindingResult) {
        if (bindingResult == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator var3 = bindingResult.getAllErrors().iterator();

            while (var3.hasNext()) {
                ObjectError error = (ObjectError) var3.next();
                sb.append(error.getDefaultMessage()).append(";");
            }

            if (sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }

    @ExceptionHandler({ResponseStatusException.class})
    public void responseStatusHandler(ResponseStatusException ex, HttpServletResponse response) throws IOException {
        response.setStatus(ex.getStatus().value());
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(ex.getReason());
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseTemplate exceptionHandle(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder().code("500").msg("内部错误,请联系管理员!").build();
    }

    @ExceptionHandler({MultipartException.class})
    @ResponseBody
    public ResponseTemplate exceptionHandle(MultipartException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder().code("400").msg("请选择文件!").build();
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    @ResponseBody
    public ResponseTemplate maxUploadSizeExceededExceptionHandle(Exception ex) {
        log.error("上传文件过大,无法上传:" + ex.getMessage());
        return ResponseTemplate.builder().code("400").msg("上传文件大小不能超过20M!").build();
    }
}
