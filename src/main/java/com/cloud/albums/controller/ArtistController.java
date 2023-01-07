package com.cloud.albums.controller;

import com.cloud.albums.model.Artist;
import com.cloud.albums.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<Artist>> listArtists() {
        return ResponseEntity.ok().body(artistService.getAll());
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> listArtistById(@PathVariable Long artistId) {
        return ResponseEntity.ok().body(artistService.findById(artistId));
    }

    @PostMapping
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        Artist savedArtist = artistService.addArtist(artist);
        return ResponseEntity.created(URI.create("/artist/" + savedArtist.getId())).body(savedArtist);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long artistId) {
        if (artistService.deleteArtist(artistId) != -1L) {
            return ResponseEntity.ok().body("Artist with the id: " + artistId + " was succesfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }
}
