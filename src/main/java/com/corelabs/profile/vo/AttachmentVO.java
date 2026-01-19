package com.corelabs.profile.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("attachmentVO")
public class AttachmentVO {

    private String fileId;
    private String profileId;
    private String originalName;
    private String saveName;
    private String filePath;
    private String fileSize;
    private String createdAt;
}
