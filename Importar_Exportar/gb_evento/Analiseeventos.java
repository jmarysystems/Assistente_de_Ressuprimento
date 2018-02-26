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
@Table(name = "ANALISEEVENTOS", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Analiseeventos.findAll", query = "SELECT a FROM Analiseeventos a"),
    @NamedQuery(name = "Analiseeventos.findById", query = "SELECT a FROM Analiseeventos a WHERE a.id = :id"),
    @NamedQuery(name = "Analiseeventos.findByMaterial", query = "SELECT a FROM Analiseeventos a WHERE a.material = :material"),
    @NamedQuery(name = "Analiseeventos.findByQtdEmbalagAposta", query = "SELECT a FROM Analiseeventos a WHERE a.qtdEmbalagAposta = :qtdEmbalagAposta"),
    @NamedQuery(name = "Analiseeventos.findByVendaAnoAnterior", query = "SELECT a FROM Analiseeventos a WHERE a.vendaAnoAnterior = :vendaAnoAnterior"),
    @NamedQuery(name = "Analiseeventos.findByVendaMesAnterior", query = "SELECT a FROM Analiseeventos a WHERE a.vendaMesAnterior = :vendaMesAnterior")})
public class Analiseeventos implements Serializable {

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
    @Column(name = "QTD_EMBALAG_APOSTA")
    private Integer qtdEmbalagAposta;
    @Column(name = "VENDA_ANO_ANTERIOR")
    private Integer vendaAnoAnterior;
    @Column(name = "VENDA_MES_ANTERIOR")
    private Integer vendaMesAnterior;

    public Analiseeventos() {
    }

    public Analiseeventos(Integer id) {
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

    public Integer getQtdEmbalagAposta() {
        return qtdEmbalagAposta;
    }

    public void setQtdEmbalagAposta(Integer qtdEmbalagAposta) {
        Integer oldQtdEmbalagAposta = this.qtdEmbalagAposta;
        this.qtdEmbalagAposta = qtdEmbalagAposta;
        changeSupport.firePropertyChange("qtdEmbalagAposta", oldQtdEmbalagAposta, qtdEmbalagAposta);
    }

    public Integer getVendaAnoAnterior() {
        return vendaAnoAnterior;
    }

    public void setVendaAnoAnterior(Integer vendaAnoAnterior) {
        Integer oldVendaAnoAnterior = this.vendaAnoAnterior;
        this.vendaAnoAnterior = vendaAnoAnterior;
        changeSupport.firePropertyChange("vendaAnoAnterior", oldVendaAnoAnterior, vendaAnoAnterior);
    }

    public Integer getVendaMesAnterior() {
        return vendaMesAnterior;
    }

    public void setVendaMesAnterior(Integer vendaMesAnterior) {
        Integer oldVendaMesAnterior = this.vendaMesAnterior;
        this.vendaMesAnterior = vendaMesAnterior;
        changeSupport.firePropertyChange("vendaMesAnterior", oldVendaMesAnterior, vendaMesAnterior);
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
        if (!(object instanceof Analiseeventos)) {
            return false;
        }
        Analiseeventos other = (Analiseeventos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Analiseeventos[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
