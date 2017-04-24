package com.cartisan.java8.examples.chapter9;

import com.cartisan.java8.examples.chapter1.Album;
import com.cartisan.java8.examples.chapter1.Artist;
import com.cartisan.java8.examples.chapter1.Track;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableAlbumLookup implements AlbumLookup {
    private static final ExecutorService service = Executors.newFixedThreadPool(4);
    private final List<Track> tracks;
    private final List<Artist> artists;

    public CompletableAlbumLookup(List<Track> tracks, List<Artist> artists) {
        this.tracks = tracks;
        this.artists = artists;
    }

    @Override
    public Album lookupByName(String albumName) {
        CompletableFuture<List<Artist>> artistLookup = loginTo("artist")
                .thenCompose(artistLogin -> lookupArtists(albumName, artistLogin));

        return loginTo("track")
                .thenCompose(trackLogin->lookupTracks(albumName, trackLogin))
                .thenCombine(artistLookup, (tracks, artists)->new Album(albumName, tracks, artists)).join();
    }

    private Track track;
    private Artist artist;

    CompletableFuture<Track> lookupTrack(String id) {
        return CompletableFuture.supplyAsync(() -> track, service);
    }

    CompletableFuture<Artist> createFuture(String id) {
        CompletableFuture<Artist> future = new CompletableFuture<>();
        startJob(future);
        return future;
    }

    private void startJob(CompletableFuture<Artist> future) {
        future.complete(artist);
    }

    private void processExceptionally(CompletableFuture<Album> future, String name) {
        future.completeExceptionally(new AlbumLookupException("Unable to find " + name));
    }

    private CompletableFuture<List<Artist>> lookupArtists(String albumName, Credentials credentials) {
        return CompletableFuture.completedFuture(artists);
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CompletableFuture<List<Track>> lookupTracks(String albumName, Credentials credentials) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return tracks;
        }, service);
    }

    private CompletableFuture<Credentials> loginTo(String serviceName) {
        return CompletableFuture.supplyAsync(() -> {
            if ("artist".equals(serviceName)) {
                sleep(1000);
            }
            return new Credentials();
        }, service);
    }
}
