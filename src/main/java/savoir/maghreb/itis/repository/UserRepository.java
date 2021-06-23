package savoir.maghreb.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import savoir.maghreb.itis.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{

}
