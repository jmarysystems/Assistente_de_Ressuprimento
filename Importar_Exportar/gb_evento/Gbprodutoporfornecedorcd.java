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
@Table(name = "GBPRODUTOPORFORNECEDORCD", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbprodutoporfornecedorcd.findAll", query = "SELECT g FROM Gbprodutoporfornecedorcd g"),
    @NamedQuery(name = "Gbprodutoporfornecedorcd.findById", query = "SELECT g FROM Gbprodutoporfornecedorcd g WHERE g.id = :id"),
    @NamedQuery(name = "Gbprodutoporfornecedorcd.findByMaterial", query = "SELECT g FROM Gbprodutoporfornecedorcd g WHERE g.material = :material"),
    @NamedQuery(name = "Gbprodutoporfornecedorcd.findByCodigofornecedor", query = "SELECT g FROM Gbprodutoporfornecedorcd g WHERE g.codigofornecedor = :codigofornecedor"),
    @NamedQuery(name = "Gbprodutoporfornecedorcd.findByNomefornecedor", query = "SELECT g FROM Gbprodutoporfornecedorcd g WHERE g.nomefornecedor = :nomefornecedor")})
public class Gbprodutoporfornecedorcd implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "MATERIAL", length = 300)
    private String material;
    @Column(name = "CODIGOFORNECEDOR", length = 300)
    private String codigofornecedor;
    @Column(name = "NOMEFORNECEDOR", length = 300)
    private String nomefornecedor;

    public Gbprodutoporfornecedorcd() {
    }

    public Gbprodutoporfornecedorcd(Integer id) {
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

    public String getCodigofornecedor() {
        return codigofornecedor;
    }

    public void setCodigofornecedor(String codigofornecedor) {
        String oldCodigofornecedor = this.codigofornecedor;
        this.codigofornecedor = codigofornecedor;
        changeSupport.firePropertyChange("codigofornecedor", oldCodigofornecedor, codigofornecedor);
    }

    public String getNomefornecedor() {
        return nomefornecedor;
    }

    public void setNomefornecedor(String nomefornecedor) {
        String oldNomefornecedor = this.nomefornecedor;
        this.nomefornecedor = nomefornecedor;
        changeSupport.firePropertyChange("nomefornecedor", oldNomefornecedor, nomefornecedor);
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
        if (!(object instanceof Gbprodutoporfornecedorcd)) {
            return false;
        }
        Gbprodutoporfornecedorcd other = (Gbprodutoporfornecedorcd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbprodutoporfornecedorcd[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
