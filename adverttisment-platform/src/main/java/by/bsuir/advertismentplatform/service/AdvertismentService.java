package by.bsuir.advertismentplatform.service;

import by.bsuir.advertismentplatform.entity.Advertisement;
import by.bsuir.advertismentplatform.repository.AdvertismentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertismentService {

    @Autowired
    private AdvertismentRepository advertismentRepository;

    public List<Advertisement> findAll() {
        return (List)advertismentRepository.findAll();
    }

    public Advertisement findById(long id) {
        return advertismentRepository.findById(id).get();
    }

    public void save(Advertisement advertisement) {
        advertismentRepository.save(advertisement);
    }

    public void deleteById(long id) {
        advertismentRepository.deleteById(id);
    }
}
