package org.nic.bug_tracker_system.serviceImpl;

import java.util.List;
import org.nic.bug_tracker_system.enums.PriorityEnum;
import org.nic.bug_tracker_system.service.PriorityEnumService;
import org.nic.bug_tracker_system.util.EnumUtil;
import org.springframework.stereotype.Service;

@Service
public class PriorityEnumServiceImpl implements PriorityEnumService {

    @Override
    public List<String> getFormattedPriorityEnums() {
        return EnumUtil.getFormattedEnums(PriorityEnum.values());
    }
}
