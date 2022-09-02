package br.com.ufg.tcc.medicamentos.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public void save(final AddressEntity address) {
        addressRepository.save(address);
    }

    public void saveAll(final List<AddressEntity> list) {
        addressRepository.saveAll(list);
    }

}
