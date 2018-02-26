/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor;

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
@Table(name = "FORNECEDOR", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findById", query = "SELECT f FROM Fornecedor f WHERE f.id = :id"),
    @NamedQuery(name = "Fornecedor.findByNomeourazaosocial", query = "SELECT f FROM Fornecedor f WHERE f.nomeourazaosocial = :nomeourazaosocial"),
    @NamedQuery(name = "Fornecedor.findByNomefantasia", query = "SELECT f FROM Fornecedor f WHERE f.nomefantasia = :nomefantasia"),
    @NamedQuery(name = "Fornecedor.findByNomesupervisor", query = "SELECT f FROM Fornecedor f WHERE f.nomesupervisor = :nomesupervisor"),
    @NamedQuery(name = "Fornecedor.findByTelefonesupervisor", query = "SELECT f FROM Fornecedor f WHERE f.telefonesupervisor = :telefonesupervisor"),
    @NamedQuery(name = "Fornecedor.findByNomevendedor", query = "SELECT f FROM Fornecedor f WHERE f.nomevendedor = :nomevendedor"),
    @NamedQuery(name = "Fornecedor.findByTelefonevendedor", query = "SELECT f FROM Fornecedor f WHERE f.telefonevendedor = :telefonevendedor"),
    @NamedQuery(name = "Fornecedor.findByPrazodeentrega", query = "SELECT f FROM Fornecedor f WHERE f.prazodeentrega = :prazodeentrega"),
    @NamedQuery(name = "Fornecedor.findByPrazodepagamento", query = "SELECT f FROM Fornecedor f WHERE f.prazodepagamento = :prazodepagamento"),
    @NamedQuery(name = "Fornecedor.findByTipodepessoa", query = "SELECT f FROM Fornecedor f WHERE f.tipodepessoa = :tipodepessoa"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"),
    @NamedQuery(name = "Fornecedor.findByCd", query = "SELECT f FROM Fornecedor f WHERE f.cd = :cd"),
    @NamedQuery(name = "Fornecedor.findByFrequencia", query = "SELECT f FROM Fornecedor f WHERE f.frequencia = :frequencia"),
    @NamedQuery(name = "Fornecedor.findByLeadtime", query = "SELECT f FROM Fornecedor f WHERE f.leadtime = :leadtime"),
    @NamedQuery(name = "Fornecedor.findByGeracaopedidosra", query = "SELECT f FROM Fornecedor f WHERE f.geracaopedidosra = :geracaopedidosra"),
    @NamedQuery(name = "Fornecedor.findByNomepromotor", query = "SELECT f FROM Fornecedor f WHERE f.nomepromotor = :nomepromotor"),
    @NamedQuery(name = "Fornecedor.findByTelefonepromotor", query = "SELECT f FROM Fornecedor f WHERE f.telefonepromotor = :telefonepromotor"),
    @NamedQuery(name = "Fornecedor.findByEmailpromotor", query = "SELECT f FROM Fornecedor f WHERE f.emailpromotor = :emailpromotor"),
    @NamedQuery(name = "Fornecedor.findByQtdminimapallet", query = "SELECT f FROM Fornecedor f WHERE f.qtdminimapallet = :qtdminimapallet"),
    @NamedQuery(name = "Fornecedor.findByQtdminimacaixa", query = "SELECT f FROM Fornecedor f WHERE f.qtdminimacaixa = :qtdminimacaixa"),
    @NamedQuery(name = "Fornecedor.findByValorminimo", query = "SELECT f FROM Fornecedor f WHERE f.valorminimo = :valorminimo")})
