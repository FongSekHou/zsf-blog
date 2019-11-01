package com.zsf.zsfblog.service.impl;

import com.zsf.zsfblog.mapper.BlogMapper;
import com.zsf.zsfblog.mapper.CollectionMapper;
import com.zsf.zsfblog.mapper.NoticeMapper;
import com.zsf.zsfblog.mapper.UserMapper;
import com.zsf.zsfblog.po.*;
import com.zsf.zsfblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void saveUser(User user) {
        userMapper.insertUser(user);
    }


    /**
     * 这里返回封禁信息时采用的方法是不规范的，实际上应该创建一个dto封装用户和封禁信息，
     * 该方法返回dto交给controller进行判断
     * @param username
     * @param password
     * @return
     */
    @Override
    public User getUserByUsernamePassword(String username,String password) {
        return userMapper.selectUserByUsernamePassword(username,password);
        /*if(user!=null){
            BannedInfo bannedInfo = bannedInfoMapper.selectBannedInfoByUserId(user.getId());
            if(bannedInfo==null){//为空则是没有被封禁
                return user;
            }else {
                user.setId(-1);//因为id是自增主键不可能为负值，设作负值则是被封号的标志
            }
        }
       return user;*/

    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public Integer saveNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public boolean getLikeRelation(Integer userid, Integer blogid) {
        Notice notice = noticeMapper.selectLikeNoticeByUseridBlogid(userid,blogid);
        if(notice==null){
            //说明无关联
            return false;
        }
        return true;
    }

    @Override
    public boolean getCollectRelation(Integer userid, Integer blogid) {
        Collection collection = collectionMapper.getCollectionByUserIdAndBlogId(userid,blogid);
        if(collection==null){
            return false;
        }
        return true;
    }

    @Override
    public Integer saveCollection(Collection collection) {
        return collectionMapper.insertCollection(collection);
    }

    @Override
    public Integer removeNotice(Integer userid, Integer blogid, Integer collectionid, Integer commentid) {
        return noticeMapper.deleteNotice(userid,blogid,collectionid,commentid);
    }

    @Override
    public Integer removeCollection(Integer userid, Integer blogid) {
        return collectionMapper.deleteCollection(userid,blogid);
    }

    @Override
    public PageBean listNoticeInPage(Integer currentPage,Integer userid) {
        PageBean pageBean = new PageBean(currentPage);
        //设置每页显示10行
        pageBean.setPageLines(10);
        pageBean.setTotalRecords(noticeMapper.selectNoticeNumByUserid(userid));
        List<Notice> notices = noticeMapper.listNoticeInPage(pageBean.getStartIndex(), pageBean.getPageLines(), userid);
        List<NoticeVo> noticeVos = new ArrayList<>();
        for(Notice notice:notices){
            //查出来后如果当前状态是未读，那么就设置成已读并插入数据库
            if(notice.getRead()==false){
                noticeMapper.updateRead(notice.getId());
            }
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setUsername(userMapper.selectUserById(notice.getUserId()).getUsername());
            if(notice.getCollectionId()!=null){
                noticeVo.setOperate("收藏");
            }else if(notice.getCommentId()!=null){
                noticeVo.setOperate("评论");
            }else {
                noticeVo.setOperate("点赞");
            }
            noticeVo.setBlogid(notice.getBlogId());
            noticeVo.setBlogname(blogMapper.selectBlogById(notice.getBlogId()).getTitle());
            noticeVos.add(noticeVo);
        }
        pageBean.setEntitys(noticeVos);
        return pageBean;
    }

    @Override
    public Notice getLatestNotice(Integer userid) {
        return noticeMapper.selectLatestNotice(userid);
    }
}
