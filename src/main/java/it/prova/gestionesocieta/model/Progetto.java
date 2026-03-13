package it.prova.gestionesocieta.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "progetto")
public class Progetto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cliente")
    private String cliente;
    @Column (name = "duratainmesi")
    private int durataInMesi;

    @ManyToMany (fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "progetti")
    private Set<Dipendente> dipendenti = new HashSet<>();

    public Progetto(String nome, String cliente, int durataInMesi) {
        this.nome = nome;
        this.cliente = cliente;
        this.durataInMesi = durataInMesi;
    }

    public Progetto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getDurataInMesi() {
        return durataInMesi;
    }

    public void setDurataInMesi(int durataInMesi) {
        this.durataInMesi = durataInMesi;
    }

    public Set<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(Set<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    @Override
    public String toString() {
        return "Progetto{" +
                "durataInMesi=" + durataInMesi +
                ", cliente='" + cliente + '\'' +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}
