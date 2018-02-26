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
@Table(name = "INVENTARIOMB52", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Inventariomb52.findAll", query = "SELECT i FROM Inventariomb52 i"),
    @NamedQuery(name = "Inventariomb52.findById", query = "SELECT i FROM Inventariomb52 i WHERE i.id = :id"),
    @NamedQuery(name = "Inventariomb52.findByMaterial", query = "SELECT i FROM Inventariomb52 i WHERE i.material = :material"),
    @NamedQuery(name = "Inventariomb52.findByEstoque", query = "SELECT i FROM Inventariomb52 i WHERE i.estoque = :estoque"),
    @NamedQuery(name = "Inventariomb52.findByCustound", query = "SELECT i FROM Inventariomb52 i WHERE i.custound = :custound"),
    @NamedQuery(name = "Inventariomb52.findByCustototal", query = "SELECT i FROM Inventariomb52 i WHERE i.custototal = :custototal")})
public class Inventariomb52 implements Serializable {
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
    @Column(name = "ESTOQUE", length = 50)
    private String estoque;
    @Column(name = "CUSTOUND", length = 50)
    private String custound;
    @Column(name = "CUSTOTOTAL", length = 50)
    private String custototal;

    public Inventariomb52() {
    }

    public Inventariomb52(Integer id) {
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

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        String oldEstoque = this.estoque;
        this.estoque = estoque;
        changeSupport.firePropertyChange("estoque", oldEstoque, estoque);
    }

    public String getCustound() {
        return custound;
    }

    public void setCustound(String custound) {
        String oldCustound = this.custound;
        this.custound = custound;
        changeSupport.firePropertyChange("custound", oldCustound, custound);
    }

    public String getCustototal() {
        return custototal;
    }

    public void setCustototal(String custototal) {
        String oldCustototal = this.custototal;
        this.custototal = custototal;
        changeSupport.firePropertyChange("custototal", oldCustototal, custototal);
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
        if (!(object instanceof Inventariomb52)) {
            return false;
        }
        Inventariomb52 other = (Inventariomb52) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Inventariomb52[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
