package com.myaiagent.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

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
        String result = loveAgent.doChat("我是汤姆猫", uuid);

        result = loveAgent.doChat("我喜欢迪丽热巴", uuid);

        result = loveAgent.doChat("我叫什么？", uuid);


    }

    @Test
    void doChatWithResponse() {
        String uuid = UUID.randomUUID().toString();
        LoveAgent.LoverReport result = loveAgent.doChatWithResponse("我是杰瑞，我想让我的来一版更加喜欢我，但是我不知道该怎么办", uuid);
        Assertions.assertNotNull(result);
    }
}