package com.jpa.service.files;

import com.jpa.domain.FileDTO;
import com.jpa.entity.File;

public interface FileService {

    public void register(FileDTO fileDTO);

    default File toEntity(FileDTO fileDTO){
        return File.builder().id(fileDTO.getId())
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileUuid(fileDTO.getFileUuid())
                .fileSize(fileDTO.getFileSize())
                .post(fileDTO.getPost())
                .build();

    }
}
