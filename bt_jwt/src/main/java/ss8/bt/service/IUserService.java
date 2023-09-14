package ss8.bt.service;

import ss8.bt.model.dto.request.FormRegisterDto;
import ss8.bt.model.entity.User;


import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String username);
    User save(FormRegisterDto registerDto);

}
