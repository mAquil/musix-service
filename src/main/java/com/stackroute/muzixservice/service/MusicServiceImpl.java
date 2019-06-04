package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    MusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository){
        this.musicRepository=musicRepository;
    }

    @Override
    public Music saveTrack(Music music) {
        Music saveTracks=musicRepository.save(music);
        return saveTracks;
    }

    @Override
    public List<Music> displayAllTracks() {
        return (List<Music>)musicRepository.findAll();
    }

    @Override
    public Music displayTrackById(int id) {
        return null;
    }


    @Override
    public void deleteTracks(int id) {
        musicRepository.deleteById(id);

    }

    @Override
    public Music updateTrack(Music music, int id) {
        Music music1=musicRepository.findById(id).get();
        music1.setTrackComments(music.getTrackComments());
        return music1;
        //return (List<Music>) musicRepository.save(music1);
    }

    @Override
    public List<Music> getTrackByName(String trackName){

        List<Music> lists = null;
        lists = musicRepository.getTrackByName(trackName);
        if (lists.equals(null))
        {
            try {
                throw new TrackNotFoundException("Track not found exception");
            } catch (TrackNotFoundException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }
}
