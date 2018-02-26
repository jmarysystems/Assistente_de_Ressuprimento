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
 * @author admin
 */
@Entity
@Table(name = "GBQUANTIDADEVENDIDA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbquantidadevendida.findAll", query = "SELECT g FROM Gbquantidadevendida g")
    , @NamedQuery(name = "Gbquantidadevendida.findById", query = "SELECT g FROM Gbquantidadevendida g WHERE g.id = :id")
    , @NamedQuery(name = "Gbquantidadevendida.findByEstabelecimento", query = "SELECT g FROM Gbquantidadevendida g WHERE g.estabelecimento = :estabelecimento")
    , @NamedQuery(name = "Gbquantidadevendida.findByMaterial", query = "SELECT g FROM Gbquantidadevendida g WHERE g.material = :material")
    , @NamedQuery(name = "Gbquantidadevendida.findByQuantidade", query = "SELECT g FROM Gbquantidadevendida g WHERE g.quantidade = :quantidade")
    , @NamedQuery(name = "Gbquantidadevendida.findByQuantidade2", query = "SELECT g FROM Gbquantidadevendida g WHERE g.quantidade2 = :quantidade2")})
public class Gbquantidadevendida implements Serializable {

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
    @Column(name = "MATERIAL", length = 70)
    private String material;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTIDADE2", precision = 52)
    private Double quantidade2;

    public Gbquantidadevendida() {
    }

    public Gbquantidadevendida(Integer id) {
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        Integer oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public Double getQuantidade2() {
        return quantidade2;
    }

    public void setQuantidade2(Double quantidade2) {
        Double oldQuantidade2 = this.quantidade2;
        this.quantidade2 = quantidade2;
        changeSupport.firePropertyChange("quantidade2", oldQuantidade2, quantidade2);
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
        if (!(object instanceof Gbquantidadevendida)) {
            return false;
        }
        Gbquantidadevendida other = (Gbquantidadevendida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Gbquantidadevendida[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
