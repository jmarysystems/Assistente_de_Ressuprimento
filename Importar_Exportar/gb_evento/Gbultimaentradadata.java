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
@Table(name = "GBULTIMAENTRADADATA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbultimaentradadata.findAll", query = "SELECT g FROM Gbultimaentradadata g"),
    @NamedQuery(name = "Gbultimaentradadata.findById", query = "SELECT g FROM Gbultimaentradadata g WHERE g.id = :id"),
    @NamedQuery(name = "Gbultimaentradadata.findByEstabelecimento", query = "SELECT g FROM Gbultimaentradadata g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbultimaentradadata.findByMaterial", query = "SELECT g FROM Gbultimaentradadata g WHERE g.material = :material"),
    @NamedQuery(name = "Gbultimaentradadata.findByDataUltimaentrada", query = "SELECT g FROM Gbultimaentradadata g WHERE g.dataUltimaentrada = :dataUltimaentrada"),
    @NamedQuery(name = "Gbultimaentradadata.findByLof", query = "SELECT g FROM Gbultimaentradadata g WHERE g.lof = :lof")})
public class Gbultimaentradadata implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ESTABELECIMENTO", length = 50)
    private String estabelecimento;
    @Column(name = "MATERIAL", length = 50)
    private String material;
    @Column(name = "DATA_ULTIMAENTRADA")
    @Temporal(TemporalType.DATE)
    private Date dataUltimaentrada;
    @Column(name = "LOF", length = 50)
    private String lof;

    public Gbultimaentradadata() {
    }

    public Gbultimaentradadata(Integer id) {
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

    public Date getDataUltimaentrada() {
        return dataUltimaentrada;
    }

    public void setDataUltimaentrada(Date dataUltimaentrada) {
        Date oldDataUltimaentrada = this.dataUltimaentrada;
        this.dataUltimaentrada = dataUltimaentrada;
        changeSupport.firePropertyChange("dataUltimaentrada", oldDataUltimaentrada, dataUltimaentrada);
    }

    public String getLof() {
        return lof;
    }

    public void setLof(String lof) {
        String oldLof = this.lof;
        this.lof = lof;
        changeSupport.firePropertyChange("lof", oldLof, lof);
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
        if (!(object instanceof Gbultimaentradadata)) {
            return false;
        }
        Gbultimaentradadata other = (Gbultimaentradadata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbultimaentradadata[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
