package com.example.helptipsactivity;

//��ʾ�û��б��е�ÿһ����Ŀ
public class Item { 
    
	private int resId; //��ʾͷ��ͼƬ��Դid 
    private String name; //��ʾ�û���
//    private String detail; //��ʾ����ǩ��������

    //���캯��
    public Item(int resId, String name) { 
        this.resId  = resId; 
        this.name   = name; 
//        this.detail = detail; 
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
     
//    public void setDetail(String detail) { 
//        this.detail = detail; 
//    } 
//     
//    public String getDetail() { 
//        return detail; 
//    } 
     
    public String toString() { 
        return "Item[" + resId + ", " + name + ", " + "]"; 
    } 
 
} 
