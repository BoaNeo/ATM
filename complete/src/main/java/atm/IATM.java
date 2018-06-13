package atm;

/**
 * TODO: Eventually this ATM will become useless since there is no way to deposit money :)
 */

public interface IATM
{
  Withdrawal withdraw(int amount);
}
