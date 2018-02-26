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
@Table(name = "GBESTOQUECDB001", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbestoquecdb001.findAll", query = "SELECT g FROM Gbestoquecdb001 g"),
    @NamedQuery(name = "Gbestoquecdb001.findById", query = "SELECT g FROM Gbestoquecdb001 g WHERE g.id = :id"),
    @NamedQuery(name = "Gbestoquecdb001.findByEstabelecimento", query = "SELECT g FROM Gbestoquecdb001 g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbestoquecdb001.findByMaterial", query = "SELECT g FROM Gbestoquecdb001 g WHERE g.material = :material"),
    @NamedQuery(name = "Gbestoquecdb001.findByEstoqueCd001", query = "SELECT g FROM Gbestoquecdb001 g WHERE g.estoqueCd001 = :estoqueCd001")})
public class Gbestoquecdb001 implements Serializable {
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
    @Column(name = "ESTOQUE_CD_001")
    private Integer estoqueCd001;

    public Gbestoquecdb001() {
    }

    public Gbestoquecdb001(Integer id) {
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

    public Integer getEstoqueCd001() {
        return estoqueCd001;
    }

    public void setEstoqueCd001(Integer estoqueCd001) {
        Integer oldEstoqueCd001 = this.estoqueCd001;
        this.estoqueCd001 = estoqueCd001;
        changeSupport.firePropertyChange("estoqueCd001", oldEstoqueCd001, estoqueCd001);
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
        if (!(object instanceof Gbestoquecdb001)) {
            return false;
        }
        Gbestoquecdb001 other = (Gbestoquecdb001) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbestoquecdb001[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
