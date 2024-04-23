import dao.BangChamCongCongNhanDao;
import dao.impl.BangChamCongCongNhanImpl;
import entity.BangChamCongCongNhan;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BangChamCongCongNhanDao bangChamCongCongNhanDao = new BangChamCongCongNhanImpl();

        ArrayList<BangChamCongCongNhan> ds = bangChamCongCongNhanDao.timChamCongTheoMa("CN0001");
        System.out.println(ds);
    }
}
