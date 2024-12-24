
package com.corelabs.sample.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("filesVO")
public class FilesVO {
	private String fileUid;
	private String docUid;
	private String docType;
	private String oriFileNm;
	private String oriFileFullNm;
	private String oriFileType;
	private String oriFileSize;
	private String saveFileNm;
	private String saveFileFullNm;
	private String saveFilePath;
	private String saveFileFullPath;
	private String saveFileType;
	private String saveFileSize;
	private String createUserId;
	private String createDt;
	private String updateUserId;
	private String updateDt;
}
