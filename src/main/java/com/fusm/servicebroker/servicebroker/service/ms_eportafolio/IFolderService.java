package com.fusm.servicebroker.servicebroker.service.ms_eportafolio;

import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FolderRequest;
import org.springframework.stereotype.Service;

@Service
public interface IFolderService {

    Object getFoldersByUser();
    void createFolder(FolderRequest folderRequest);
    void updateFolder(FolderRequest folderRequest, Integer folderId);
    void deleteFolder(Integer folderId);

}
