package com.myaiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author tanghua
 * @date: 2026/03/01/ 15:56
 */
@Configuration
public class LoverAgentVectorStoreConfig {

    @Resource
    private LoveAgentDocumentLoader loveAgentDocumentLoader;

    @Bean
    public VectorStore loverVectorStore(EmbeddingModel dashScopeEmbeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashScopeEmbeddingModel).build();
        simpleVectorStore.doAdd(loveAgentDocumentLoader.loadDocuments());
        return simpleVectorStore;
    }
}
