package top.jihongchang.feiyan;

import java.util.List;

public class AudioListPlayer {

    public void play(List<String> audioList) {
        for (String audio : audioList) {
            new AudioPlayer().play(audio);
        }
    }

}
