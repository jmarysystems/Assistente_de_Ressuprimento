/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gb_evento;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "OPIDENTIFICADA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Opidentificada.findAll", query = "SELECT o FROM Opidentificada o"),
    @NamedQuery(name = "Opidentificada.findById", query = "SELECT o FROM Opidentificada o WHERE o.id = :id"),
    @NamedQuery(name = "Opidentificada.findByMaterial", query = "SELECT o FROM Opidentificada o WHERE o.material = :material"),
    @NamedQuery(name = "Opidentificada.findByDataIdent", query = "SELECT o FROM Opidentificada o WHERE o.dataIdent = :dataIdent"),
    @NamedQuery(name = "Opidentificada.findByOpidentificada", query = "SELECT o FROM Opidentificada o WHERE o.opidentificada = :opidentificada")})
public class Opidentificada implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "DATA_IDENT", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataIdent;
    @Column(name = "OPIDENTIFICADA", length = 300)
    private String opidentificada;

    public Opidentificada() {
    }

    public Opidentificada(Integer id) {
        this.id = id;
    }

    public Opidentificada(Integer id, Date dataIdent) {
        this.id = id;
        this.dataIdent = dataIdent;
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

    public Date getDataIdent() {
        return dataIdent;
    }

    public void setDataIdent(Date dataIdent) {
        Date oldDataIdent = this.dataIdent;
        this.dataIdent = dataIdent;
        changeSupport.firePropertyChange("dataIdent", oldDataIdent, dataIdent);
    }

    public String getOpidentificada() {
        return opidentificada;
    }

    public void setOpidentificada(String opidentificada) {
        String oldOpidentificada = this.opidentificada;
        this.opidentificada = opidentificada;
        changeSupport.firePropertyChange("opidentificada", oldOpidentificada, opidentificada);
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
        if (!(object instanceof Opidentificada)) {
            return false;
        }
        Opidentificada other = (Opidentificada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Opidentificada[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
