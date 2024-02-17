package me.jincrates.springbank.user.query.api.repositories;

import me.jincrates.springbank.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
