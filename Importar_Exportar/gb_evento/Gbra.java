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
@Table(name = "GBRA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbra.findAll", query = "SELECT g FROM Gbra g"),
    @NamedQuery(name = "Gbra.findById", query = "SELECT g FROM Gbra g WHERE g.id = :id"),
    @NamedQuery(name = "Gbra.findByEstabelecimento", query = "SELECT g FROM Gbra g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbra.findByMaterial", query = "SELECT g FROM Gbra g WHERE g.material = :material"),
    @NamedQuery(name = "Gbra.findByRaDoDia", query = "SELECT g FROM Gbra g WHERE g.raDoDia = :raDoDia"),
    @NamedQuery(name = "Gbra.findByNumepedido", query = "SELECT g FROM Gbra g WHERE g.numepedido = :numepedido"),
    @NamedQuery(name = "Gbra.findByEmbalagem", query = "SELECT g FROM Gbra g WHERE g.embalagem = :embalagem"),
    @NamedQuery(name = "Gbra.findByCodfornec", query = "SELECT g FROM Gbra g WHERE g.codfornec = :codfornec")})
public class Gbra implements Serializable {
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
    @Column(name = "RA_DO_DIA", length = 50)
    private String raDoDia;
    @Column(name = "NUMEPEDIDO", length = 50)
    private String numepedido;
    @Column(name = "EMBALAGEM", length = 10)
    private String embalagem;
    @Column(name = "CODFORNEC", length = 15)
    private String codfornec;

    public Gbra() {
    }

    public Gbra(Integer id) {
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

    public String getRaDoDia() {
        return raDoDia;
    }

    public void setRaDoDia(String raDoDia) {
        String oldRaDoDia = this.raDoDia;
        this.raDoDia = raDoDia;
        changeSupport.firePropertyChange("raDoDia", oldRaDoDia, raDoDia);
    }

    public String getNumepedido() {
        return numepedido;
    }

    public void setNumepedido(String numepedido) {
        String oldNumepedido = this.numepedido;
        this.numepedido = numepedido;
        changeSupport.firePropertyChange("numepedido", oldNumepedido, numepedido);
    }

    public String getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(String embalagem) {
        String oldEmbalagem = this.embalagem;
        this.embalagem = embalagem;
        changeSupport.firePropertyChange("embalagem", oldEmbalagem, embalagem);
    }

    public String getCodfornec() {
        return codfornec;
    }

    public void setCodfornec(String codfornec) {
        String oldCodfornec = this.codfornec;
        this.codfornec = codfornec;
        changeSupport.firePropertyChange("codfornec", oldCodfornec, codfornec);
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
        if (!(object instanceof Gbra)) {
            return false;
        }
        Gbra other = (Gbra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbra[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
