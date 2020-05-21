package com.xin.daily.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mybatis通用工具类
 *
 * @author creator mafh 2019/12/3 13:57
 * @author updater
 * @version 1.0.0
 */
@Component
public class MybatisUtils {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据库连接模板类
     */
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 批量保存
     *
     * @param list          数据列表
     * @param classPath     类路径
     * @param rangeStandard 范围标准
     * @param <T>           泛型实体
     * @return 保存数量
     */
    public <T> Integer batchInsert(List<T> list, String classPath, Integer rangeStandard) {
        // 判空
        if (null == rangeStandard || rangeStandard <= 0) {
            rangeStandard = 1000;
        }
        // 存放结果
        int result = 0;
        // 新获取一个模式为BATCH，自动提交为false的session
        // 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            // 分段session保存
            int rounds = list.size() / rangeStandard + 1;
            for (int i = 0; i < rounds; i++) {
                List<T> subList = list.subList(i * rangeStandard, Math.min((i + 1) * rangeStandard, list.size()));
                // 具体循环处理
                for (T t : subList) {
                    session.insert(classPath, t);
                }
                // 手动提交，提交后无法回滚
                session.commit();
                // 清理缓存，防止溢出
                session.clearCache();
                // 收集结果
                result += subList.size();
            }
        } catch (Exception e) {
            // 没有提交的数据可以回滚
            session.rollback();
            logger.error("批量保存异常，错误：{}", e.getMessage());
        } finally {
            session.close();
        }
        return result;
    }
}
