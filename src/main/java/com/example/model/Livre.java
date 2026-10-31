package com.example.model;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "livres")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Livre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titre", nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auteur_id", nullable = false)
    private Auteur author;

    public Livre() { }
    public Livre(String title) { this.title = title; }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Auteur getAuthor() { return author; }
    public void setAuthor(Auteur author) { this.author = author; }
}
