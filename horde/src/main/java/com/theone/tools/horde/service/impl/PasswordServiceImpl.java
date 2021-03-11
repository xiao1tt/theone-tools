package com.theone.tools.horde.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.google.common.base.Preconditions;
import com.theone.tools.horde.dao.PasswordDao;
import com.theone.tools.horde.entity.PasswordEntity;
import com.theone.tools.horde.service.PasswordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Password)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 13:58:39
 */
@Service
public class PasswordServiceImpl implements PasswordService {
    @Resource
    private PasswordDao passwordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PasswordEntity queryById(Integer id) {
        return this.passwordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<PasswordEntity> queryAllByLimit(int offset, int limit) {
        return this.passwordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param password 实例对象
     * @return 实例对象
     */
    @Override
    public PasswordEntity insert(PasswordEntity password) {
        this.passwordDao.insert(password);
        return password;
    }

    /**
     * 修改数据
     *
     * @param password 实例对象
     * @return 实例对象
     */
    @Override
    public PasswordEntity update(PasswordEntity password) {
        this.passwordDao.update(password);
        return this.queryById(password.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.passwordDao.deleteById(id) > 0;
    }

    private final MD5 md5 = MD5.create();

    @Override
    public boolean check(String username, String password) {
        Preconditions.checkArgument(StringUtils.isNotBlank(username), "用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(password), "密码不能为空");

        PasswordEntity entity = passwordDao.query(username);
        if (entity == null) {
            return StringUtils.equals(md5.digestHex(username), password);
        }

        return StringUtils.equals(password, entity.getPassword());
    }
}