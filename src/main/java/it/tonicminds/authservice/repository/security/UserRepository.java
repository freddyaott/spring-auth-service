package it.tonicminds.authservice.repository.security;

import it.tonicminds.authservice.model.security.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
