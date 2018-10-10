package it.tonicminds.authservice.service.security;

import it.tonicminds.authservice.exception.ConflictException;
import it.tonicminds.authservice.model.security.Role;
import it.tonicminds.authservice.model.security.User;
import it.tonicminds.authservice.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    @Override
    public void subscribe(User user) throws ConflictException {

        User existing = repository.findOne(user.getUsername());
        if(existing != null){
            throw new ConflictException("user already exsist");
        }

        List<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setRole("ROLE_USER");
        roles.add(role);

        user.setRoles(roles);

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("new user has been created: ", user.getUsername());
    }
}
