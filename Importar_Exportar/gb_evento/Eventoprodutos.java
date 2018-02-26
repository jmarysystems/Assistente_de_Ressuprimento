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
@Table(name = "EVENTOPRODUTOS", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Eventoprodutos.findAll", query = "SELECT e FROM Eventoprodutos e"),
    @NamedQuery(name = "Eventoprodutos.findById", query = "SELECT e FROM Eventoprodutos e WHERE e.id = :id"),
    @NamedQuery(name = "Eventoprodutos.findByIdEvento", query = "SELECT e FROM Eventoprodutos e WHERE e.idEvento = :idEvento"),
    @NamedQuery(name = "Eventoprodutos.findBySetor", query = "SELECT e FROM Eventoprodutos e WHERE e.setor = :setor"),
    @NamedQuery(name = "Eventoprodutos.findByItem", query = "SELECT e FROM Eventoprodutos e WHERE e.item = :item"),
    @NamedQuery(name = "Eventoprodutos.findByLoja", query = "SELECT e FROM Eventoprodutos e WHERE e.loja = :loja"),
    @NamedQuery(name = "Eventoprodutos.findByQtdEmb", query = "SELECT e FROM Eventoprodutos e WHERE e.qtdEmb = :qtdEmb"),
    @NamedQuery(name = "Eventoprodutos.findByTipoEmb", query = "SELECT e FROM Eventoprodutos e WHERE e.tipoEmb = :tipoEmb"),
    @NamedQuery(name = "Eventoprodutos.findByTipoMrp", query = "SELECT e FROM Eventoprodutos e WHERE e.tipoMrp = :tipoMrp"),
    @NamedQuery(name = "Eventoprodutos.findByViaDeAbastecimento", query = "SELECT e FROM Eventoprodutos e WHERE e.viaDeAbastecimento = :viaDeAbastecimento"),
    @NamedQuery(name = "Eventoprodutos.findByRegional", query = "SELECT e FROM Eventoprodutos e WHERE e.regional = :regional"),
    @NamedQuery(name = "Eventoprodutos.findByCd", query = "SELECT e FROM Eventoprodutos e WHERE e.cd = :cd"),
    @NamedQuery(name = "Eventoprodutos.findByDescricaoDoMaterial", query = "SELECT e FROM Eventoprodutos e WHERE e.descricaoDoMaterial = :descricaoDoMaterial"),
    @NamedQuery(name = "Eventoprodutos.findByEstoqueLojaCx", query = "SELECT e FROM Eventoprodutos e WHERE e.estoqueLojaCx = :estoqueLojaCx"),
    @NamedQuery(name = "Eventoprodutos.findByVendaMedia12SemanasCx", query = "SELECT e FROM Eventoprodutos e WHERE e.vendaMedia12SemanasCx = :vendaMedia12SemanasCx"),
    @NamedQuery(name = "Eventoprodutos.findByDdeLoja", query = "SELECT e FROM Eventoprodutos e WHERE e.ddeLoja = :ddeLoja"),
    @NamedQuery(name = "Eventoprodutos.findByAdicionalEmCaixas", query = "SELECT e FROM Eventoprodutos e WHERE e.adicionalEmCaixas = :adicionalEmCaixas"),
    @NamedQuery(name = "Eventoprodutos.findByQtdEmUnidades", query = "SELECT e FROM Eventoprodutos e WHERE e.qtdEmUnidades = :qtdEmUnidades")})
