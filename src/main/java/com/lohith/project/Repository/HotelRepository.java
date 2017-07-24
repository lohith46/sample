package com.lohith.project.Repository;

import com.lohith.project.models.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lohith.km on 21-07-2017.
 */
@Transactional
public interface HotelRepository extends MongoRepository<Hotel,Long>{
}
