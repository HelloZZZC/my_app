package com.heartbeat.myapp.handler;

import com.heartbeat.myapp.exception.BizException;
import com.heartbeat.myapp.exception.errorcode.CommonErrorCode;
import com.heartbeat.myapp.util.ResponseUtil;
import com.heartbeat.myapp.web.model.ErrBodyVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public ResponseUtil<ErrBodyVO> handleBizException(HttpServletRequest request, BizException bizException) {
        ErrBodyVO errBodyVO = new ErrBodyVO();
        errBodyVO.setErrCode(bizException.getErrorCode().getCode());
        if (StringUtils.hasLength(bizException.getCustomMessage())) {
            errBodyVO.setErrMsg(bizException.getCustomMessage());
        } else {
            errBodyVO.setErrMsg(bizException.getErrorCode().getMessage());
        }
        return ResponseUtil.failure(errBodyVO);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseUtil<ErrBodyVO> handleMethodArgNotValid(
            HttpServletRequest request,
            MethodArgumentNotValidException exception
    ) {
        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        ErrBodyVO errBodyVO = new ErrBodyVO();
        errBodyVO.setErrCode(CommonErrorCode.INVALID_PARAMS.getCode());
        errBodyVO.setErrMsg(msg);

        return ResponseUtil.failure(errBodyVO);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseUtil<ErrBodyVO> handleConstraintViolationException(
            HttpServletRequest request,
            ConstraintViolationException exception
    ) {
        ErrBodyVO errBodyVO = new ErrBodyVO();
        errBodyVO.setErrCode(CommonErrorCode.INVALID_PARAMS.getCode());
        errBodyVO.setErrMsg(exception.getMessage());

        return ResponseUtil.failure(errBodyVO);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseUtil<ErrBodyVO> handleMissingServletRequestParam(
            HttpServletRequest request,
            MissingServletRequestParameterException exception) {

        ErrBodyVO errBodyVO = new ErrBodyVO();
        errBodyVO.setErrCode(CommonErrorCode.INVALID_PARAMS.getCode());
        errBodyVO.setErrMsg(exception.getMessage());

        return ResponseUtil.failure(errBodyVO);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseUtil<ErrBodyVO> handleException(HttpServletRequest request, Exception exception) {
        ErrBodyVO errBodyVO = new ErrBodyVO();
        errBodyVO.setErrCode(CommonErrorCode.UN_KNOWN.getCode());
        errBodyVO.setErrMsg(exception.getMessage());

        return ResponseUtil.failure(errBodyVO);
    }
}