public class Eventoprodutos implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ID_EVENTO", nullable = false)
    private int idEvento;
    @Column(name = "SETOR", length = 100)
    private String setor;
    @Column(name = "ITEM", length = 100)
    private String item;
    @Column(name = "LOJA", length = 100)
    private String loja;
    @Column(name = "QTD_EMB", length = 100)
    private String qtdEmb;
    @Column(name = "TIPO_EMB", length = 100)
    private String tipoEmb;
    @Column(name = "TIPO_MRP", length = 100)
    private String tipoMrp;
    @Column(name = "VIA_DE_ABASTECIMENTO", length = 100)
    private String viaDeAbastecimento;
    @Column(name = "REGIONAL", length = 100)
    private String regional;
    @Column(name = "CD", length = 100)
    private String cd;
    @Column(name = "DESCRICAO_DO_MATERIAL", length = 100)
    private String descricaoDoMaterial;
    @Column(name = "ESTOQUE_LOJA_CX", length = 100)
    private String estoqueLojaCx;
    @Column(name = "VENDA_MEDIA_12_SEMANAS_CX", length = 100)
    private String vendaMedia12SemanasCx;
    @Column(name = "DDE_LOJA", length = 100)
    private String ddeLoja;
    @Column(name = "ADICIONAL_EM_CAIXAS", length = 100)
    private String adicionalEmCaixas;
    @Column(name = "QTD_EM_UNIDADES", length = 100)
    private String qtdEmUnidades;

    public Eventoprodutos() {
    }

    public Eventoprodutos(Integer id) {
        this.id = id;
    }

    public Eventoprodutos(Integer id, int idEvento) {
        this.id = id;
        this.idEvento = idEvento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        int oldIdEvento = this.idEvento;
        this.idEvento = idEvento;
        changeSupport.firePropertyChange("idEvento", oldIdEvento, idEvento);
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        String oldSetor = this.setor;
        this.setor = setor;
        changeSupport.firePropertyChange("setor", oldSetor, setor);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        String oldItem = this.item;
        this.item = item;
        changeSupport.firePropertyChange("item", oldItem, item);
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        String oldLoja = this.loja;
        this.loja = loja;
        changeSupport.firePropertyChange("loja", oldLoja, loja);
    }

    public String getQtdEmb() {
        return qtdEmb;
    }

    public void setQtdEmb(String qtdEmb) {
        String oldQtdEmb = this.qtdEmb;
        this.qtdEmb = qtdEmb;
        changeSupport.firePropertyChange("qtdEmb", oldQtdEmb, qtdEmb);
    }

    public String getTipoEmb() {
        return tipoEmb;
    }

    public void setTipoEmb(String tipoEmb) {
        String oldTipoEmb = this.tipoEmb;
        this.tipoEmb = tipoEmb;
        changeSupport.firePropertyChange("tipoEmb", oldTipoEmb, tipoEmb);
    }

    public String getTipoMrp() {
        return tipoMrp;
    }

    public void setTipoMrp(String tipoMrp) {
        String oldTipoMrp = this.tipoMrp;
        this.tipoMrp = tipoMrp;
        changeSupport.firePropertyChange("tipoMrp", oldTipoMrp, tipoMrp);
    }

    public String getViaDeAbastecimento() {
        return viaDeAbastecimento;
    }

    public void setViaDeAbastecimento(String viaDeAbastecimento) {
        String oldViaDeAbastecimento = this.viaDeAbastecimento;
        this.viaDeAbastecimento = viaDeAbastecimento;
        changeSupport.firePropertyChange("viaDeAbastecimento", oldViaDeAbastecimento, viaDeAbastecimento);
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        String oldRegional = this.regional;
        this.regional = regional;
        changeSupport.firePropertyChange("regional", oldRegional, regional);
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        String oldCd = this.cd;
        this.cd = cd;
        changeSupport.firePropertyChange("cd", oldCd, cd);
    }

    public String getDescricaoDoMaterial() {
        return descricaoDoMaterial;
    }

    public void setDescricaoDoMaterial(String descricaoDoMaterial) {
        String oldDescricaoDoMaterial = this.descricaoDoMaterial;
        this.descricaoDoMaterial = descricaoDoMaterial;
        changeSupport.firePropertyChange("descricaoDoMaterial", oldDescricaoDoMaterial, descricaoDoMaterial);
    }

    public String getEstoqueLojaCx() {
        return estoqueLojaCx;
    }

    public void setEstoqueLojaCx(String estoqueLojaCx) {
        String oldEstoqueLojaCx = this.estoqueLojaCx;
        this.estoqueLojaCx = estoqueLojaCx;
        changeSupport.firePropertyChange("estoqueLojaCx", oldEstoqueLojaCx, estoqueLojaCx);
    }

    public String getVendaMedia12SemanasCx() {
        return vendaMedia12SemanasCx;
    }

    public void setVendaMedia12SemanasCx(String vendaMedia12SemanasCx) {
        String oldVendaMedia12SemanasCx = this.vendaMedia12SemanasCx;
        this.vendaMedia12SemanasCx = vendaMedia12SemanasCx;
        changeSupport.firePropertyChange("vendaMedia12SemanasCx", oldVendaMedia12SemanasCx, vendaMedia12SemanasCx);
    }

    public String getDdeLoja() {
        return ddeLoja;
    }

    public void setDdeLoja(String ddeLoja) {
        String oldDdeLoja = this.ddeLoja;
        this.ddeLoja = ddeLoja;
        changeSupport.firePropertyChange("ddeLoja", oldDdeLoja, ddeLoja);
    }

    public String getAdicionalEmCaixas() {
        return adicionalEmCaixas;
    }

    public void setAdicionalEmCaixas(String adicionalEmCaixas) {
        String oldAdicionalEmCaixas = this.adicionalEmCaixas;
        this.adicionalEmCaixas = adicionalEmCaixas;
        changeSupport.firePropertyChange("adicionalEmCaixas", oldAdicionalEmCaixas, adicionalEmCaixas);
    }

    public String getQtdEmUnidades() {
        return qtdEmUnidades;
    }

    public void setQtdEmUnidades(String qtdEmUnidades) {
        String oldQtdEmUnidades = this.qtdEmUnidades;
        this.qtdEmUnidades = qtdEmUnidades;
        changeSupport.firePropertyChange("qtdEmUnidades", oldQtdEmUnidades, qtdEmUnidades);
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
        if (!(object instanceof Eventoprodutos)) {
            return false;
        }
        Eventoprodutos other = (Eventoprodutos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Eventoprodutos[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
