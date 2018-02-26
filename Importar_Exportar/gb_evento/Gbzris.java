/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gb_evento;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "GBZRIS", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbzris.findAll", query = "SELECT g FROM Gbzris g"),
    @NamedQuery(name = "Gbzris.findById", query = "SELECT g FROM Gbzris g WHERE g.id = :id"),
    @NamedQuery(name = "Gbzris.findByEstabelecimento", query = "SELECT g FROM Gbzris g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbzris.findByMaterial", query = "SELECT g FROM Gbzris g WHERE g.material = :material"),
    @NamedQuery(name = "Gbzris.findByQtdxemb", query = "SELECT g FROM Gbzris g WHERE g.qtdxemb = :qtdxemb"),
    @NamedQuery(name = "Gbzris.findByEstoqueMinimo", query = "SELECT g FROM Gbzris g WHERE g.estoqueMinimo = :estoqueMinimo"),
    @NamedQuery(name = "Gbzris.findByVendaSemanaAnterior", query = "SELECT g FROM Gbzris g WHERE g.vendaSemanaAnterior = :vendaSemanaAnterior"),
    @NamedQuery(name = "Gbzris.findByVenda4Semanas", query = "SELECT g FROM Gbzris g WHERE g.venda4Semanas = :venda4Semanas"),
    @NamedQuery(name = "Gbzris.findByVenda12Semanas", query = "SELECT g FROM Gbzris g WHERE g.venda12Semanas = :venda12Semanas"),
    @NamedQuery(name = "Gbzris.findByDde", query = "SELECT g FROM Gbzris g WHERE g.dde = :dde"),
    @NamedQuery(name = "Gbzris.findByDisponibilidade", query = "SELECT g FROM Gbzris g WHERE g.disponibilidade = :disponibilidade"),
    @NamedQuery(name = "Gbzris.findByVendaSemanaAtual", query = "SELECT g FROM Gbzris g WHERE g.vendaSemanaAtual = :vendaSemanaAtual"),
    @NamedQuery(name = "Gbzris.findByPontoreabastecimento", query = "SELECT g FROM Gbzris g WHERE g.pontoreabastecimento = :pontoreabastecimento"),
    @NamedQuery(name = "Gbzris.findByEstoqueesperado", query = "SELECT g FROM Gbzris g WHERE g.estoqueesperado = :estoqueesperado"),
    @NamedQuery(name = "Gbzris.findByPrecocusto", query = "SELECT g FROM Gbzris g WHERE g.precocusto = :precocusto"),
    @NamedQuery(name = "Gbzris.findByCluster", query = "SELECT g FROM Gbzris g WHERE g.cluster = :cluster")})
