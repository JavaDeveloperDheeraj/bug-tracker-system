package org.nic.bug_tracker_system.serviceImpl;

import java.util.List;
import org.nic.bug_tracker_system.enums.ReproducibilityEnum;
import org.nic.bug_tracker_system.service.ReproducibilityEnumService;
import org.nic.bug_tracker_system.util.EnumUtil;
import org.springframework.stereotype.Service;

@Service
public class ReproducibilityEnumServiceImpl implements ReproducibilityEnumService {

    @Override
    public List<String> getFormattedReproducibilityEnums() {
        return EnumUtil.getFormattedEnums(ReproducibilityEnum.values());
    }
}
