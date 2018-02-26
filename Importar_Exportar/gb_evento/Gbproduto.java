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
@Table(name = "GBPRODUTO", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbproduto.findAll", query = "SELECT g FROM Gbproduto g"),
    @NamedQuery(name = "Gbproduto.findById", query = "SELECT g FROM Gbproduto g WHERE g.id = :id"),
    @NamedQuery(name = "Gbproduto.findByMaterial", query = "SELECT g FROM Gbproduto g WHERE g.material = :material"),
    @NamedQuery(name = "Gbproduto.findByEan", query = "SELECT g FROM Gbproduto g WHERE g.ean = :ean"),
    @NamedQuery(name = "Gbproduto.findByDescricao", query = "SELECT g FROM Gbproduto g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "Gbproduto.findByUnd", query = "SELECT g FROM Gbproduto g WHERE g.und = :und"),
    @NamedQuery(name = "Gbproduto.findByGrupo", query = "SELECT g FROM Gbproduto g WHERE g.grupo = :grupo"),
    @NamedQuery(name = "Gbproduto.findBySubgrupo", query = "SELECT g FROM Gbproduto g WHERE g.subgrupo = :subgrupo"),
    @NamedQuery(name = "Gbproduto.findByQtdxes", query = "SELECT g FROM Gbproduto g WHERE g.qtdxes = :qtdxes")})
public class Gbproduto implements Serializable {
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
    @Column(name = "EAN", length = 300)
    private String ean;
    @Column(name = "DESCRICAO", length = 300)
    private String descricao;
    @Column(name = "UND", length = 50)
    private String und;
    @Column(name = "GRUPO", length = 50)
    private String grupo;
    @Column(name = "SUBGRUPO", length = 50)
    private String subgrupo;
    @Column(name = "QTDXES", length = 50)
    private String qtdxes;

    public Gbproduto() {
    }

    public Gbproduto(Integer id) {
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

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        String oldEan = this.ean;
        this.ean = ean;
        changeSupport.firePropertyChange("ean", oldEan, ean);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public String getUnd() {
        return und;
    }

    public void setUnd(String und) {
        String oldUnd = this.und;
        this.und = und;
        changeSupport.firePropertyChange("und", oldUnd, und);
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        String oldGrupo = this.grupo;
        this.grupo = grupo;
        changeSupport.firePropertyChange("grupo", oldGrupo, grupo);
    }

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        String oldSubgrupo = this.subgrupo;
        this.subgrupo = subgrupo;
        changeSupport.firePropertyChange("subgrupo", oldSubgrupo, subgrupo);
    }

    public String getQtdxes() {
        return qtdxes;
    }

    public void setQtdxes(String qtdxes) {
        String oldQtdxes = this.qtdxes;
        this.qtdxes = qtdxes;
        changeSupport.firePropertyChange("qtdxes", oldQtdxes, qtdxes);
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
        if (!(object instanceof Gbproduto)) {
            return false;
        }
        Gbproduto other = (Gbproduto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbproduto[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
