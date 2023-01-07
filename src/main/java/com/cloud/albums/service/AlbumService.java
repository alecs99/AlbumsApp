package com.cloud.albums.service;

import com.cloud.albums.model.Album;
import com.cloud.albums.model.Artist;
import com.cloud.albums.repository.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistService artistService;

    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    public Album findById(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Album addAlbum(Album album, Long artistId) {
        Artist artist = artistService.findById(artistId);
        album.setArtist(artist);

        return albumRepository.save(album);
    }

    public Long deleteAlbum(Long albumId) {
        if (albumRepository.existsById(albumId)) {
            albumRepository.deleteById(albumId);
            return albumId;
        }

        throw new DataAccessException("Artist not found") {};
    }

}
