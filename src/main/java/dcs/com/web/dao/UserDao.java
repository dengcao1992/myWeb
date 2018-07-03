package dcs.com.web.dao;

import dcs.com.web.pojo.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserPO,Long> {
    @Query(value = "select u from UserPO u where u.username = ?1")
    public UserPO findByUsername(String username);
}
