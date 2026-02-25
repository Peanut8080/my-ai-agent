package com.myaiagent.agent;

import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.content.Media;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/23/ 22:59
 */
@SpringBootTest
class LoveAgentTest {

    @Resource
    private LoveAgent loveAgent;

    @Test
    void doChat() {
        String uuid = UUID.randomUUID().toString();
        String result = loveAgent.doChat("你是哪个版本呢？", uuid);

//        String result = loveAgent.doChat("我是汤姆猫", uuid);
//
//        result = loveAgent.doChat("我喜欢迪丽热巴", uuid);
//
//        result = loveAgent.doChat("我叫什么？", uuid);
    }

    @Test
    void doChatWithResponse() {
        String uuid = UUID.randomUUID().toString();
        LoveAgent.LoverReport result = loveAgent.doChatWithResponse("我是杰瑞，我想让我的另一半更加喜欢我，但是我不知道该怎么办", uuid);
        Assertions.assertNotNull(result);
    }

    @Test
    void doChatMultimodal() {
        try {
            String result = loveAgent.doChatMultimodal(null, null, null);
            System.out.println(result);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}