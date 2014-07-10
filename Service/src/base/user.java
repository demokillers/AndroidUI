package base;

//表示用户列表中的每一个条目
public class user { 
    
    private int resId; //表示头像图片资源id 
    private String name; //表示用户名
    private String detail; //表示类似签名的描述
     
    //构造函数
    public user(int resId, String name, String detail) { 
        this.resId  = resId; 
        this.name   = name; 
        this.detail = detail; 
    } 
     
    public void setImageId(int resId) { 
        this.resId  = resId; 
    } 
     
    public int getImageId() { 
        return resId; 
    } 
     
    public void setName(String name) { 
        this.name   = name; 
    } 
     
    public String getName() { 
        return name; 
    } 
     
    public void setDetail(String detail) { 
        this.detail = detail; 
    } 
     
    public String getDetail() { 
        return detail; 
    } 
     
    public String toString() { 
        return "Item[" + resId + ", " + name + ", " + detail + "]"; 
    } 
 
} 
