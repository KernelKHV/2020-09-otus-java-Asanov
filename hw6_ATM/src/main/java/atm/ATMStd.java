package atm;


import atmlogic.ATMLogic;
import banknote.DenominationsBanknotes;
import cell.Cell;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ATMStd implements ATM {
    private static final Comparator<Cell> CELL_COMPARATOR = Comparator.comparingInt((Cell c) -> c.getDenominationsBanknotes().getDenominations()).reversed();

    private ATMLogic atmLogic;
    private List<Cell> cellATMS;


    public ATMStd(List<Cell> cellATMS, ATMLogic atmComand) {
        cellATMS.sort(CELL_COMPARATOR);
        this.cellATMS = cellATMS;
        this.atmLogic = atmComand;
    }

    public int getSum(){
        return atmLogic.getSum(cellATMS);
    }

    @Override
    public List<Cell> getCellATMS() {
        return Collections.unmodifiableList(cellATMS);
    }

    @Override
    public int getNumberBills(DenominationsBanknotes banknote) {
        return atmLogic.getNumberBills(cellATMS , banknote);
    }

    @Override
    public void setNumberBills(DenominationsBanknotes banknote, int numberBills) {
        atmLogic.setNumberBills(cellATMS, banknote, numberBills);
    }

    @Override
    public Map<DenominationsBanknotes, Integer> takeMoney(int sum) {
        return atmLogic.takeMoney(cellATMS, sum);
    }
}