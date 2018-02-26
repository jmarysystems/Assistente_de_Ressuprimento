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
@Table(name = "INVENTARIOZLMR06", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Inventariozlmr06.findAll", query = "SELECT i FROM Inventariozlmr06 i"),
    @NamedQuery(name = "Inventariozlmr06.findById", query = "SELECT i FROM Inventariozlmr06 i WHERE i.id = :id"),
    @NamedQuery(name = "Inventariozlmr06.findByMaterial", query = "SELECT i FROM Inventariozlmr06 i WHERE i.material = :material"),
    @NamedQuery(name = "Inventariozlmr06.findByQtdcontado", query = "SELECT i FROM Inventariozlmr06 i WHERE i.qtdcontado = :qtdcontado"),
    @NamedQuery(name = "Inventariozlmr06.findByCustound", query = "SELECT i FROM Inventariozlmr06 i WHERE i.custound = :custound"),
    @NamedQuery(name = "Inventariozlmr06.findByCustototal", query = "SELECT i FROM Inventariozlmr06 i WHERE i.custototal = :custototal")})
public class Inventariozlmr06 implements Serializable {
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
    @Column(name = "QTDCONTADO", length = 50)
    private String qtdcontado;
    @Column(name = "CUSTOUND", length = 50)
    private String custound;
    @Column(name = "CUSTOTOTAL", length = 50)
    private String custototal;

    public Inventariozlmr06() {
    }

    public Inventariozlmr06(Integer id) {
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

    public String getQtdcontado() {
        return qtdcontado;
    }

    public void setQtdcontado(String qtdcontado) {
        String oldQtdcontado = this.qtdcontado;
        this.qtdcontado = qtdcontado;
        changeSupport.firePropertyChange("qtdcontado", oldQtdcontado, qtdcontado);
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
        if (!(object instanceof Inventariozlmr06)) {
            return false;
        }
        Inventariozlmr06 other = (Inventariozlmr06) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Inventariozlmr06[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
