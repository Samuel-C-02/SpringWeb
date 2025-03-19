package it.pc.test.WebSpringApp.mapper;

import it.pc.test.WebSpringApp.dto.security.UserDTO;
import it.pc.test.WebSpringApp.entity.security.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends IMapperBase<UserEntity, UserDTO> {

}
