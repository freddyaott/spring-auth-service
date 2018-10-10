package it.tonicminds.authservice.service.security;

import it.tonicminds.authservice.exception.ConflictException;
import it.tonicminds.authservice.model.security.User;

public interface UserService {

    void subscribe(User user) throws ConflictException;

}
