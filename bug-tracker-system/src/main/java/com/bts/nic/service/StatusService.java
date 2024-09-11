package com.bts.nic.service;

import com.bts.nic.modal.Status;
import java.util.List;

public interface StatusService {
    Status saveStatus(Status status);
    Status getStatusById(Integer statusId);
    List<Status> getAllStatuses();
    void deleteStatus(Integer statusId);
}
