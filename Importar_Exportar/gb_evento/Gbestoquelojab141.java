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
@Table(name = "GBESTOQUELOJAB141", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbestoquelojab141.findAll", query = "SELECT g FROM Gbestoquelojab141 g"),
    @NamedQuery(name = "Gbestoquelojab141.findById", query = "SELECT g FROM Gbestoquelojab141 g WHERE g.id = :id"),
    @NamedQuery(name = "Gbestoquelojab141.findByEstabelecimento", query = "SELECT g FROM Gbestoquelojab141 g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbestoquelojab141.findByMaterial", query = "SELECT g FROM Gbestoquelojab141 g WHERE g.material = :material"),
    @NamedQuery(name = "Gbestoquelojab141.findByEstoqueLoja", query = "SELECT g FROM Gbestoquelojab141 g WHERE g.estoqueLoja = :estoqueLoja")})
public class Gbestoquelojab141 implements Serializable {
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
    @Column(name = "ESTOQUE_LOJA")
    private Integer estoqueLoja;

    public Gbestoquelojab141() {
    }

    public Gbestoquelojab141(Integer id) {
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

    public Integer getEstoqueLoja() {
        return estoqueLoja;
    }

    public void setEstoqueLoja(Integer estoqueLoja) {
        Integer oldEstoqueLoja = this.estoqueLoja;
        this.estoqueLoja = estoqueLoja;
        changeSupport.firePropertyChange("estoqueLoja", oldEstoqueLoja, estoqueLoja);
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
        if (!(object instanceof Gbestoquelojab141)) {
            return false;
        }
        Gbestoquelojab141 other = (Gbestoquelojab141) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbestoquelojab141[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
