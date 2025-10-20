package com.omeracar.carventory.repository;

import com.omeracar.carventory.dto.DtoGalleristIU;
import com.omeracar.carventory.model.Gallerist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist,Long> {

}
