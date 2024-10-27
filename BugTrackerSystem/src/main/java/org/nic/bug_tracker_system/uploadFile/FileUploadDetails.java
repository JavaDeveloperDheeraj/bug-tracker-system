package org.nic.bug_tracker_system.uploadFile;

import org.nic.bug_tracker_system.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileUploadDetails extends JpaRepository<FileUpload, String>{

}
