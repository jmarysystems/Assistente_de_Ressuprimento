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
@Table(name = "GBULTIMAENTRADA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbultimaentrada.findAll", query = "SELECT g FROM Gbultimaentrada g"),
    @NamedQuery(name = "Gbultimaentrada.findById", query = "SELECT g FROM Gbultimaentrada g WHERE g.id = :id"),
    @NamedQuery(name = "Gbultimaentrada.findByMaterial", query = "SELECT g FROM Gbultimaentrada g WHERE g.material = :material"),
    @NamedQuery(name = "Gbultimaentrada.findByUltimaentrada", query = "SELECT g FROM Gbultimaentrada g WHERE g.ultimaentrada = :ultimaentrada")})
public class Gbultimaentrada implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "MATERIAL", length = 50)
    private String material;
    @Column(name = "ULTIMAENTRADA", length = 50)
    private String ultimaentrada;

    public Gbultimaentrada() {
    }

    public Gbultimaentrada(Integer id) {
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public String getUltimaentrada() {
        return ultimaentrada;
    }

    public void setUltimaentrada(String ultimaentrada) {
        String oldUltimaentrada = this.ultimaentrada;
        this.ultimaentrada = ultimaentrada;
        changeSupport.firePropertyChange("ultimaentrada", oldUltimaentrada, ultimaentrada);
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
        if (!(object instanceof Gbultimaentrada)) {
            return false;
        }
        Gbultimaentrada other = (Gbultimaentrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbultimaentrada[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
