package dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao {

public double getData(){
System.out.println("DaoImpl");
    return 10;
}

}