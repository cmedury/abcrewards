package com.abcdotcom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Given Customer is not found.")
public class CustomerNotFoundException extends RuntimeException {}
