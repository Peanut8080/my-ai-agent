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
//        String result = loveAgent.doChat("我目前没有交到对象，社交上有点问题，你能给我推荐几个课程嘛？", uuid);
        String result = loveAgent.doChat("我想要找一个性格温柔、阳光、顾家、可以提供权限价值的另一半，有合适的人选嘛？", uuid);
        System.out.println(result);

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

    @Test
    void doChatWithTools() {
            // 测试联网搜索问题的答案
            testMessage("周末想带女朋友去上海约会，推荐几个适合情侣的小众打卡地？");

            // 测试网页抓取：恋爱案例分析
            testMessage("最近和对象吵架了，看看编程导航网站（codefather.cn）的其他情侣是怎么解决矛盾的？");

            // 测试资源下载：图片下载
            testMessage("直接下载一张适合做手机壁纸的星空情侣图片为文件");

            // 测试终端操作：执行代码
            testMessage("执行 Python3 脚本来生成数据分析报告");

            // 测试文件操作：保存用户档案
            testMessage("保存我的恋爱档案为文件");

            // 测试 PDF 生成
            testMessage("生成一份‘七夕约会计划’PDF，包含餐厅预订、活动流程和礼物清单");
        }

        private void testMessage(String message) {
            String chatId = UUID.randomUUID().toString();
            String answer = loveAgent.doChatWithTools(message, chatId);
            Assertions.assertNotNull(answer);
        }
}