package dcs.com.web.pojo.bo;

public class RestResponseBo {
    boolean succeed;

    public RestResponseBo(boolean succeed){
        this.succeed = succeed;
    }

    public static RestResponseBo ok(){
        return new RestResponseBo(true);
    }
}
