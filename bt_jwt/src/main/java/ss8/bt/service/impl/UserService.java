package ss8.bt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ss8.bt.service.IRoleService;
import ss8.bt.service.IUserService;
import ss8.bt.model.dto.request.FormRegisterDto;
import ss8.bt.model.entity.Role;
import ss8.bt.model.entity.RoleName;
import ss8.bt.model.entity.User;
import ss8.bt.repository.IUserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return  userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public User save(FormRegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("Email is existed");
        }

        Set<Role> roles = new HashSet<>();
        System.out.println(registerDto.getRoles());
        if(registerDto.getRoles()==null||registerDto.getRoles().isEmpty()){
            roles.add(roleService.findByRoleName(RoleName.ROLE_MEMBER));
        } else {
        registerDto.getRoles().forEach(
                role->{switch (role){
                    case "ADMIN":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                    case "USER":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_MEMBER));
                    default:
                        roles.add(roleService.findByRoleName(RoleName.ROLE_MEMBER));
                }}
        );
        }
        User user = User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(encoder.encode(registerDto.getPassword()))
                .roles(roles)
                .build();
        return userRepository.save(user);
    }


}
