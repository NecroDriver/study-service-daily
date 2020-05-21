package com.xin.daily.dao.novel;

import com.xin.daily.entity.novel.Novel;
import com.xin.daily.vo.NovelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态代理总结
 * 1.Mapper的namespace必须和mapper接口的全路径一致。
 * 2.Mapper接口的方法名必须和sql定义的id一致
 * 3.Mapper接口中方法的输入参数类型必须和sql定义的parameterType一致（不一定）
 * 4.Mapper接口中方法的输出参数类型必须和sql定义的resultType一致
 *
 * @author creator mafh 2019/11/28 17:56
 * @author updater
 * @version 1.0.0
 */
@Mapper
public interface NovelMapper {
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
    int insert(Novel record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Novel record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Novel record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Novel record);

    /**
     * 根据小说名称查询小说
     *
     * @param novelName 小说名称
     * @return 小说对象
     */
    Novel selectByNovelName(String novelName);

    /**
     * 根据小说编号查询小说
     *
     * @param novelCode 小说编号
     * @return 小说对象
     */
    Novel selectByNovelCode(String novelCode);

    /**
     * 查询小说列表
     *
     * @param orderType 排序类型
     * @return 列表
     */
    List<NovelVo> selectListByOrderType(@Param("orderType") String orderType);
}