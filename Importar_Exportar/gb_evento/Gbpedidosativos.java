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
@Table(name = "GBPEDIDOSATIVOS", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbpedidosativos.findAll", query = "SELECT g FROM Gbpedidosativos g"),
    @NamedQuery(name = "Gbpedidosativos.findById", query = "SELECT g FROM Gbpedidosativos g WHERE g.id = :id"),
    @NamedQuery(name = "Gbpedidosativos.findByEstabelecimento", query = "SELECT g FROM Gbpedidosativos g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbpedidosativos.findByMaterial", query = "SELECT g FROM Gbpedidosativos g WHERE g.material = :material"),
    @NamedQuery(name = "Gbpedidosativos.findByPedidoCd184Ativo", query = "SELECT g FROM Gbpedidosativos g WHERE g.pedidoCd184Ativo = :pedidoCd184Ativo"),
    @NamedQuery(name = "Gbpedidosativos.findByPedidoCd184AtivoRemessa", query = "SELECT g FROM Gbpedidosativos g WHERE g.pedidoCd184AtivoRemessa = :pedidoCd184AtivoRemessa"),
    @NamedQuery(name = "Gbpedidosativos.findByPedidoCd184AtivoSaiu", query = "SELECT g FROM Gbpedidosativos g WHERE g.pedidoCd184AtivoSaiu = :pedidoCd184AtivoSaiu"),
    @NamedQuery(name = "Gbpedidosativos.findByPedidoCd184AtivoEntradaLoja", query = "SELECT g FROM Gbpedidosativos g WHERE g.pedidoCd184AtivoEntradaLoja = :pedidoCd184AtivoEntradaLoja"),
    @NamedQuery(name = "Gbpedidosativos.findByNumepedido", query = "SELECT g FROM Gbpedidosativos g WHERE g.numepedido = :numepedido"),
    @NamedQuery(name = "Gbpedidosativos.findByEs", query = "SELECT g FROM Gbpedidosativos g WHERE g.es = :es"),
    @NamedQuery(name = "Gbpedidosativos.findByCodfornec", query = "SELECT g FROM Gbpedidosativos g WHERE g.codfornec = :codfornec")})
public class Gbpedidosativos implements Serializable {
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
    @Column(name = "PEDIDO_CD184_ATIVO", length = 50)
    private String pedidoCd184Ativo;
    @Column(name = "PEDIDO_CD184_ATIVO_REMESSA", length = 50)
    private String pedidoCd184AtivoRemessa;
    @Column(name = "PEDIDO_CD184_ATIVO_SAIU", length = 50)
    private String pedidoCd184AtivoSaiu;
    @Column(name = "PEDIDO_CD184_ATIVO_ENTRADA_LOJA", length = 50)
    private String pedidoCd184AtivoEntradaLoja;
    @Column(name = "NUMEPEDIDO", length = 50)
    private String numepedido;
    @Column(name = "ES", length = 10)
    private String es;
    @Column(name = "CODFORNEC", length = 15)
    private String codfornec;

    public Gbpedidosativos() {
    }

    public Gbpedidosativos(Integer id) {
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

    public String getPedidoCd184Ativo() {
        return pedidoCd184Ativo;
    }

    public void setPedidoCd184Ativo(String pedidoCd184Ativo) {
        String oldPedidoCd184Ativo = this.pedidoCd184Ativo;
        this.pedidoCd184Ativo = pedidoCd184Ativo;
        changeSupport.firePropertyChange("pedidoCd184Ativo", oldPedidoCd184Ativo, pedidoCd184Ativo);
    }

    public String getPedidoCd184AtivoRemessa() {
        return pedidoCd184AtivoRemessa;
    }

    public void setPedidoCd184AtivoRemessa(String pedidoCd184AtivoRemessa) {
        String oldPedidoCd184AtivoRemessa = this.pedidoCd184AtivoRemessa;
        this.pedidoCd184AtivoRemessa = pedidoCd184AtivoRemessa;
        changeSupport.firePropertyChange("pedidoCd184AtivoRemessa", oldPedidoCd184AtivoRemessa, pedidoCd184AtivoRemessa);
    }

    public String getPedidoCd184AtivoSaiu() {
        return pedidoCd184AtivoSaiu;
    }

    public void setPedidoCd184AtivoSaiu(String pedidoCd184AtivoSaiu) {
        String oldPedidoCd184AtivoSaiu = this.pedidoCd184AtivoSaiu;
        this.pedidoCd184AtivoSaiu = pedidoCd184AtivoSaiu;
        changeSupport.firePropertyChange("pedidoCd184AtivoSaiu", oldPedidoCd184AtivoSaiu, pedidoCd184AtivoSaiu);
    }

    public String getPedidoCd184AtivoEntradaLoja() {
        return pedidoCd184AtivoEntradaLoja;
    }

    public void setPedidoCd184AtivoEntradaLoja(String pedidoCd184AtivoEntradaLoja) {
        String oldPedidoCd184AtivoEntradaLoja = this.pedidoCd184AtivoEntradaLoja;
        this.pedidoCd184AtivoEntradaLoja = pedidoCd184AtivoEntradaLoja;
        changeSupport.firePropertyChange("pedidoCd184AtivoEntradaLoja", oldPedidoCd184AtivoEntradaLoja, pedidoCd184AtivoEntradaLoja);
    }

    public String getNumepedido() {
        return numepedido;
    }

    public void setNumepedido(String numepedido) {
        String oldNumepedido = this.numepedido;
        this.numepedido = numepedido;
        changeSupport.firePropertyChange("numepedido", oldNumepedido, numepedido);
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        String oldEs = this.es;
        this.es = es;
        changeSupport.firePropertyChange("es", oldEs, es);
    }

    public String getCodfornec() {
        return codfornec;
    }

    public void setCodfornec(String codfornec) {
        String oldCodfornec = this.codfornec;
        this.codfornec = codfornec;
        changeSupport.firePropertyChange("codfornec", oldCodfornec, codfornec);
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
        if (!(object instanceof Gbpedidosativos)) {
            return false;
        }
        Gbpedidosativos other = (Gbpedidosativos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Gbpedidosativos[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
