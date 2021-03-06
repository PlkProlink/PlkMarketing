package br.com.tiagods.model.implantacao;

import br.com.tiagods.config.init.UsuarioLogado;
import br.com.tiagods.model.Usuario;
import br.com.tiagods.model.Departamento;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@MappedSuperclass
@Embeddable
public class ImplantacaoEtapa{
    public enum Etapa{
        //ETAPA(-1,"Todos"),
        PRIMEIRA(1,"Primeira"),SEGUNDA(2,"Segunda"),TERCEIRA(3,"Terceira");
        //QUARTA(4),QUINTA(5),SEXTA(6),SETIMA(7),OITAVA(8),NONA(9),DECIMA(10)
        private int valor;
        private String nome;
        Etapa(Integer valor,String nome){
            this.valor=valor;
            this.nome=nome;
        }

        public int getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return this.nome;
        }
    }
    @Enumerated(EnumType.STRING)
    private Etapa etapa = Etapa.PRIMEIRA;
    @Transient
    private Usuario responsavel;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    private String descricao;

    private int tempo = 15;
    @ManyToOne
    @JoinColumn(name = "atividade_id")
    private ImplantacaoAtividade atividade;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criado_em")
    private Calendar criadoEm;
    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    private Usuario criadoPor;

    public Etapa getEtapa() {
        return etapa;
    }


    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ImplantacaoAtividade getAtividade() {
        return atividade;
    }

    public void setAtividade(ImplantacaoAtividade atividade) {
        this.atividade = atividade;
    }

    public Calendar getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Calendar criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Usuario getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Usuario criadoPor) {
        this.criadoPor = criadoPor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImplantacaoEtapa that = (ImplantacaoEtapa) o;
        return etapa == that.etapa &&
                Objects.equals(atividade, that.atividade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(etapa, atividade);
    }
}
