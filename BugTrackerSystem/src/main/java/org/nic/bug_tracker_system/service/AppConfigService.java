package org.nic.bug_tracker_system.service;

import java.io.Serializable;
import java.util.List;

public interface AppConfigService extends Serializable {
	
    List<?> categories();
    List<?> priorities();
    List<?> reproducibilites();
    List<?> severities();
    List<?> assignTo();
}
