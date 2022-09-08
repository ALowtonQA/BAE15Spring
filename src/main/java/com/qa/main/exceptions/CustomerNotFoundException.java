package com.qa.main.exceptions;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer doesn't exist with that ID")
public class CustomerNotFoundException extends EntityNotFoundException {

}
