package com.paulo.arquivos_virtuais.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
public class Diretorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @ManyToOne
    @JoinColumn(name = "parent_diretorio_id")
    private Diretorio parentDiretorio;

    @OneToMany(mappedBy = "parentDiretorio", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Diretorio> subDiretorios = new ArrayList<>();

    @OneToMany(mappedBy = "diretorio", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<File> files = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Diretorio diretorio = (Diretorio) o;
        return getId() != null && Objects.equals(getId(), diretorio.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}