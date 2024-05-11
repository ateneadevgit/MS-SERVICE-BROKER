package com.fusm.servicebroker.servicebroker.service.ms_eportafolio;

import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileRequest;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileSearch;
import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import org.springframework.stereotype.Service;

@Service
public interface IFileService {

    Object getFilesByFolder(FileSearch fileSearch, PageModel pageModel, Integer folderId);
    Object getFileById(Integer fileId);
    void createFile(FileRequest<FileModel> fileRequest);
    void deleteFile(Integer fileId);
    void updateFile(FileRequest<FileModel> fileRequest, Integer fileId);
    Object getConsumedSpace();

}
