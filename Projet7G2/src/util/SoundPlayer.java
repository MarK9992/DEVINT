package util;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import t2s.SIVOXDevint;

/**
 * Created by Marc KARASSEV on 12/05/2014.
 */
public final class SoundPlayer {

    private static SoundPlayer instance;

    private Sound bumpSound, switchSound;
    private final String MALEPATH = "../ressources/sound/homme/", FEMALEPATH = "../ressources/sound/femme/";
    private SIVOXDevint voix;

    private SoundPlayer() {
        voix = new SIVOXDevint();
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

    public void say(String wav) {
        voix.stop();
        //try {
            //Sound sound;
            if(Preferences.getCurrentVoice() == 1) {
                voix.playWav(MALEPATH + wav);
                //sound = new Sound(MALEPATH + wav);
            }
            else {
                voix.playWav(FEMALEPATH + wav);
                //sound = new Sound(FEMALEPATH + wav);
            }
            //sound.play();
        /*} catch (SlickException e) {
            e.printStackTrace();
        }*/
    }
}
