package banknote;

public interface DenominationsBanknotes {

    int getDenominations();

    default int toMoney(int amount) {
        return amount * getDenominations();
    }
}
