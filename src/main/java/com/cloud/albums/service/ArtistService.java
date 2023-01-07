package com.cloud.albums.service;

import com.cloud.albums.model.Artist;
import com.cloud.albums.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;


    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    public Artist findById(Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Long deleteArtist(Long artistId) {
        if (artistRepository.existsById(artistId)) {
            artistRepository.deleteById(artistId);
            return artistId;
        }

        throw new DataAccessException("Artist not found") {};
    }
}
