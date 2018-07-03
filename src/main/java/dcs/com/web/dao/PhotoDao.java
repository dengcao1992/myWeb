package dcs.com.web.dao;

import dcs.com.web.pojo.PhotoPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhotoDao extends JpaRepository<PhotoPO,Long> {
}
