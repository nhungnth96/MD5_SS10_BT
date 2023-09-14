package ss8.bt.service;

import ss8.bt.model.entity.Role;
import ss8.bt.model.entity.RoleName;

public interface IRoleService {
    Role findByRoleName(RoleName roleName);
}
