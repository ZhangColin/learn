package com.cartisan.java8.examples.chapter9;

import com.cartisan.java8.examples.chapter1.Artist;
import rx.Observable;
import rx.Observer;

import java.util.List;
import java.util.stream.Collectors;

public class RxExamples {
    private final List<Artist> savedArtists;
    private final List<String> savedArtistNames;

    public RxExamples(List<Artist> savedArtists) {
        this.savedArtists = savedArtists;
        savedArtistNames = savedArtists.stream().map(Artist::getName).collect(Collectors.toList());
    }

    public Observable<Artist> search(String searchedName,
                                     String searchedNationality,
                                     int maxResults) {
        return getSavedArtists()
                .filter(name -> name.contains(searchedName))
                .flatMap(this::lookupArtist)
                .filter(artist -> artist.getNationality().contains(searchedNationality))
                .take(maxResults);
    }

    private Observable<String> getSavedArtists() {
        return Observable.from(savedArtistNames);
    }

    private Observable<Artist> lookupArtist(String name) {
        Artist required = savedArtists.stream().filter(artist -> artist.getName().equals(name)).findFirst().get();

        return Observable.from(required);
    }

    public void creationCodeSample() {
        Observer<String> observer = null;

        observer.onNext("a");
        observer.onNext("b");
        observer.onNext("c");

        observer.onCompleted();

        observer.onError(new Exception());
    }
}
