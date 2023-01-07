package com.cloud.albums.controller;

import com.cloud.albums.model.Album;
import com.cloud.albums.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> listAlbums() {
        return ResponseEntity.ok().body(albumService.getAll());
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Album> listAlbumById(@PathVariable Long albumId) {
        return ResponseEntity.ok().body(albumService.findById(albumId));
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album, @RequestParam Long artistId) {
        Album savedAlbum = albumService.addAlbum(album, artistId);
        return ResponseEntity.created(URI.create("/album/" + savedAlbum.getId())).body(savedAlbum);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long albumId) {
        if (albumService.deleteAlbum(albumId) != -1L) {
            return ResponseEntity.ok().body("Album with the id: " + albumId + " was succesfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }
    
    
}
