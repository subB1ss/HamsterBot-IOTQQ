package cn.hamster3.bot.test;

import cn.hamster3.bot.core.BotCore;
import cn.hamster3.bot.preset.thread.TimeLimitThread;
import cn.hamster3.bot.utils.MessageUtils;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class BotTest {
    private BotCore botCore;

    @Before
    public void init() throws URISyntaxException {
        botCore = new BotCore("localhost", 8888, 2644895480L);
    }

    @Test
    public void sendGroupMessage() throws IOException {
        JsonObject object = botCore.sendMessage(
                MessageUtils.sendImageToGroup(612955360, "", new URL("https://www.hamster3.cn/images/bg3.jpg"))
        );
        System.out.println(object.toString());
    }

    @Test
    public void textImage() throws IOException {
        JsonObject object = botCore.sendMessage(
                MessageUtils.sendTextToGroup(767089578, "test")
        );
        System.out.println(object.toString());
    }

    @Test
    public void timeLimit() {
        new TimeLimitThread(3000) {
            @Override
            public void run() {
                int i = 0;
                while (!isFinished()) {
                    System.out.println(i++);
                }
            }
        }.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
