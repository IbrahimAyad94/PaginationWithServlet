/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ibrahim Ayad
 */
public class EmpDao {
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gym", "root", "root");
        } catch(Exception ex){
            System.out.println("Error in getConnection" + ex.getMessage());
        }
        return con;
    }
    public static  int save(Emp e){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into emp (name, pass, emial, countery)values(?, ?, ?, ?);");
            ps.setString(1, e.getName());
            ps.setString(2, e.getPass());
            ps.setString(3, e.getEmial());
            ps.setString(4, e.getCountry());
            status = ps.executeUpdate();
            con.close();
        } catch(Exception ex){
            System.out.println("Error in save" + ex.getMessage());
        }
        return status;
    }
    
    public static int delete(int id){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("delete from emp where id = ?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.close();
        } catch(Exception ex){
            System.out.println("Error in delete " + ex.getMessage());
        }
        return status;
    }
    public static Emp getEmployeeById(int id){
        Emp e = new Emp();
        try{
            Connection con = EmpDao.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from emp where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPass(rs.getString(3));
                e.setEmial(rs.getString(4));
                e.setCountry(rs.getString(5));
                
            }
            con.close();
        } catch(Exception ex){
            System.out.println("Error in getEmployeeById " + ex.getMessage());
        }
        return e;
    }
    public static List<Emp> getAllEmployees(){
        List<Emp> list = new ArrayList<Emp>();
        try{
            Connection con = EmpDao.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from emp");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Emp e=new Emp();  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPass(rs.getString(3));  
                e.setEmial(rs.getString(4));  
                e.setCountry(rs.getString(5));  
                list.add(e); 
            }
            con.close();
        } catch(Exception e){
            System.out.println("Error in getAllEmployees");
        }
        return list;
    }
    public static int update(Emp e){  
        int status=0;  
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update emp set name=?,pass=?,emial=?,countery=? where id=?");  
            ps.setString(1,e.getName());  
            ps.setString(2,e.getPass());  
            ps.setString(3,e.getEmial());  
            ps.setString(4,e.getCountry());  
            ps.setInt(5,e.getId());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){
            System.out.println("Error in update" + ex.getMessage());
        }  
          
        return status;  
    }
    /* Addition */
    public static List<Emp> getRecords(int x,int y){
        List<Emp> list = new ArrayList<Emp>();
        try{
            Connection con = EmpDao.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from emp limit " + (x-1) + "," + y);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Emp e=new Emp();  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPass(rs.getString(3));  
                e.setEmial(rs.getString(4));  
                e.setCountry(rs.getString(5));  
                list.add(e); 
            }
            con.close();
        } catch(Exception e){
            System.out.println("Error in getAllEmployees");
        }
        return list;
    }
}
