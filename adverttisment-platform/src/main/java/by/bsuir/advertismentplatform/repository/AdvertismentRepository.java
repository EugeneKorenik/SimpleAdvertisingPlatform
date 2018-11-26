package by.bsuir.advertismentplatform.repository;

import by.bsuir.advertismentplatform.entity.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertismentRepository extends CrudRepository<Advertisement, Long> {

}
