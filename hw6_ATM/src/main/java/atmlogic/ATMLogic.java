package atmlogic;


import banknote.DenominationsBanknotes;
import cell.Cell;

import java.util.List;
import java.util.Map;

public interface  ATMLogic {
    int getSum(List<Cell> cellATMS);

    int getNumberBills(List<Cell> cellATMS, DenominationsBanknotes banknote);

    void setNumberBills(List<Cell> cellATMS, DenominationsBanknotes banknote, int numberBills);

    Map<DenominationsBanknotes, Integer> takeMoney(List<Cell> cellATMS, int sum);
}
