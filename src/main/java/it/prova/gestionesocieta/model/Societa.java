package it.prova.gestionesocieta.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "societa")
public class Societa {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "ragionesociale")
    private String ragioneSociale;
    @Column (name = "indirizzo")
    private String Indirizzo;
    @Column (name = "datafondazione")
    private LocalDate dataFondazione;
    @Column (name = "datachiusura")
    private LocalDate dataChiusura;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "societa" )
    private Set<Dipendente> dipendenti = new HashSet<>();

    public Societa(String ragioneSociale, String indirizzo, LocalDate dataFondazione, LocalDate dataChiusura) {
        this.ragioneSociale = ragioneSociale;
        Indirizzo = indirizzo;
        this.dataFondazione = dataFondazione;
        this.dataChiusura = dataChiusura;
    }

    public Societa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }

    public LocalDate getDataFondazione() {
        return dataFondazione;
    }

    public void setDataFondazione(LocalDate dataFondazione) {
        this.dataFondazione = dataFondazione;
    }

    public LocalDate getDataChiusura() {
        return dataChiusura;
    }

    public void setDataChiusura(LocalDate dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public Set<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(Set<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    @Override
    public String toString() {
        return "Societa{" +
                "dataChiusura=" + dataChiusura +
                ", dataFondazione=" + dataFondazione +
                ", Indirizzo='" + Indirizzo + '\'' +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", id=" + id +
                '}';
    }
}
