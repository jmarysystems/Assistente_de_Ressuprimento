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
@Table(name = "GBDIRETOLOJA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbdiretoloja.findAll", query = "SELECT g FROM Gbdiretoloja g"),
    @NamedQuery(name = "Gbdiretoloja.findById", query = "SELECT g FROM Gbdiretoloja g WHERE g.id = :id"),
    @NamedQuery(name = "Gbdiretoloja.findByFornecedor", query = "SELECT g FROM Gbdiretoloja g WHERE g.fornecedor = :fornecedor"),
    @NamedQuery(name = "Gbdiretoloja.findByMaterial", query = "SELECT g FROM Gbdiretoloja g WHERE g.material = :material"),
    @NamedQuery(name = "Gbdiretoloja.findByInputzris", query = "SELECT g FROM Gbdiretoloja g WHERE g.inputzris = :inputzris"),
    @NamedQuery(name = "Gbdiretoloja.findByPedidogerado", query = "SELECT g FROM Gbdiretoloja g WHERE g.pedidogerado = :pedidogerado")})
public class Gbdiretoloja implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "FORNECEDOR", length = 300)
    private String fornecedor;
    @Column(name = "MATERIAL", length = 300)
    private String material;
    @Column(name = "INPUTZRIS")
    private Integer inputzris;
    @Column(name = "PEDIDOGERADO")
    private Integer pedidogerado;

    public Gbdiretoloja() {
    }

    public Gbdiretoloja(Integer id) {
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

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        String oldFornecedor = this.fornecedor;
        this.fornecedor = fornecedor;
        changeSupport.firePropertyChange("fornecedor", oldFornecedor, fornecedor);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public Integer getInputzris() {
        return inputzris;
    }

    public void setInputzris(Integer inputzris) {
        Integer oldInputzris = this.inputzris;
        this.inputzris = inputzris;
        changeSupport.firePropertyChange("inputzris", oldInputzris, inputzris);
    }

    public Integer getPedidogerado() {
        return pedidogerado;
    }

    public void setPedidogerado(Integer pedidogerado) {
        Integer oldPedidogerado = this.pedidogerado;
        this.pedidogerado = pedidogerado;
        changeSupport.firePropertyChange("pedidogerado", oldPedidogerado, pedidogerado);
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
        if (!(object instanceof Gbdiretoloja)) {
            return false;
        }
        Gbdiretoloja other = (Gbdiretoloja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbdiretoloja[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