public class Gbzris implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ESTABELECIMENTO", length = 300)
    private String estabelecimento;
    @Column(name = "MATERIAL", length = 300)
    private String material;
    @Column(name = "QTDXEMB", length = 50)
    private String qtdxemb;
    @Column(name = "ESTOQUE_MINIMO")
    private Integer estoqueMinimo;
    @Column(name = "VENDA_SEMANA_ANTERIOR")
    private Integer vendaSemanaAnterior;
    @Column(name = "VENDA_4_SEMANAS")
    private Integer venda4Semanas;
    @Column(name = "VENDA_12_SEMANAS")
    private Integer venda12Semanas;
    @Column(name = "DDE")
    private Integer dde;
    @Column(name = "DISPONIBILIDADE")
    private Integer disponibilidade;
    @Column(name = "VENDA_SEMANA_ATUAL")
    private Integer vendaSemanaAtual;
    @Column(name = "PONTOREABASTECIMENTO")
    private Integer pontoreabastecimento;
    @Column(name = "ESTOQUEESPERADO")
    private Integer estoqueesperado;
    @Column(name = "PRECOCUSTO", length = 40)
    private String precocusto;
    @Column(name = "CLUSTER", length = 10)
    private String cluster;

    public Gbzris() {
    }

    public Gbzris(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        String oldEstabelecimento = this.estabelecimento;
        this.estabelecimento = estabelecimento;
        changeSupport.firePropertyChange("estabelecimento", oldEstabelecimento, estabelecimento);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public String getQtdxemb() {
        return qtdxemb;
    }

    public void setQtdxemb(String qtdxemb) {
        String oldQtdxemb = this.qtdxemb;
        this.qtdxemb = qtdxemb;
        changeSupport.firePropertyChange("qtdxemb", oldQtdxemb, qtdxemb);
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        Integer oldEstoqueMinimo = this.estoqueMinimo;
        this.estoqueMinimo = estoqueMinimo;
        changeSupport.firePropertyChange("estoqueMinimo", oldEstoqueMinimo, estoqueMinimo);
    }

    public Integer getVendaSemanaAnterior() {
        return vendaSemanaAnterior;
    }

    public void setVendaSemanaAnterior(Integer vendaSemanaAnterior) {
        Integer oldVendaSemanaAnterior = this.vendaSemanaAnterior;
        this.vendaSemanaAnterior = vendaSemanaAnterior;
        changeSupport.firePropertyChange("vendaSemanaAnterior", oldVendaSemanaAnterior, vendaSemanaAnterior);
    }

    public Integer getVenda4Semanas() {
        return venda4Semanas;
    }

    public void setVenda4Semanas(Integer venda4Semanas) {
        Integer oldVenda4Semanas = this.venda4Semanas;
        this.venda4Semanas = venda4Semanas;
        changeSupport.firePropertyChange("venda4Semanas", oldVenda4Semanas, venda4Semanas);
    }

    public Integer getVenda12Semanas() {
        return venda12Semanas;
    }

    public void setVenda12Semanas(Integer venda12Semanas) {
        Integer oldVenda12Semanas = this.venda12Semanas;
        this.venda12Semanas = venda12Semanas;
        changeSupport.firePropertyChange("venda12Semanas", oldVenda12Semanas, venda12Semanas);
    }

    public Integer getDde() {
        return dde;
    }

    public void setDde(Integer dde) {
        Integer oldDde = this.dde;
        this.dde = dde;
        changeSupport.firePropertyChange("dde", oldDde, dde);
    }

    public Integer getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Integer disponibilidade) {
        Integer oldDisponibilidade = this.disponibilidade;
        this.disponibilidade = disponibilidade;
        changeSupport.firePropertyChange("disponibilidade", oldDisponibilidade, disponibilidade);
    }

    public Integer getVendaSemanaAtual() {
        return vendaSemanaAtual;
    }

    public void setVendaSemanaAtual(Integer vendaSemanaAtual) {
        Integer oldVendaSemanaAtual = this.vendaSemanaAtual;
        this.vendaSemanaAtual = vendaSemanaAtual;
        changeSupport.firePropertyChange("vendaSemanaAtual", oldVendaSemanaAtual, vendaSemanaAtual);
    }

    public Integer getPontoreabastecimento() {
        return pontoreabastecimento;
    }

    public void setPontoreabastecimento(Integer pontoreabastecimento) {
        Integer oldPontoreabastecimento = this.pontoreabastecimento;
        this.pontoreabastecimento = pontoreabastecimento;
        changeSupport.firePropertyChange("pontoreabastecimento", oldPontoreabastecimento, pontoreabastecimento);
    }

    public Integer getEstoqueesperado() {
        return estoqueesperado;
    }

    public void setEstoqueesperado(Integer estoqueesperado) {
        Integer oldEstoqueesperado = this.estoqueesperado;
        this.estoqueesperado = estoqueesperado;
        changeSupport.firePropertyChange("estoqueesperado", oldEstoqueesperado, estoqueesperado);
    }

    public String getPrecocusto() {
        return precocusto;
    }

    public void setPrecocusto(String precocusto) {
        String oldPrecocusto = this.precocusto;
        this.precocusto = precocusto;
        changeSupport.firePropertyChange("precocusto", oldPrecocusto, precocusto);
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        String oldCluster = this.cluster;
        this.cluster = cluster;
        changeSupport.firePropertyChange("cluster", oldCluster, cluster);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gbzris)) {
            return false;
        }
        Gbzris other = (Gbzris) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbzris[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
