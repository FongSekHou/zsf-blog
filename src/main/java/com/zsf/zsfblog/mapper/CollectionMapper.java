package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Collection;
import org.apache.ibatis.annotations.Param;

public interface CollectionMapper {

    Integer insertCollection(Collection collection);
    Collection getCollectionByUserIdAndBlogId(@Param("userid") Integer userid,@Param("blogid") Integer blogid);
    Integer deleteCollection(@Param("userid") Integer userid,@Param("blogid") Integer blogid);
}
