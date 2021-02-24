package atm;


import atmlogic.ATMLogicStd;
import banknote.DenominationsBanknotes;
import banknote.RubBanknotes;
import cell.Cell;
import cell.CellATM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMFactory {
    public static ATMStd atmForRubles() {
        var banknotes = new HashMap<DenominationsBanknotes, Integer>();
        banknotes.put(RubBanknotes.rub_10, 100);
        banknotes.put(RubBanknotes.rub_50, 100);
        banknotes.put(RubBanknotes.rub_100, 80);
        banknotes.put(RubBanknotes.rub_200, 50);
        banknotes.put(RubBanknotes.rub_500, 50);
        banknotes.put(RubBanknotes.rub_1000, 20);
        banknotes.put(RubBanknotes.rub_2000, 20);
        banknotes.put(RubBanknotes.rub_5000, 10);
        return standardATM(banknotes);
    }


    public static ATMStd standardATM(Map<DenominationsBanknotes, Integer> banknotes) {
        List<Cell> cellATMS = new ArrayList();
        banknotes.forEach((banknot, count)->cellATMS.add(new CellATM(banknot, count)));
        return new ATMStd(cellATMS , new ATMLogicStd());
    }
}
