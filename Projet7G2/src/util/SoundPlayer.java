package util;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Created by Marc KARASSEV on 12/05/2014.
 */
public final class SoundPlayer {

    private static SoundPlayer instance;

    private static Sound bumpSound, switchSound;

    private SoundPlayer() {
        try {
            bumpSound = new Sound("../ressources/sound/smb_bump.wav");
            switchSound = new Sound("../ressources/sound/smb2_enter_door.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static SoundPlayer getInstance() {
        if(instance == null) {
            synchronized (SoundPlayer.class) {
                if(instance == null) {
                    instance = new SoundPlayer();
                }
            }
        }
        return instance;
    }

    public void playBump() {
        if (!bumpSound.playing()) {
            bumpSound.play();
        }
    }

    public void playSwitch() {
        switchSound.play();
    }
}
