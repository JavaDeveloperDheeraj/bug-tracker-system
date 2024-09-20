package org.nic.bug_tracker_system.serviceImpl;

import java.util.List;
import org.nic.bug_tracker_system.enums.CategoryEnum;
import org.nic.bug_tracker_system.service.CategoryEnumService;
import org.nic.bug_tracker_system.util.EnumUtil;
import org.springframework.stereotype.Service;

@Service
public class CategoryEnumServiceImpl implements CategoryEnumService {

    @Override
    public List<String> getFormattedCategoryEnums() {
        return EnumUtil.getFormattedEnums(CategoryEnum.values());
    }
}
