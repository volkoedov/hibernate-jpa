package vea.home.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    public Actor(String name) {
        this.name = name;
    }

    protected Actor() {
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        movie.getActors().add(this);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
        movie.getActors().remove(this);
    }
}
