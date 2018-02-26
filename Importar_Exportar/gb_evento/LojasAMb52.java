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
@Table(name = "LOJAS_A_MB52", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "LojasAMb52.findAll", query = "SELECT l FROM LojasAMb52 l"),
    @NamedQuery(name = "LojasAMb52.findById", query = "SELECT l FROM LojasAMb52 l WHERE l.id = :id"),
    @NamedQuery(name = "LojasAMb52.findByLoja", query = "SELECT l FROM LojasAMb52 l WHERE l.loja = :loja"),
    @NamedQuery(name = "LojasAMb52.findByDep", query = "SELECT l FROM LojasAMb52 l WHERE l.dep = :dep"),
    @NamedQuery(name = "LojasAMb52.findByMaterial", query = "SELECT l FROM LojasAMb52 l WHERE l.material = :material"),
    @NamedQuery(name = "LojasAMb52.findByDescricao", query = "SELECT l FROM LojasAMb52 l WHERE l.descricao = :descricao"),
    @NamedQuery(name = "LojasAMb52.findByUtilizacaoLivre", query = "SELECT l FROM LojasAMb52 l WHERE l.utilizacaoLivre = :utilizacaoLivre"),
    @NamedQuery(name = "LojasAMb52.findByUmb", query = "SELECT l FROM LojasAMb52 l WHERE l.umb = :umb"),
    @NamedQuery(name = "LojasAMb52.findByValorUtilizacaoLivre", query = "SELECT l FROM LojasAMb52 l WHERE l.valorUtilizacaoLivre = :valorUtilizacaoLivre"),
    @NamedQuery(name = "LojasAMb52.findByGrpMerc", query = "SELECT l FROM LojasAMb52 l WHERE l.grpMerc = :grpMerc"),
    @NamedQuery(name = "LojasAMb52.findByStatusEstoque", query = "SELECT l FROM LojasAMb52 l WHERE l.statusEstoque = :statusEstoque")})
public class LojasAMb52 implements Serializable {

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
    @Column(name = "DEP", length = 10)
    private String dep;
    @Column(name = "MATERIAL", length = 600)
    private String material;
    @Column(name = "DESCRICAO", length = 600)
    private String descricao;
    @Column(name = "UTILIZACAO_LIVRE", length = 40)
    private String utilizacaoLivre;
    @Column(name = "UMB", length = 10)
    private String umb;
    @Column(name = "VALOR_UTILIZACAO_LIVRE", length = 30)
    private String valorUtilizacaoLivre;
    @Column(name = "GRP_MERC", length = 20)
    private String grpMerc;
    @Column(name = "STATUS_ESTOQUE", length = 90)
    private String statusEstoque;

    public LojasAMb52() {
    }

    public LojasAMb52(Integer id) {
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

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        String oldDep = this.dep;
        this.dep = dep;
        changeSupport.firePropertyChange("dep", oldDep, dep);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public String getUtilizacaoLivre() {
        return utilizacaoLivre;
    }

    public void setUtilizacaoLivre(String utilizacaoLivre) {
        String oldUtilizacaoLivre = this.utilizacaoLivre;
        this.utilizacaoLivre = utilizacaoLivre;
        changeSupport.firePropertyChange("utilizacaoLivre", oldUtilizacaoLivre, utilizacaoLivre);
    }

    public String getUmb() {
        return umb;
    }

    public void setUmb(String umb) {
        String oldUmb = this.umb;
        this.umb = umb;
        changeSupport.firePropertyChange("umb", oldUmb, umb);
    }

    public String getValorUtilizacaoLivre() {
        return valorUtilizacaoLivre;
    }

    public void setValorUtilizacaoLivre(String valorUtilizacaoLivre) {
        String oldValorUtilizacaoLivre = this.valorUtilizacaoLivre;
        this.valorUtilizacaoLivre = valorUtilizacaoLivre;
        changeSupport.firePropertyChange("valorUtilizacaoLivre", oldValorUtilizacaoLivre, valorUtilizacaoLivre);
    }

    public String getGrpMerc() {
        return grpMerc;
    }

    public void setGrpMerc(String grpMerc) {
        String oldGrpMerc = this.grpMerc;
        this.grpMerc = grpMerc;
        changeSupport.firePropertyChange("grpMerc", oldGrpMerc, grpMerc);
    }

    public String getStatusEstoque() {
        return statusEstoque;
    }

    public void setStatusEstoque(String statusEstoque) {
        String oldStatusEstoque = this.statusEstoque;
        this.statusEstoque = statusEstoque;
        changeSupport.firePropertyChange("statusEstoque", oldStatusEstoque, statusEstoque);
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
        if (!(object instanceof LojasAMb52)) {
            return false;
        }
        LojasAMb52 other = (LojasAMb52) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.LojasAMb52[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
