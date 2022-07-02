package com.ramazaniperlik;

import com.ramazaniperlik.entity.Artist;
import com.ramazaniperlik.entity.Lyric;
import com.ramazaniperlik.entity.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class Controller {

    SessionFactory sessionFactory;

    public Controller() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Song.class)
                .addAnnotatedClass(Artist.class)
                .addAnnotatedClass(Lyric.class)
                .buildSessionFactory();
    }

    public void removeArtist(String name) {
        try (var currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            var result = queryArtist(currentSession, name);
            currentSession.delete(result);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("removeArtist").info(e.getMessage());
        }
    }

    public void updateArtist(String oldName, String newName) {
        try (var currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            var result = queryArtist(currentSession, oldName);
            result.setName(newName);
            currentSession.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger("UpdateArtist").info(ex.getMessage());

        }
    }

    public void addArtist(String name) {

        try (var currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            var newArtist = new Artist(name);
            currentSession.save(newArtist);
            currentSession.getTransaction().commit();

        } catch (Exception ex) {
            Logger.getLogger("AddArtist").info(ex.getMessage());

        }
    }

    public Artist queryArtist(Session session, String name) {
        String query = "from Artist a where a.name=:name";
        var result = session.createQuery(query, Artist.class)
                .setParameter("name", name)
                .getResultList();

        if (result.size() > 0)
            return result.get(0);
        else
            return null;
    }

    public void addSong(String songName, int releaseYear) {
        try (var currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            Song newSong = new Song(songName, releaseYear);
            currentSession.save(newSong);
            currentSession.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger("AddSong").info(ex.getMessage());

        }
    }

    public Song getSong(String songName) {
        try (var currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            String query = "from Song s where s.name like :name";
            var songs = currentSession.createQuery(query, Song.class)
                    .setParameter("name", "%" + songName + "%")
                    .getResultList();
            if (songs.size() > 0)
                return songs.get(0);
            else
                return null;
        } catch (Exception ex) {
            Logger.getLogger("getSong").info(ex.getMessage());
            return null;
        }
    }

    public void addLyrics(Song song, String content) {
        try(var currentSession = sessionFactory.getCurrentSession()){
            currentSession.beginTransaction();
            Lyric lyric = new Lyric(content);
            song.getLyrics().add(lyric);
            lyric.setSong(song);
            currentSession.save(lyric);
            currentSession.getTransaction().commit();

        }catch(Exception ex){
            Logger.getLogger("AddArtist").info(ex.getMessage());

        }
    }
}
