package ss8.bt.security.userprincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ss8.bt.model.entity.User;
import ss8.bt.service.IUserService;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Username is not existed"));
        return UserPrincipal.build(user);
    }
}
