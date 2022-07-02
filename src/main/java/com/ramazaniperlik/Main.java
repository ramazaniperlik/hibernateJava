package com.ramazaniperlik;

import com.ramazaniperlik.entity.Artist;
import com.ramazaniperlik.entity.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        Controller controller  = new Controller();

      //  controller.addArtist("Ramazan");
//        controller.removeArtist("Artist10");
       // controller.removeArtist("Artist20");

        controller.addSong("Wonders at your",2005);
        Song song = controller.getSong("Wonders at your feat");
        controller.addLyrics(song,"lyriclyriclyric");

    }
}
