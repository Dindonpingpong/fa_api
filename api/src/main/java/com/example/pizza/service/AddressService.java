package com.example.pizza.service;

import com.example.pizza.model.Address;
import com.example.pizza.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public void create(Address address){
        addressRepository.save(address);
    }

    public Address update(Address address, Address addressFromDB){
        BeanUtils.copyProperties(address, addressFromDB, "id");
        return addressRepository.save(address);
    }

    public boolean delete(Address address) {
        if (address != null){
            addressRepository.delete(address);
            return true;
        }
        return false;
    }
}
