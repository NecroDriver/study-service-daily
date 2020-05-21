package com.xin.daily.dao.novel;

import com.xin.daily.entity.novel.NovelChapter;
import com.xin.daily.vo.NovelChapterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author creator mafh 2019/11/28 17:56
 * @author updater
 * @version 1.0.0
 */
@Mapper
public interface NovelChapterMapper {
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
    int insert(NovelChapter record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(NovelChapter record);

    /**
     * 根据章节编号获取章节内容
     *
     * @param chapterCode 章节编号
     * @return 结果
     */
    NovelChapterVo selectByChapterCode(String chapterCode);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(NovelChapter record);

    /**
     * 更新内容
     *
     * @param chapterCode 章节编号
     * @param content     内容
     * @return 结果
     */
    int updateContentByChapterCode(@Param("chapterCode") String chapterCode, @Param("content") String content);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(NovelChapter record);

    /**
     * 批量插入记录
     *
     * @param novelChapterList 实体列表
     * @return 插入数量
     */
    Integer batchInsert(List<NovelChapter> novelChapterList);

    /**
     * 根据小说编号查询内容为空的章节
     *
     * @param novelCode 小说编号
     * @return 列表
     */
    List<NovelChapterVo> selectListForEmptyByNovelCode(String novelCode);

    /**
     * 根据小说编号获取最新章节
     *
     * @param novelCode 小说编号
     * @return 章节
     */
    NovelChapterVo selectMaxByNovelCode(String novelCode);

    /**
     * 查询小说章节列表
     *
     * @param novelCode    小说编号
     * @param keyword      关键字
     * @param orderTypeStr 排序
     * @return 列表
     */
    List<NovelChapterVo> selectPageByNovelCode(@Param("novelCode") String novelCode, @Param("keyword") String keyword, @Param("orderTypeStr") String orderTypeStr);
}