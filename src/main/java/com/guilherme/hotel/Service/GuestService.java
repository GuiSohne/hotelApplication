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

    public void save(Guest guest){
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

    public List<Guest> findAll(){
        return dao.findAllGuest();
    }

    public Guest searchByCPF(String cpf){
        if(cpf == null || cpf.isBlank()){
            throw new RuntimeException("To search, you need a CPF.");
        }
        return dao.searchByCPFGuest(cpf);
    }

    public void update(Guest guest){
        dao.updateGuest(guest);
    }

    public void delete(long id){
        if(id <= 0 ){
            throw new RuntimeException("This id is null");
        }
        dao.deleteGuest(id);
    }
}
