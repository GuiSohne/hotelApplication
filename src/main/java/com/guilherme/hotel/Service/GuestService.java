package com.guilherme.hotel.Service;

import com.guilherme.hotel.Dao.GuestDAO;
import com.guilherme.hotel.Dao.GuestDAOImp;
import com.guilherme.hotel.Model.Guest;

import java.util.List;

public class GuestService {
    private final GuestDAO dao;

    public GuestService(){
        this.dao = new GuestDAOImp();
    }

    //Salvar hospedes
    public void save(Guest guest){
        //verifica senha, cpf e nome se estão preenchidos e depois salva
        if (guest.getName() == null || guest.getName().isBlank()){
            throw new RuntimeException("Name is required.");
        }

        if (guest.getCpf() == null || guest.getCpf().isBlank()){
            throw new RuntimeException("CPF is required.");
        }

        if(guest.getPassword() == null || guest.getPassword().isBlank()){
            throw new RuntimeException("Password is required.");
        }
        dao.saveGuest(guest);
    }

    //Listar todos os hospedes
    public List<Guest> findAll(){
        return dao.findAllGuest();
    }

    //Listar hospede por cpf
    public Guest searchByCPF(String cpf){
        if(cpf == null || cpf.isBlank()){
            throw new RuntimeException("To search, you need a CPF.");
        }
        return dao.searchByCPFGuest(cpf);
    }

    //Atualizar hospedes
    public void update(Guest guest){
        dao.updateGuest(guest);
    }

    //Deletar hospedes
    public void delete(long id){
        //verifica se o id é usado
        if(id <= 0 ){
            throw new RuntimeException("This id is null");
        }
        dao.deleteGuest(id);
    }
}
