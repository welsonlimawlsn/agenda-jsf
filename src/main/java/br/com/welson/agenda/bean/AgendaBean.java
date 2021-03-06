package br.com.welson.agenda.bean;

import br.com.welson.agenda.model.Contato;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AgendaBean implements Serializable {

    private List<Contato> contatos;

    public void init() {
        try {
            contatos = Contato.listAll();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String excluir(Contato contato) {
        try {
            contato.excluir();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "index?faces-redirect=true";
    }

    public String alterar(Contato contato) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("contato", contato);
        return "editarContato.xhtml?faces-redirect=true";
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }


}
