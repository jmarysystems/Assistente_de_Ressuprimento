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
@Table(name = "ENDERECOESPACOGONDULA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Enderecoespacogondula.findAll", query = "SELECT e FROM Enderecoespacogondula e"),
    @NamedQuery(name = "Enderecoespacogondula.findById", query = "SELECT e FROM Enderecoespacogondula e WHERE e.id = :id"),
    @NamedQuery(name = "Enderecoespacogondula.findByEstabelecimento", query = "SELECT e FROM Enderecoespacogondula e WHERE e.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Enderecoespacogondula.findByMaterial", query = "SELECT e FROM Enderecoespacogondula e WHERE e.material = :material"),
    @NamedQuery(name = "Enderecoespacogondula.findByRua", query = "SELECT e FROM Enderecoespacogondula e WHERE e.rua = :rua"),
    @NamedQuery(name = "Enderecoespacogondula.findByGondula", query = "SELECT e FROM Enderecoespacogondula e WHERE e.gondula = :gondula"),
    @NamedQuery(name = "Enderecoespacogondula.findByPrateleira", query = "SELECT e FROM Enderecoespacogondula e WHERE e.prateleira = :prateleira"),
    @NamedQuery(name = "Enderecoespacogondula.findByAltura", query = "SELECT e FROM Enderecoespacogondula e WHERE e.altura = :altura"),
    @NamedQuery(name = "Enderecoespacogondula.findByFrente", query = "SELECT e FROM Enderecoespacogondula e WHERE e.frente = :frente"),
    @NamedQuery(name = "Enderecoespacogondula.findByFundo", query = "SELECT e FROM Enderecoespacogondula e WHERE e.fundo = :fundo")})
public class Enderecoespacogondula implements Serializable {
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
    @Column(name = "RUA", length = 300)
    private String rua;
    @Column(name = "GONDULA", length = 300)
    private String gondula;
    @Column(name = "PRATELEIRA", length = 300)
    private String prateleira;
    @Column(name = "ALTURA", length = 300)
    private String altura;
    @Column(name = "FRENTE", length = 300)
    private String frente;
    @Column(name = "FUNDO", length = 300)
    private String fundo;

    public Enderecoespacogondula() {
    }

    public Enderecoespacogondula(Integer id) {
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        String oldRua = this.rua;
        this.rua = rua;
        changeSupport.firePropertyChange("rua", oldRua, rua);
    }

    public String getGondula() {
        return gondula;
    }

    public void setGondula(String gondula) {
        String oldGondula = this.gondula;
        this.gondula = gondula;
        changeSupport.firePropertyChange("gondula", oldGondula, gondula);
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        String oldPrateleira = this.prateleira;
        this.prateleira = prateleira;
        changeSupport.firePropertyChange("prateleira", oldPrateleira, prateleira);
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        String oldAltura = this.altura;
        this.altura = altura;
        changeSupport.firePropertyChange("altura", oldAltura, altura);
    }

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        String oldFrente = this.frente;
        this.frente = frente;
        changeSupport.firePropertyChange("frente", oldFrente, frente);
    }

    public String getFundo() {
        return fundo;
    }

    public void setFundo(String fundo) {
        String oldFundo = this.fundo;
        this.fundo = fundo;
        changeSupport.firePropertyChange("fundo", oldFundo, fundo);
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
        if (!(object instanceof Enderecoespacogondula)) {
            return false;
        }
        Enderecoespacogondula other = (Enderecoespacogondula) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Enderecoespacogondula[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
