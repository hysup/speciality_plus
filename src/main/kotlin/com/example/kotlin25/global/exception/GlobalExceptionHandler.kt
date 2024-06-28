package com.example.kotlin25.global.exception

import com.example.kotlin25.global.Type.ModelNotFoundException
import com.example.kotlin25.global.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        e: IllegalArgumentException
    ): ResponseEntity<ErrorResponse> =

        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(
        e: UsernameNotFoundException
    ): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(
        e: ModelNotFoundException
    ): ResponseEntity<ErrorResponse> =

        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
}


