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
@Table(name = "GBPRODUTOPORFORNECEDOR", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbprodutoporfornecedor.findAll", query = "SELECT g FROM Gbprodutoporfornecedor g"),
    @NamedQuery(name = "Gbprodutoporfornecedor.findById", query = "SELECT g FROM Gbprodutoporfornecedor g WHERE g.id = :id"),
    @NamedQuery(name = "Gbprodutoporfornecedor.findByEstabelecimento", query = "SELECT g FROM Gbprodutoporfornecedor g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbprodutoporfornecedor.findByMaterial", query = "SELECT g FROM Gbprodutoporfornecedor g WHERE g.material = :material"),
    @NamedQuery(name = "Gbprodutoporfornecedor.findByCodigofornecedor", query = "SELECT g FROM Gbprodutoporfornecedor g WHERE g.codigofornecedor = :codigofornecedor")})
public class Gbprodutoporfornecedor implements Serializable {
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
    @Column(name = "CODIGOFORNECEDOR", length = 300)
    private String codigofornecedor;

    public Gbprodutoporfornecedor() {
    }

    public Gbprodutoporfornecedor(Integer id) {
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

    public String getCodigofornecedor() {
        return codigofornecedor;
    }

    public void setCodigofornecedor(String codigofornecedor) {
        String oldCodigofornecedor = this.codigofornecedor;
        this.codigofornecedor = codigofornecedor;
        changeSupport.firePropertyChange("codigofornecedor", oldCodigofornecedor, codigofornecedor);
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
        if (!(object instanceof Gbprodutoporfornecedor)) {
            return false;
        }
        Gbprodutoporfornecedor other = (Gbprodutoporfornecedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbprodutoporfornecedor[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
