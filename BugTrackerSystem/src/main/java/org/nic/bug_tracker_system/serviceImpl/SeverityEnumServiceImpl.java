package org.nic.bug_tracker_system.serviceImpl;

import java.util.List;
import org.nic.bug_tracker_system.enums.SeverityEnum;
import org.nic.bug_tracker_system.service.SeverityEnumService;
import org.nic.bug_tracker_system.util.EnumUtil;
import org.springframework.stereotype.Service;

@Service
public class SeverityEnumServiceImpl implements SeverityEnumService {

    @Override
    public List<String> getFormattedSeverityEnums() {
        return EnumUtil.getFormattedEnums(SeverityEnum.values());
    }
}
