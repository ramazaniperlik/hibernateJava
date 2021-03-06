package com.ramazaniperlik.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lyric")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Lyric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name = "content")
    @NonNull
    private String content;

    @ManyToOne
    @JoinColumn(name ="songid")
    private Song song;
}
