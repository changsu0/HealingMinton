
package com.corelabs.notice.vo;

import com.corelabs.common.vo.CommonVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("noticeVO")
public class NoticeVO extends CommonVO {
    private String noticeUid;
    private String title;
    private String content;
    private int sort;
    private String delYn;
    private String type;
}
