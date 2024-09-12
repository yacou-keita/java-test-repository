package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier {

private final IDao dao;
    public MetierImpl(IDao dao){
        this.dao = dao;
    }
public double calcul(){
    double tmp = dao.getData();
    double res = tmp * 540/Math.cos(tmp*Math.PI);
return res;
}



}