public class Fornecedor implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NOMEOURAZAOSOCIAL", length = 300)
    private String nomeourazaosocial;
    @Column(name = "NOMEFANTASIA", length = 300)
    private String nomefantasia;
    @Column(name = "NOMESUPERVISOR", length = 300)
    private String nomesupervisor;
    @Column(name = "TELEFONESUPERVISOR", length = 300)
    private String telefonesupervisor;
    @Column(name = "NOMEVENDEDOR", length = 300)
    private String nomevendedor;
    @Column(name = "TELEFONEVENDEDOR", length = 300)
    private String telefonevendedor;
    @Column(name = "PRAZODEENTREGA", length = 300)
    private String prazodeentrega;
    @Column(name = "PRAZODEPAGAMENTO", length = 300)
    private String prazodepagamento;
    @Column(name = "TIPODEPESSOA", length = 300)
    private String tipodepessoa;
    @Column(name = "CNPJ", length = 300)
    private String cnpj;
    @Column(name = "CD", length = 300)
    private String cd;
    @Column(name = "FREQUENCIA", length = 300)
    private String frequencia;
    @Column(name = "LEADTIME", length = 300)
    private String leadtime;
    @Column(name = "GERACAOPEDIDOSRA", length = 300)
    private String geracaopedidosra;
    @Column(name = "NOMEPROMOTOR", length = 300)
    private String nomepromotor;
    @Column(name = "TELEFONEPROMOTOR", length = 300)
    private String telefonepromotor;
    @Column(name = "EMAILPROMOTOR", length = 300)
    private String emailpromotor;
    @Column(name = "QTDMINIMAPALLET", length = 300)
    private String qtdminimapallet;
    @Column(name = "QTDMINIMACAIXA", length = 300)
    private String qtdminimacaixa;
    @Column(name = "VALORMINIMO", length = 300)
    private String valorminimo;

    public Fornecedor() {
    }

    public Fornecedor(Integer id) {
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

    public String getNomeourazaosocial() {
        return nomeourazaosocial;
    }

    public void setNomeourazaosocial(String nomeourazaosocial) {
        String oldNomeourazaosocial = this.nomeourazaosocial;
        this.nomeourazaosocial = nomeourazaosocial;
        changeSupport.firePropertyChange("nomeourazaosocial", oldNomeourazaosocial, nomeourazaosocial);
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        String oldNomefantasia = this.nomefantasia;
        this.nomefantasia = nomefantasia;
        changeSupport.firePropertyChange("nomefantasia", oldNomefantasia, nomefantasia);
    }

    public String getNomesupervisor() {
        return nomesupervisor;
    }

    public void setNomesupervisor(String nomesupervisor) {
        String oldNomesupervisor = this.nomesupervisor;
        this.nomesupervisor = nomesupervisor;
        changeSupport.firePropertyChange("nomesupervisor", oldNomesupervisor, nomesupervisor);
    }

    public String getTelefonesupervisor() {
        return telefonesupervisor;
    }

    public void setTelefonesupervisor(String telefonesupervisor) {
        String oldTelefonesupervisor = this.telefonesupervisor;
        this.telefonesupervisor = telefonesupervisor;
        changeSupport.firePropertyChange("telefonesupervisor", oldTelefonesupervisor, telefonesupervisor);
    }

    public String getNomevendedor() {
        return nomevendedor;
    }

    public void setNomevendedor(String nomevendedor) {
        String oldNomevendedor = this.nomevendedor;
        this.nomevendedor = nomevendedor;
        changeSupport.firePropertyChange("nomevendedor", oldNomevendedor, nomevendedor);
    }

    public String getTelefonevendedor() {
        return telefonevendedor;
    }

    public void setTelefonevendedor(String telefonevendedor) {
        String oldTelefonevendedor = this.telefonevendedor;
        this.telefonevendedor = telefonevendedor;
        changeSupport.firePropertyChange("telefonevendedor", oldTelefonevendedor, telefonevendedor);
    }

    public String getPrazodeentrega() {
        return prazodeentrega;
    }

    public void setPrazodeentrega(String prazodeentrega) {
        String oldPrazodeentrega = this.prazodeentrega;
        this.prazodeentrega = prazodeentrega;
        changeSupport.firePropertyChange("prazodeentrega", oldPrazodeentrega, prazodeentrega);
    }

    public String getPrazodepagamento() {
        return prazodepagamento;
    }

    public void setPrazodepagamento(String prazodepagamento) {
        String oldPrazodepagamento = this.prazodepagamento;
        this.prazodepagamento = prazodepagamento;
        changeSupport.firePropertyChange("prazodepagamento", oldPrazodepagamento, prazodepagamento);
    }

    public String getTipodepessoa() {
        return tipodepessoa;
    }

    public void setTipodepessoa(String tipodepessoa) {
        String oldTipodepessoa = this.tipodepessoa;
        this.tipodepessoa = tipodepessoa;
        changeSupport.firePropertyChange("tipodepessoa", oldTipodepessoa, tipodepessoa);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        String oldCnpj = this.cnpj;
        this.cnpj = cnpj;
        changeSupport.firePropertyChange("cnpj", oldCnpj, cnpj);
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        String oldCd = this.cd;
        this.cd = cd;
        changeSupport.firePropertyChange("cd", oldCd, cd);
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        String oldFrequencia = this.frequencia;
        this.frequencia = frequencia;
        changeSupport.firePropertyChange("frequencia", oldFrequencia, frequencia);
    }

    public String getLeadtime() {
        return leadtime;
    }

    public void setLeadtime(String leadtime) {
        String oldLeadtime = this.leadtime;
        this.leadtime = leadtime;
        changeSupport.firePropertyChange("leadtime", oldLeadtime, leadtime);
    }

    public String getGeracaopedidosra() {
        return geracaopedidosra;
    }

    public void setGeracaopedidosra(String geracaopedidosra) {
        String oldGeracaopedidosra = this.geracaopedidosra;
        this.geracaopedidosra = geracaopedidosra;
        changeSupport.firePropertyChange("geracaopedidosra", oldGeracaopedidosra, geracaopedidosra);
    }

    public String getNomepromotor() {
        return nomepromotor;
    }

    public void setNomepromotor(String nomepromotor) {
        String oldNomepromotor = this.nomepromotor;
        this.nomepromotor = nomepromotor;
        changeSupport.firePropertyChange("nomepromotor", oldNomepromotor, nomepromotor);
    }

    public String getTelefonepromotor() {
        return telefonepromotor;
    }

    public void setTelefonepromotor(String telefonepromotor) {
        String oldTelefonepromotor = this.telefonepromotor;
        this.telefonepromotor = telefonepromotor;
        changeSupport.firePropertyChange("telefonepromotor", oldTelefonepromotor, telefonepromotor);
    }

    public String getEmailpromotor() {
        return emailpromotor;
    }

    public void setEmailpromotor(String emailpromotor) {
        String oldEmailpromotor = this.emailpromotor;
        this.emailpromotor = emailpromotor;
        changeSupport.firePropertyChange("emailpromotor", oldEmailpromotor, emailpromotor);
    }

    public String getQtdminimapallet() {
        return qtdminimapallet;
    }

    public void setQtdminimapallet(String qtdminimapallet) {
        String oldQtdminimapallet = this.qtdminimapallet;
        this.qtdminimapallet = qtdminimapallet;
        changeSupport.firePropertyChange("qtdminimapallet", oldQtdminimapallet, qtdminimapallet);
    }

    public String getQtdminimacaixa() {
        return qtdminimacaixa;
    }

    public void setQtdminimacaixa(String qtdminimacaixa) {
        String oldQtdminimacaixa = this.qtdminimacaixa;
        this.qtdminimacaixa = qtdminimacaixa;
        changeSupport.firePropertyChange("qtdminimacaixa", oldQtdminimacaixa, qtdminimacaixa);
    }

    public String getValorminimo() {
        return valorminimo;
    }

    public void setValorminimo(String valorminimo) {
        String oldValorminimo = this.valorminimo;
        this.valorminimo = valorminimo;
        changeSupport.firePropertyChange("valorminimo", oldValorminimo, valorminimo);
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
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fornecedor.Fornecedor[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
