package com.example.model;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "auteurs")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedEntityGraph(name = "Auteur.books", attributeNodes = @NamedAttributeNode("books"))
public class Auteur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Les noms de colonnes restent identiques pour preserver le schema et les captures du TP.
    @Column(name = "nom", nullable = false)
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Livre> books = new ArrayList<>();

    public Auteur() { }
    public Auteur(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Livre> getBooks() { return books; }
    public void ajouterLivre(Livre livre) {
        Objects.requireNonNull(livre);
        if (livre.getAuthor() != null && livre.getAuthor() != this)
            throw new IllegalArgumentException("Le livre appartient deja a un autre auteur");
        if (!books.contains(livre)) books.add(livre);
        livre.setAuthor(this);
    }
    public void retirerLivre(Livre livre) {
        if (books.remove(livre)) livre.setAuthor(null);
    }
}
