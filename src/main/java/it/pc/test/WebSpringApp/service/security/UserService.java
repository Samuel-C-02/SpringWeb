package it.pc.test.WebSpringApp.service.security;

import it.pc.test.WebSpringApp.dto.security.TokenDTO;
import it.pc.test.WebSpringApp.dto.security.UserDTO;
import it.pc.test.WebSpringApp.entity.security.UserEntity;
import it.pc.test.WebSpringApp.mapper.UserMapper;
import it.pc.test.WebSpringApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

    @Transactional
    public UserDTO saveUser(UserDTO userToAdd) {
        if (userToAdd == null) {
            throw new RuntimeException("Utente null");
        }

        if (userRepository.checkIfUserExists(userToAdd.getUsername(), userToAdd.getEmail()) != 0) {
            throw new RuntimeException("Utente già presente nel db");
        }

        userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));

        UserEntity savedUser = userRepository.save(userMapper.dtoToEntity(userToAdd));

        return userMapper.entityToDTO(savedUser);
    }

    public TokenDTO login(UserDTO user) {
        if (user == null) {
            throw new RuntimeException("User null");
        }

        UserEntity userDB = userRepository.getUserByEmail(user.getEmail()).orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // check validità password
        if (!checkPassword(user.getPassword(), userDB.getPassword())) {
            throw new RuntimeException("Credenziali errate");
        }

        String tokenJWT = jwtService.generateToken(userMapper.entityToDTO(userDB));

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(tokenJWT);
        tokenDTO.setExpiresDate(jwtService.getJwtExpiration(tokenJWT));

        return tokenDTO;
    }

    private boolean checkPassword(String rawPsw, String encryptedPsw) {
        return passwordEncoder.matches(rawPsw, encryptedPsw);
    }

}
