package com.myaiagent.rag.loader;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/27/ 19:09
 */
@SpringBootTest
class LoveAgentDocumentLoaderTest {

    @Resource
    private LoveAgentDocumentLoader loader;

    @Test
    void getDocument() {
        loader.loadDocuments();
    }
}