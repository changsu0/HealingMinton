
package com.corelabs.image.vo;

import com.corelabs.common.vo.CommonVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("imageVO")
public class ImageVO extends CommonVO {
    private String fileUid;
    private String docUid;
    private String docType;
    private String oriFileNm;
    private String oriFileFullNm;
    private String oriFileSize;
    private String saveFileNm;
    private String saveFileFullNm;
    private String saveFilePath;
    private String saveFileFullPath;
    private String saveFileType;
    private String saveFileSize;
}
