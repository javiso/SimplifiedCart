package com.ecommerce.ecommerce.exception

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class RestExceptionHandler  {

    @ExceptionHandler(NoSuchElementException::class, EmptyResultDataAccessException::class, ProductNotFound::class, CartNotFound::class)
    fun handleEntityNotFound(e: Exception) : ResponseEntity<ApiError>{
        val apiError = e.message?.let { ApiError(HttpStatus.NOT_FOUND, message = it, errors = listOf()) }
        return  ResponseEntity(apiError, HttpStatus.NOT_FOUND)
    }

   @ExceptionHandler(HttpMessageNotReadableException::class, MethodArgumentTypeMismatchException::class)
    fun handleNotReadableException(ex: Exception) : ResponseEntity<ApiError>{
        val apiError = ApiError(status = HttpStatus.BAD_REQUEST, message = "field bad formatted", errors = listOf(ex.message))
        return  ResponseEntity(apiError, HttpStatus.BAD_REQUEST, )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<ApiError> {
        val exceptions = ex.bindingResult.fieldErrors.map {violation -> violation.field + ":" + violation.defaultMessage }
        val apiError = ApiError(HttpStatus.BAD_REQUEST, message = "Parameter not valid.", errors = exceptions)
        return ResponseEntity(apiError , HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handledConstraintViolation(ex: ConstraintViolationException, ): ResponseEntity<ApiError> {
        val exceptions = ex.constraintViolations.map {violation -> violation.propertyPath.toString() + ":" + violation.message }
        val apiError = ApiError(HttpStatus.BAD_REQUEST, message = "Parameter not valid.", errors = exceptions)
        return ResponseEntity(apiError , HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MissingPathVariableException::class, MissingServletRequestParameterException::class)
    fun handledMissingPathVariable(ex : Exception) : ResponseEntity<ApiError> {
        val apiError = ApiError(status = HttpStatus.BAD_REQUEST, message = "missing parameter", errors = listOf(ex.message))
        return  ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }
}