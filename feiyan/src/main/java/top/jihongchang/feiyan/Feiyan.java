package top.jihongchang.feiyan;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Feiyan {

    private static final int SECONDS_BEFORE_ALL_START = 10; //几秒后开始

    private static final int SECONDS_PER_GROUP = 20; //每组持续（单位：秒）

    private static final int REST_BETWEEN_GROUP_BASE = 10; //组间间隔（单位：秒），每往后1组可以多歇1秒

    private static final int HOW_MANY_GROUP = 20; //一共做几组

    private static AudioListPlayer AUDIO_LIST_PLAYER = new AudioListPlayer();

    public static void main(String[] args) throws InterruptedException {

        String pathnameFormat = "E:\\record\\2022\\11\\21\\%s.wav";

        TimeUnit.SECONDS.sleep(SECONDS_BEFORE_ALL_START);

        new AudioPlayer().play(String.format(pathnameFormat, "开始"));

        for (int j = 0; j < HOW_MANY_GROUP; j++) {

            Instant start = Instant.now();

            if (j != 0) {

                new Thread(()->{
                    new AudioPlayer().play(String.format(pathnameFormat, "开始"));
                }).start();

            }

            TimeUnit.SECONDS.sleep(SECONDS_PER_GROUP - 10);

            for (int i = 10; i > 0; i--) {

                int finalI = i;
                new Thread(()->{
                    new AudioPlayer().play(String.format(pathnameFormat, finalI + ""));
                }).start();

                TimeUnit.SECONDS.sleep(1);

            }

            int finalJ = j + 1;
            new Thread(() -> {

                List<String> list = new ArrayList<>();
                list.add(String.format(pathnameFormat, "第"));

                if (finalJ <= 10) {
                    list.add(String.format(pathnameFormat, finalJ + ""));

                } else {

                    if (finalJ < 20) {

                        list.add(String.format(pathnameFormat, "10"));

                    } else {

                        list.add(String.format(pathnameFormat, finalJ / 10 + ""));
                        list.add(String.format(pathnameFormat, "10"));

                    }

                    if (finalJ % 10 != 0) {
                        list.add(String.format(pathnameFormat, finalJ % 10 + ""));
                    }

                }

                list.add(String.format(pathnameFormat, "组"));
                list.add(String.format(pathnameFormat, "结束"));

                AUDIO_LIST_PLAYER.play(list);

            }).start();

            Instant end = Instant.now();

            System.out.println(Duration.between(start, end).getSeconds());

            if (j < HOW_MANY_GROUP - 1) {

                TimeUnit.SECONDS.sleep(REST_BETWEEN_GROUP_BASE + j);

            }

        }

        new AudioPlayer().play(String.format(pathnameFormat, "全程结束"));

    }

}
