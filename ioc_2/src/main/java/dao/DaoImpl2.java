package dao;

import org.springframework.stereotype.Component;

//@Component("dao2")
public class DaoImpl2 implements IDao{

    public double getData() {
        System.out.println("DaoImpl2");
        return 0;
    }
}
