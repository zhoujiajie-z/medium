package com.bootdo.common.service.impl;

import com.bootdo.common.dao.CommentDao;
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
    public ConsultDO get(Long id) {
        return commentDao.get(id);
    }

    @Override
    public List<ConsultDO> list(Map<String, Object> map) {
        return commentDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return commentDao.count(map);
    }

    @Override
    public int save(ConsultDO sysFile) {
        return commentDao.save(sysFile);
    }

    @Override
    public int update(ConsultDO sysFile) {
        return commentDao.update(sysFile);
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
