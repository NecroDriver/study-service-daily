package com.xin.daily.dao.user;

import com.xin.daily.entity.user.UserLogin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author creator mafh 2019/11/28 17:59
 * @author updater
 * @version 1.0.0
 */
@Mapper
public interface UserLoginMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(UserLogin record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserLogin record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UserLogin record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(UserLogin record);

    /**
     * 根据账户查询用户信息
     *
     * @param account 账户
     * @return 结果
     */
    UserLogin selectByAccount(String account);
}