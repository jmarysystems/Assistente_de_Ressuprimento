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
@Table(name = "LOJAS_A_ZVTRA_SORT", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "LojasAZvtraSort.findAll", query = "SELECT l FROM LojasAZvtraSort l"),
    @NamedQuery(name = "LojasAZvtraSort.findById", query = "SELECT l FROM LojasAZvtraSort l WHERE l.id = :id"),
    @NamedQuery(name = "LojasAZvtraSort.findByLoja", query = "SELECT l FROM LojasAZvtraSort l WHERE l.loja = :loja"),
    @NamedQuery(name = "LojasAZvtraSort.findByMaterial", query = "SELECT l FROM LojasAZvtraSort l WHERE l.material = :material"),
    @NamedQuery(name = "LojasAZvtraSort.findByMrpZvtra", query = "SELECT l FROM LojasAZvtraSort l WHERE l.mrpZvtra = :mrpZvtra"),
    @NamedQuery(name = "LojasAZvtraSort.findBySmZvtra", query = "SELECT l FROM LojasAZvtraSort l WHERE l.smZvtra = :smZvtra"),
    @NamedQuery(name = "LojasAZvtraSort.findByStatusBloqueio", query = "SELECT l FROM LojasAZvtraSort l WHERE l.statusBloqueio = :statusBloqueio")})
public class LojasAZvtraSort implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "LOJA", length = 10)
    private String loja;
    @Column(name = "MATERIAL", length = 30)
    private String material;
    @Column(name = "MRP_ZVTRA", length = 10)
    private String mrpZvtra;
    @Column(name = "SM_ZVTRA", length = 10)
    private String smZvtra;
    @Column(name = "STATUS_BLOQUEIO", length = 30)
    private String statusBloqueio;

    public LojasAZvtraSort() {
    }

    public LojasAZvtraSort(Integer id) {
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

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        String oldLoja = this.loja;
        this.loja = loja;
        changeSupport.firePropertyChange("loja", oldLoja, loja);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public String getMrpZvtra() {
        return mrpZvtra;
    }

    public void setMrpZvtra(String mrpZvtra) {
        String oldMrpZvtra = this.mrpZvtra;
        this.mrpZvtra = mrpZvtra;
        changeSupport.firePropertyChange("mrpZvtra", oldMrpZvtra, mrpZvtra);
    }

    public String getSmZvtra() {
        return smZvtra;
    }

    public void setSmZvtra(String smZvtra) {
        String oldSmZvtra = this.smZvtra;
        this.smZvtra = smZvtra;
        changeSupport.firePropertyChange("smZvtra", oldSmZvtra, smZvtra);
    }

    public String getStatusBloqueio() {
        return statusBloqueio;
    }

    public void setStatusBloqueio(String statusBloqueio) {
        String oldStatusBloqueio = this.statusBloqueio;
        this.statusBloqueio = statusBloqueio;
        changeSupport.firePropertyChange("statusBloqueio", oldStatusBloqueio, statusBloqueio);
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
        if (!(object instanceof LojasAZvtraSort)) {
            return false;
        }
        LojasAZvtraSort other = (LojasAZvtraSort) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.LojasAZvtraSort[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
