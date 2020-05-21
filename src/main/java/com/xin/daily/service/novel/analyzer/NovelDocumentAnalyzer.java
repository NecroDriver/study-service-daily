package com.xin.daily.service.novel.analyzer;

import com.xin.web.utils.jsoup.BaseDocumentAnalyzer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小说文档解析器
 *
 * @author creator mafh 2019/12/2 14:21
 * @author updater
 * @version 1.0.0
 */
@Component
public class NovelDocumentAnalyzer extends BaseDocumentAnalyzer {

    /**
     * 根据html文档对象获取List<Map>
     *
     * @param document 文档
     * @return 列表
     */
    @Override
    public List<Map<String, Object>> getMapList(Document document) {

        List<Map<String, Object>> results = new ArrayList<>();
        Assert.notNull(document, "文档对象不能为空！");
        // 解析html文档
        Elements elements = document.getElementsByTag("dd");
        // 拆解具体元素 忽略前12个最近章节
        for (int i = 12; i < elements.size(); i++) {
            // 获取具体元素
            Element element = elements.get(i);
            // 存放结果
            Map<String, Object> result = new HashMap<>();
            result.put("chapterName", element.getElementsByTag("a").get(0).text());
            result.put("url", element.getElementsByTag("a").get(0).attr("href"));
            results.add(result);
        }
        return results;
    }

    /**
     * 根据html文档对象获取Map
     *
     * @param document 文档
     * @return 数据
     */
    @Override
    public Map<String, Object> getMap(Document document) {
        Map<String, Object> result = new HashMap<>();
        Assert.notNull(document, "文档对象不能为空！");
        result.put("content", document.body().getElementById("content").text());
        return result;
    }
}
