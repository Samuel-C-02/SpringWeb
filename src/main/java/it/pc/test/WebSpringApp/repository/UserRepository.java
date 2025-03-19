package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    Optional<UserEntity> getUserByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM web.utente u WHERE u.username = ?1 OR u.email = ?2", nativeQuery = true)
    int checkIfUserExists(String username, String email);

    Optional<UserEntity> findByUsernameAndEmail(String username, String email);

}
