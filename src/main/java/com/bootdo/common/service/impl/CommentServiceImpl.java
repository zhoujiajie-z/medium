package com.bootdo.common.service.impl;

import com.bootdo.common.dao.CommentDao;
import com.bootdo.common.domain.CommentDO;
import com.bootdo.common.domain.CommentRelation;
import com.bootdo.common.domain.ConsultDO;
import com.bootdo.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public CommentDO get(Long id) {
        return commentDao.get(id);
    }

    @Override
    public List<CommentDO> list(Map<String, Object> map) {
        return commentDao.list(map);
    }

    @Override
    public List<CommentRelation> listCommentRelation(Map<String, Object> map) {
        return commentDao.listCommentRelation(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return commentDao.count(map);
    }

    @Override
    public int save(CommentDO comment) {
        return commentDao.save(comment);
    }

    @Override
    public int update(CommentDO comment) {
        return commentDao.update(comment);
    }

    @Override
    public int remove(Long id) {
        return commentDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return commentDao.batchRemove(ids);
    }
}
