package com.ramazaniperlik.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="name")
    @NonNull
    private String name;

    @ManyToMany()
    @JoinTable(name ="artist_song",joinColumns = @JoinColumn(name="artistid"), inverseJoinColumns = @JoinColumn (name ="songid"))
    private List<Song> songs;

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
