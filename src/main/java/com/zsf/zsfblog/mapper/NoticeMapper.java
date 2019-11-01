package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {

    int insertNotice(Notice notice);

    Notice selectLikeNoticeByUseridBlogid(@Param("userid") Integer userid,@Param("blogid") Integer blogid);

    Integer deleteNotice(@Param("userid")Integer userid,@Param("blogid")Integer blogid,@Param("collectionid")Integer collectionid,@Param("commentid")Integer commentid);

    List<Notice> listNoticeInPage(@Param("offset")Integer startIndex,@Param("number") Integer pageLines, @Param("userid")Integer userid);

    Integer selectNoticeNumByUserid(Integer userid);

    Notice selectLatestNotice(Integer userid);

    Integer updateRead(Integer id);
}
