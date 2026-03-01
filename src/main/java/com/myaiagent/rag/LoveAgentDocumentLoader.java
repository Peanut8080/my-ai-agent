package com.myaiagent.rag;

import com.myaiagent.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档加载器
 *
 * @author tanghua
 * @date: 2026/02/27/ 18:57
 */
@Component
@Slf4j
public class LoveAgentDocumentLoader {

    private final ResourcePatternResolver resourcePatternResolver;

    /**
     * 使用构造器函数注入
     *
     * @param patternResolver 资源加载器
     */
    LoveAgentDocumentLoader(ResourcePatternResolver patternResolver) {
        this.resourcePatternResolver = patternResolver;
    }

    /**
     * 使用 markdownReader 加载文档
     *
     * @return 文档切片集合
     */
    public List<Document> loadDocuments() {
        List<Document> documents = new ArrayList<>();
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath:/document/*.md");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                MarkdownDocumentReaderConfig readerConfig = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
                        .withAdditionalMetadata("filename", filename)
                        .build();
                MarkdownDocumentReader markdownDocumentReader = new MarkdownDocumentReader(resource, readerConfig);
                documents.addAll(markdownDocumentReader.get());
            }
        } catch (Throwable e) {
            throw new BusinessException("加载文档异常");
        }
        return documents;
    }
}
