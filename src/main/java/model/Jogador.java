package model;

import ctrl.MultiCastPeer;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Serializer;

public class Jogador implements Serializable {

    private Integer id;
    private String nick;
    private transient MultiCastPeer multicastConnection;
    private transient boolean server;
    private transient ArrayList<Integer> idProcessos;
    private transient ArrayList<Jogador> listaJogadores;
    private transient int cont = 0;

    public Jogador(String nick) {
        this.nick = nick;
        this.server = false;
        idProcessos = new ArrayList<Integer>();
        listaJogadores = new ArrayList<Jogador>();
        Random r = new Random();
        id = r.nextInt(10000);
        addIdJogadores(id);
        multicastConnection = new MultiCastPeer(this);
    }

    public void addIdJogadores(int id) {
        boolean insereId = true;

        for (Integer idJogadores : idProcessos) {
            if (idJogadores == id) {
                insereId = false;
                break;
            }
        }

        if (insereId) {
            idProcessos.add(id);
            cont++;
        }
    }

    public void addJogador(Jogador jogador) {
        boolean insere = true;

        for (Jogador j : getListaJogadores()) {
            if (jogador.getId().equals(j.getId())) {
                insere = false;
                break;
            }
        }

        if (insere) {
            getListaJogadores().add(jogador);
        }
    }

    public boolean isDefinindoJogadores() {
        if (cont < 4) {
            return true;
        } else {
            eleicao();
            return false;
        }
    }

    public void eleicao() {
        int idJogadorEleito = idProcessos.get(0);

        for (int i = 1; i < idProcessos.size(); i++) {
            if (i > idJogadorEleito) {
                idJogadorEleito = i;
            }
        }

        if (this.getId() == idJogadorEleito) {
            server = true;
        } else {
            server = false;
        }
    }

    public byte[] sendInfo() {
        try {
            String msg = this.getNick() + "|" + this.getId() ;
            return Serializer.serialize(this);
        } catch (IOException ex) {
            Logger.getLogger(Jogador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public ArrayList<Jogador> getListaJogadores() {
        return listaJogadores;
    }

}
