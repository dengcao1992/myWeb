package dcs.com.web.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class UserPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long uid;

    @Column(name = "u_username", unique = true,length = 20, nullable = false)
    private String username;

    @Column(name = "u_psaaword", length = 48, nullable = false)
    private String password;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "username", nullable = false)
//    private Set<PhotoPO> photoPOs;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

//    public Set<PhotoPO> getPhotoPOs() {
//        return photoPOs;
//    }
//
//    public void setPhotoPOs(Set<PhotoPO> photoPOs) {
//        this.photoPOs = photoPOs;
//    }
}
