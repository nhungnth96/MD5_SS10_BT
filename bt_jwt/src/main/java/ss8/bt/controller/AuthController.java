package ss8.bt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ss8.bt.model.dto.request.FormLoginDto;
import ss8.bt.model.dto.request.FormRegisterDto;
import ss8.bt.model.dto.response.JwtResponse;
import ss8.bt.security.jwt.JwtProvider;
import ss8.bt.security.userprincipal.UserPrincipal;
import ss8.bt.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login","login",new FormRegisterDto());
    }
    @PostMapping("/login")
    public String login(@ModelAttribute FormLoginDto formLoginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        formLoginDto.getEmail(),
                        formLoginDto.getPassword()
                ));
        String token = jwtProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
         JwtResponse.builder().token(token)
                .name(userPrincipal.getName())
                .email(userPrincipal.getEmail())
                .roles(roles)
                .type("Bearer")
               .build();
            
    }
    @GetMapping("/register")
    private ModelAndView register(){
            return new ModelAndView("register","register",new FormRegisterDto());
    }
    @PostMapping("/register")
    private String doRegister(@ModelAttribute FormRegisterDto formRegisterDto){
        userService.save(formRegisterDto);
        return "redirect:/login";
    }
}
