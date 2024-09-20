package org.nic.bug_tracker_system.serviceImpl;

import java.util.List;
import org.nic.bug_tracker_system.enums.RoleEnum;
import org.nic.bug_tracker_system.service.RoleEnumService;
import org.nic.bug_tracker_system.util.EnumUtil;
import org.springframework.stereotype.Service;

@Service
public class RoleEnumServiceImpl implements RoleEnumService {

 
    public List<String> getFormattedRoleEnums() {
        return EnumUtil.getFormattedEnums(RoleEnum.values());
    }
}
