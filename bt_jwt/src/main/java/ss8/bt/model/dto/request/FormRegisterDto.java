package ss8.bt.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormRegisterDto {
    private String name;
    private String email;
    private String password;
    private List<String> roles;
}
