package com.myaiagent.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 云知识库配置类
 *
 * @author tanghua
 * @date: 2026/03/01/ 16:58
 */
@Configuration
public class LoveRagCloudAdvisorConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String agentApiKey;

    @Bean
    Advisor loveRagCloudAdvisor() {
        DocumentRetriever retriever = new DashScopeDocumentRetriever(
                DashScopeApi.builder().apiKey(agentApiKey).build(),
                DashScopeDocumentRetrieverOptions.builder()
                        .indexName("用户画像")
                        .build());
        return RetrievalAugmentationAdvisor.builder().documentRetriever(retriever).build();
    }
}
