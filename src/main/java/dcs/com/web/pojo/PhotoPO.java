package dcs.com.web.pojo;



import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name = "t_photo")
public class PhotoPO {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    //标题
    @Column
    private String title;
    //创建时间
    private Date created;
    //文件名
    private String name;
    //用户
    @Column(name = "user_name")
    private String username;
    //类别
    private String categories;
    //点击数
    private int hit;
    //下载数
    private int downloadNum;
    //收藏数
    private int collectNum;
    //喜欢数
    private int lickNum;
    //图片原始高度
    private int width;
    //图片原始宽度
    private int height;
    //url
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PhotoPO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(int downloadNum) {
        this.downloadNum = downloadNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getLickNum() {
        return lickNum;
    }

    public void setLickNum(int lickNum) {
        this.lickNum = lickNum;
    }
}
