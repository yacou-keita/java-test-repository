package metier;

import dao.IDao;

public class MetierImpl implements IMetier{
    IDao dao;
    public double calcule() {
        return this.dao.getData() * 10;
    }

   public void setDao(IDao dao){
        this.dao = dao;
    }
}
