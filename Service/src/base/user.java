package base;

//��ʾ�û��б��е�ÿһ����Ŀ
public class user { 
    
    private int resId; //��ʾͷ��ͼƬ��Դid 
    private String name; //��ʾ�û���
    private String detail; //��ʾ����ǩ��������
     
    //���캯��
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
