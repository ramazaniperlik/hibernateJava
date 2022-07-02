package com.ramazaniperlik.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.util.List;

@Entity
@Table(name="songs")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "releaseyear")
    @NonNull
    private int releaseyear;

    @OneToMany(mappedBy = "song")
    private List<Lyric> lyrics;

    @ManyToMany
    @JoinTable(name = "artist_song",joinColumns = @JoinColumn(name ="songid"),inverseJoinColumns = @JoinColumn(name ="artistid"))
    private List<Artist> artists;
}
