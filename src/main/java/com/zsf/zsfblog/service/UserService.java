package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.Collection;
import com.zsf.zsfblog.po.Notice;
import com.zsf.zsfblog.po.PageBean;
import com.zsf.zsfblog.po.User;

public interface UserService {
    void saveUser(User user);

    User getUserByUsernamePassword(String username,String password);

    int updateUser(User user);

    User getUserById(Integer id);

    Integer saveNotice(Notice notice);

    boolean getLikeRelation(Integer userid,Integer blogid);

    boolean getCollectRelation(Integer userid,Integer blogid);

    Integer saveCollection(Collection collection);

    Integer removeNotice(Integer userid,Integer blogid,Integer collectionid,Integer commentid);

    Integer removeCollection(Integer userid,Integer blogid);

    PageBean listNoticeInPage(Integer currentPage,Integer userid);


    Notice getLatestNotice(Integer userid);
}
