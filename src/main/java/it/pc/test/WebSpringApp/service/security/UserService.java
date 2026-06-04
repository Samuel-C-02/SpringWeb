package it.pc.test.WebSpringApp.service.security;

import it.pc.test.WebSpringApp.dto.security.TokenDTO;
import it.pc.test.WebSpringApp.dto.security.UserDTO;
import it.pc.test.WebSpringApp.entity.security.UserEntity;
import it.pc.test.WebSpringApp.exceptions.EntityNotFoundException;
import it.pc.test.WebSpringApp.mapper.UserMapper;
import it.pc.test.WebSpringApp.repository.UserRepository;
import it.pc.test.WebSpringApp.utils.LogUtils;
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

    public UserDTO getUserById(Integer id) {
        return userMapper.entityToDTO(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with Id " + id)));
    }

    /**
     * Login method: search user by email and check password
     *
     * @param user user containing email and password
     * @return dto wth token and expire date
     */
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

    /**
     * Update the user username and role, uses id to search the user.
     *
     * @param user user with updated data
     * @return updated user
     */
    @Transactional
    public UserDTO updateUser(UserDTO user) {
        if (user == null || user.getId() == null || user.getEmail() == null) {
            throw new RuntimeException("Invalid User data");
        }

        UserEntity userDB = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found with Id " + user.getId()));
        UserEntity updatedData = userMapper.dtoToEntity(user);

        userDB.setUsername(updatedData.getUsername());
        userDB.setRole(updatedData.getRole());
        //userDB.setEmail(updatedData.getEmail());
        //userDB.setPassword(passwordEncoder.encode(updatedData.getPassword()));

        UserEntity updatedSavedUser = userRepository.save(userDB);

        return userMapper.entityToDTO(updatedSavedUser);
    }

    @Transactional
    public boolean deleteUser(Integer id) {
        if (id == null || id == 0) {
            return false;
        }

        userRepository.deleteById(id);
        LogUtils.log.info("Delete user with Id {}", id);
        return true;
    }

    private boolean checkPassword(String rawPsw, String encryptedPsw) {
        return passwordEncoder.matches(rawPsw, encryptedPsw);
    }

}
